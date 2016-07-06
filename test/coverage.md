# C0 C1 C2というテスト時のカバレッジの名前がある

どこまでユニットテストを実施していくかの指標になりそう。  


このスライドの画像が分かりやすかった  
http://www.slideshare.net/hiroppie/test-coverage

# C0の場合  
命令網羅  

命令を網羅する  
以下の場合、doItという命令がちゃんと行われる。testがtrueの時のみ行う  

```java
if (test) {
    doIt();
}
```

# C1の場合  
分岐網羅  

testがtrueのときもfalseの時も行う  

# C2の場合  
条件網羅  
test1がtrue false  
test2がtrue falseの4パターンを行う


```java
if (test1 && test2) {
    doIt();
}

```
