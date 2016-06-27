テーマか要素か何かがあたって、なんでViewがこの色になるの！？みたいな状態になることって無いですか？
backgroundの色など

1.layout.xmlで指定されている要素 AttributeSet   

```xml
<TextView 
android:text="....">
```
などのandroid:text要素などレイアウトで直接指定しているものです。

2.Style要素で指定されている要素
style要素で指定して、styleで指定するとその文字の色になったりします。

```xml
<TextView 
android:text="...."
style="@style/CodeFont"
>
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="CodeFont" parent="@android:style/TextAppearance.Medium">
        <item name="android:textColor">#00FF00</item>
    </style>
</resources>
```

3.一つ一つのViewクラスに対するテーマ要素で指定されている要素 defStyleAttr
Viewごとにデフォルトのスタイルのテーマ要素が指定されています。
普通はこの要素はViewのコンストラクタで第三引数で渡しています。

具体的には以下のとおりです。
TextViewのコードだと`com.android.internal.R.attr.textViewStyle`になります。
テーマ要素の場合は継承関係があるので、AppCompatベースのテーマであれば大量のテーマ要素がデフォルトで用意されています。この中のtextViewStyleを取り出して、利用します。
https://android.googlesource.com/platform/frameworks/base/+/ee4ab0a/core/java/android/widget/TextView.java#667

```java
    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, com.android.internal.R.attr.textViewStyle);
    }
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }
    @SuppressWarnings("deprecation")
    public TextView(
            Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
...
```


実際には以下の様なstyleがセットされています。

https://github.com/android/platform_frameworks_base/blob/4535e11fb7010f2b104d3f8b3954407b9f330e0f/core/res/res/values/themes.xml#L300

```xml
        <item name="textViewStyle">@style/Widget.TextView</item>
```

Widget.TextViewの内容はここにあります。
https://github.com/android/platform_frameworks_base/blob/4535e11fb7010f2b104d3f8b3954407b9f330e0f/core/res/res/values/styles.xml#L489


4.デフォルトのスタイルで適応されている要素 defStyleRes

TextViewだと0を渡している第四引数目の要素で、テーマではなく、リソースIDを直接渡すようです。
(あんまり使われていない？)

```java
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TextView(
            Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
...
```

5.テーマで設定されている要素
ここまでで一個も指定されていなかった場合、テーマの要素が利用されるようになります。
