val N = 1000

fun main() {
    val cache = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    (1..N).forEach { a ->
        (1..N).forEach { b ->
            val mutableList = cache.getOrPut(a * a * a + b * b * b) {
                mutableListOf<Pair<Int, Int>>()
            }
            mutableList.add(a to b)
        }
    }
    cache.forEach { (_, list) ->
        list.forEach { (a, b) ->
            list.forEach { (c, d) ->
                println("$a $b $c $d")
            }
        }
    }
}

main()