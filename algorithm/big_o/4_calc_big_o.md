# 計算方法

* 定数を捨てる

2N + 10 とかでも O(n)

* 影響の少ない項を捨てる

n^2 + n とかだと O(n^2)

# 償却計算量(amortized time complexity)

時々起こる最悪ケースが有るとき、「償却」して記述できる。 
例えば配列のサイズを2倍にしていく場合、X回追加すると  
1 + 2 + 4 + 8 +... + Xの計算量になる。  
これは右からX + X/2 + X/4... で代替 2Xの計算量になる。  
つまりX回で2xの計算量になっているので、これを償却計算量では O(1)となる。

# Log N実行時間

2分探索のような場合。  
  
要素数Nの場合。N = 2であれば1回比較、N=4であれば2回比較する感じになり、  
2^k = N  
のk回計算することになる。  
これは k = log 2 Nになるため、O(log N)となる。  


探索空間の要素数が毎回半分になっていくような場合はおそらくO(log N)になる。


# 再起の実行時間

```
int f(int) {
  if (n <= 1) {
    return 1;
  }
  return f(n-1) + f(n-1);
}
```

深さがNになって、枝が深さが1増えるごとに2個増えるので、2^(N+1) - 1個ノードができるので実行時間はO(2^N)になる。  
O(枝の数^深さ)になる事が多い。  
  
ちなみに空間計算量は同時にNだけあれば良いので、O(N)となる。  
ちなみに枝が4つあった場合、O(4^N)になるが、これはO(2^N)と同じではない。なぜなら2^N * 2^Nになるので、違うもの。  


# 例題

## 例題3

```
(0..N).forEachIndexed { i ->
  (i..N).forEachIndexed { j ->
    if(i < j) {}
  }
}
```

計算回数を考える。

<details>
<summary>答え</summary>
N=4であれば、中は1+2+3+4回 = 4+1 + 3+2 = N+1 + N+1  
N=6であれば、中は1+2+3+4+5+6回 = 6+1 + 5+2 + 4+3 = N+1 + N+1 + N+1  
つまり、N/2 *( N+1 ) 、つまりO(N^2)  
</details>

## 例題4

```
(0..arrayA.size).forEachIndexed { i ->
  (i..arrayB.size).forEachIndexed { j ->
    if(i < j) {}
  }
}
```

<details>
<summary>答え</summary>
これはO(N^2)ではない。O(ab)。aとbは異なるデータであるため。
</details>

## 例題8
文字列の配列について、各文字列をソートし、その後配列全体をソートするアルゴリズムの実行時間は？  

<details>
<summary>答え</summary>
安易に各文字列のソート O(N*N log N) 、その後のソート O(N log N)だから O(N^2 log N)とすると誤り。    

最も長い文字列の長さをsとする。  
文字列の配列の要素数をaとする。  
文字列のソート: O(s log s)  
各文字列のソート: O(a * s log s)  
全体ソート: O(a log a)  
文字列の全体ソート(要素ごとにs必要): O(s * a log a)  
各文字列のソート + 文字列の全体ソート(要素ごとにs必要)なので  
O(a * s log s) + O(s * a log a) = s * a (log a + log s)  
</details>

## 例題9  

以下の計算量

```
int sum(Node node) {
  if (node == null) {
    return 0;
  }
  return sum(node.left) + node.value + sum(node.right);
}
```

<details>
<summary>答え</summary>
Nodeの数をnだとするとO(n)。  

別解  
深さは1増えるごとにノードの数が2倍になっていくので、
n = 2^深さ  
また、深さはlog 2 nになる  
つまり計算時間は2^(log n)になる  

対数の定義   
2^P = Q であれば P = log 2 Q  

Xが計算時間とすると  
X = 2^(log n)になる  
両方にlog 2をつける  
log 2 X = log 2 2^(log n)  
log 2 X = log2 n  
つまり  
X = n  

</details>

## 例題10

素数を求める以下のプログラムの時間計算量はいくらか。

```
boolean isPrime(int n) {
  for (int x = 2; x * x <= n; x++) {
    if (n % x == 0) {
      return false;
    }
  }
}
```

<details>
<summary>答え</summary>
繰り返す回数が√nまでになるので、O(√n)
</details>

## 例題12

順列をすべて表示するプログラム  

```kotlin
fun permutation(str: String) {
    permutation(str, "")
}

fun permutation(str: String, prefix: String) {
    if (str.isEmpty()) {
        println(prefix)
    }
    str.forEachIndexed { index, c ->
        val rem = str.substring(0, index) + str.substring(index + 1)
        permutation(rem, prefix + c)
    }
}

fun main() {
    permutation("abcd")
}
```

<details>
<summary>答え</summary>
繰り返す回数が、一つ深くなるたびに一つ減っていくので葉1の数はO(N!)  

これを合計する必要があるので  
n<N  
Σ n!になりそうだが、とりあえず、これを求めることは現実的ではないので、一旦は長さnの経路でつながっているので、n * n!とする。  
n=1  
  
また文字列の出力にnになるので、O(n^2 * n!)になる  
</details>

## 例題14
単純なフィボナッチの計算アルゴリズムO(n^2)のときに、0からn番目まで計算するときのオーダー  

<details>
<summary>答え</summary>
O(1*1 + 2*2 + 3*3 + .. + n*n)で、一番大きい項が必要になるので、O(2^n)になる。
</details>

## 例題15
メモ化したフィボナッチ計算  

<details>
<summary>答え</summary>
Nの数だけ一度計算すればいいので、O(N)
</details>

## 例題16

```
int powersOf2(int n) {
  if ()
```
