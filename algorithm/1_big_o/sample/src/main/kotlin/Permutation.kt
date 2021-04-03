fun permutation(str: String) {
    permutation(str, "")
}

var count = 0

fun permutation(str: String, prefix: String) {
    if (str.isEmpty()) {
//        println(prefix)
    }
    str.forEachIndexed { index, c ->
        count++
        val rem = str.substring(0, index) + str.substring(index + 1)
        println("\"${prefix.ifEmpty { "blank" }}\" -> \"${prefix + c}\"")
        permutation(rem, prefix + c)
    }
}

fun main() {
    permutation("abcd")
    /*
str:abc rem: bc
str:bc rem: c
str:c rem:
abc
str:bc rem:b
str:b rem:
acb
str:abc rem:a c
str:ac rem: c
str:c rem:
bac
str:ac rem:a
str:a rem:
bca
str:abc rem:ab
str:ab rem: b
str:b rem:
cab
str:ab rem:a
str:a rem:
cba
     */
}