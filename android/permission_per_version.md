# バージョンごとのパーミッションの詳細情報とその差分を出す方法  
そんなに必要になることはないと思いますが、、  
AOSPにパーミッションの一覧が載っているシステムのAndroidManifestがあるようです。  
https://android.googlesource.com/platform/frameworks/base/+/master/core/res/AndroidManifest.xml 

パーミッショングループやprotectionLevelが見れるのは良いと思います   
あとは新しいOSについて調べたりするときは便利でしょうか  
  
5.1.1から6.0.1へのdiffを出すには以下のようにします。  
https://android.googlesource.com/platform/frameworks/base/+/android-5.1.1_r37..android-6.0.1_r46/core/res/AndroidManifest.xml
