# Doze

DeviceIdleControllerで状態を管理
https://android.googlesource.com/platform/frameworks/base/+/master/services/core/java/com/android/server/DeviceIdleController.java#1392

これは次にmStateがIDLE=Dozeモードに切り替わるときのコード。つまりはDozeモードに入るときのコード。  
まず状態が切り替わった時に以下のように`scheduleAlarmLocked`を呼ぶ。  

```java
            case STATE_IDLE_MAINTENANCE:
                scheduleAlarmLocked(mNextIdleDelay, true);
                if (DEBUG) Slog.d(TAG, "Moved to STATE_IDLE. Next alarm in " + mNextIdleDelay +
                        " ms.");
                mNextIdleDelay = (long)(mNextIdleDelay * mConstants.IDLE_FACTOR);
                mNextIdleDelay = Math.min(mNextIdleDelay, mConstants.MAX_IDLE_TIMEOUT);
                mState = STATE_IDLE;
```

https://android.googlesource.com/platform/frameworks/base/+/master/services/core/java/com/android/server/DeviceIdleController.java#1505

AlarmManager.setIdleUntil()またはAlarmManager.set()を呼び出す  
引数がtrueなのでsetIdleUntil()を呼ぶ

```java
    void scheduleAlarmLocked(long delay, boolean idleUntil) {
        // 省略
        mNextAlarmTime = SystemClock.elapsedRealtime() + delay;
        if (idleUntil) {
            mAlarmManager.setIdleUntil(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    mNextAlarmTime, mAlarmIntent);
        } else {
            mAlarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    mNextAlarmTime, mAlarmIntent);
        }
    }
```

https://android.googlesource.com/platform/frameworks/base.git/+/master/core/java/android/app/AlarmManager.java#410

```java
    /**
     * Schedule an idle-until alarm, which will keep the alarm manager idle until
     * the given time.
     * @hide
     */
    public void setIdleUntil(int type, long triggerAtMillis, PendingIntent operation) {
        setImpl(type, triggerAtMillis, WINDOW_EXACT, 0, FLAG_IDLE_UNTIL, operation, null, null);
    }

```

https://android.googlesource.com/platform/frameworks/base/+/master/services/core/java/com/android/server/AlarmManagerService.java#969

```java
    private void setImplLocked(Alarm a, boolean rebatching, boolean doValidate) {
        if ((a.flags&AlarmManager.FLAG_IDLE_UNTIL) != 0) {
            // This is a special alarm that will put the system into idle until it goes off.
            // The caller has given the time they want this to happen at, however we need
            // to pull that earlier if there are existing alarms that have requested to
            // bring us out of idle at an earlier time.
            if (mNextWakeFromIdle != null && a.whenElapsed > mNextWakeFromIdle.whenElapsed) {
                a.when = a.whenElapsed = a.maxWhenElapsed = mNextWakeFromIdle.whenElapsed;
            }
            // Add fuzz to make the alarm go off some time before the actual desired time.
            final long nowElapsed = SystemClock.elapsedRealtime();
            final int fuzz = fuzzForDuration(a.whenElapsed-nowElapsed);
            if (fuzz > 0) {
                if (mRandom == null) {
                    mRandom = new Random();
                }
                final int delta = mRandom.nextInt(fuzz);
                a.whenElapsed -= delta;
                if (false) {
                    Slog.d(TAG, "Alarm when: " + a.whenElapsed);
                    Slog.d(TAG, "Delta until alarm: " + (a.whenElapsed-nowElapsed));
                    Slog.d(TAG, "Applied fuzz: " + fuzz);
                    Slog.d(TAG, "Final delta: " + delta);
                    Slog.d(TAG, "Final when: " + a.whenElapsed);
                }
                a.when = a.maxWhenElapsed = a.whenElapsed;
            }
        } else if (mPendingIdleUntil != null) {
            // We currently have an idle until alarm scheduled; if the new alarm has
            // not explicitly stated it wants to run while idle, then put it on hold.
            if ((a.flags&(AlarmManager.FLAG_ALLOW_WHILE_IDLE
                    | AlarmManager.FLAG_ALLOW_WHILE_IDLE_UNRESTRICTED
                    | AlarmManager.FLAG_WAKE_FROM_IDLE))
                    == 0) {
                mPendingWhileIdleAlarms.add(a);
                return;
            }
        }
        int whichBatch = ((a.flags&AlarmManager.FLAG_STANDALONE) != 0)
                ? -1 : attemptCoalesceLocked(a.whenElapsed, a.maxWhenElapsed);
        if (whichBatch < 0) {
            Batch batch = new Batch(a);
            addBatchLocked(mAlarmBatches, batch);
        } else {
            Batch batch = mAlarmBatches.get(whichBatch);
            if (batch.add(a)) {
                // The start time of this batch advanced, so batch ordering may
                // have just been broken.  Move it to where it now belongs.
                mAlarmBatches.remove(whichBatch);
                addBatchLocked(mAlarmBatches, batch);
            }
        }
        if (a.alarmClock != null) {
            mNextAlarmClockMayChange = true;
        }
        boolean needRebatch = false;
        if ((a.flags&AlarmManager.FLAG_IDLE_UNTIL) != 0) {
            mPendingIdleUntil = a;
            mConstants.updateAllowWhileIdleMinTimeLocked();
            needRebatch = true;
        } else if ((a.flags&AlarmManager.FLAG_WAKE_FROM_IDLE) != 0) {
            if (mNextWakeFromIdle == null || mNextWakeFromIdle.whenElapsed > a.whenElapsed) {
                mNextWakeFromIdle = a;
                // If this wake from idle is earlier than whatever was previously scheduled,
                // and we are currently idling, then we need to rebatch alarms in case the idle
                // until time needs to be updated.
                if (mPendingIdleUntil != null) {
                    needRebatch = true;
                }
            }
        }
        if (!rebatching) {
            if (DEBUG_VALIDATE) {
                if (doValidate && !validateConsistencyLocked()) {
                    Slog.v(TAG, "Tipping-point operation: type=" + a.type + " when=" + a.when
                            + " when(hex)=" + Long.toHexString(a.when)
                            + " whenElapsed=" + a.whenElapsed
                            + " maxWhenElapsed=" + a.maxWhenElapsed
                            + " interval=" + a.repeatInterval + " op=" + a.operation
                            + " flags=0x" + Integer.toHexString(a.flags));
                    rebatchAllAlarmsLocked(false);
                    needRebatch = false;
                }
            }
            if (needRebatch) {
                rebatchAllAlarmsLocked(false);
            }
            rescheduleKernelAlarmsLocked();
            updateNextAlarmClockLocked();
        }
    }

```


https://android.googlesource.com/platform/frameworks/base/+/master/services/core/java/com/android/server/DeviceIdleController.java
https://android.googlesource.com/platform/frameworks/base/+/master/services/core/java/com/android/server/AlarmManagerService.java