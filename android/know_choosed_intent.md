# 暗黙的インテントで選んだものをAndroid 5.1以上で取得できる
ちゃんと動作確認はできていませんができるようです。    


こちらのドキュメントの説明には以下のようにあります。  
https://developer.android.com/reference/android/content/Intent.html#createChooser(android.content.Intent, java.lang.CharSequence, android.content.IntentSender)

     The caller may optionally supply an IntentSender to receive a callback when the user makes a choice. This can be useful if the calling application wants to remember the last chosen target and surface it as a more prominent or one-touch affordance elsewhere in the UI for next time.   

ユーザーが選んだものを受け取けとることができる。次に起動するときに楽にするのに使えると書いてあります。    

実際にChromeでは前回選んだ共有先がメニューに表示されるようになっています。   
   

実装例はこちらです。  

https://chromium.googlesource.com/chromium/src.git/+/master/chrome/android/java/src/org/chromium/chrome/browser/share/ShareHelper.java#91
