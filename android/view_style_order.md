# styleが適応される順番

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

3. Viewに対するテーマで指定されている要素
4. デフォルトのスタイルで適応されている要素
5. テーマでベースとなる色