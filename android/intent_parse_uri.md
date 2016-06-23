# Intent#parseUri
3パターンのIntentをパースできる
これはChromeや標準ブラウザはこれを利用してURLから起動するIntentを決める。

## 旧パターン

```
testdata#action(test)categories(test!test2)type(mtype)launchFlags(5)
```

みたいな感じ
ここに例がある

https://android.googlesource.com/platform/cts/+/3d8cbe9/tests/tests/content/src/android/content/cts/IntentTest.java#677
内部的にgetIntentOldを使ってパースする
https://android.googlesource.com/platform/frameworks/base/+/c2b3e48bb1ab75163f8d7890db39b91436c06015/core/java/android/content/Intent.java#4718

## 新パターン
最もよく使われる奴
このドキュメントが詳しい
https://developer.chrome.com/multidevice/android/intents
文法はこれ

```
intent:
   HOST/URI-path // Optional host 
   #Intent; 
      package=[string]; 
      action=[string]; 
      category=[string]; 
      component=[string]; 
      scheme=[string]; 
   end; 
```


```
intent://scan/#Intent;scheme=zxing;package=com.google.zxing.client.android;end
```

Chromeでは
S.browser_fallback_url=http%3A%2F%2Fzxing.org
のようにつけることによって、インテントの起動に失敗した時の動きを定義できる。

## android-app:スキーマ
パッケージを指定して起動することができる
API Level 22
公式ドキュメントが詳しい
https://developer.android.com/reference/android/content/Intent.html#URI_ANDROID_APP_SCHEME

```
android-app://com.example.app/http/example.com/foo?1234
#Intent;action=com.example.MY_ACTION;end
```

こんな感じ

API Level 22から使うことができる、22未満ではparseUriすると、おそらくIntentのデータに入ることになる

そのintentをstartActivityしてもうまく動く。理由はおそらく以下のActivityがスキーマを受け取って割り振ってくれるように作ってあるからである。
com.google.android.googlequicksearchbox/com.google.android.search.calypso.AppIndexingActivity


`adb shell dumpsys package |view -`
でアプリが対応するスキームが見れる