# Kotlinで三項演算子
Kotlinには三項演算子がないが、if文が値を返せるので、以下のように書ける。  
直感的でいいかも

```kotlin
val languageDirectoryName = if (language == Language.KOTLIN) "kotlin" else "java"
```
