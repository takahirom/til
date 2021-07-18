// This file was automatically generated from 9.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleBstSequence01

data class Node(
    val value: Int,
    val left: Node?,
    val right: Node?,
)


sealed class Result {
    object NotFound : Result()
    object OneFound : Result()
    data class TwoFound(val node: Node) : Result()
}

/**
 *     4
 *  2    5
 * 1 3
 */

fun main() {
    val targets = listOf(1, 4)
    val root = Node(
        4,
        Node(
            2,
            Node(1, null, null),
            Node(3, null, null)
        ),
        Node(
            5,
            null,
            null
        )
    )
    val cache = mutableMapOf<Int, List<List<Int>>>()
    fun visit(node: Node): List<List<Int>> {
        return cache.getOrPut(node.value) {
            if (node.right == null && node.left == null) {
                return@getOrPut listOf(listOf(node.value))
            }
            if (node.right == null) {
                if (node.left != null) {
                    return@getOrPut visit(node.left).map { leftResult ->
                        listOf(node.value) + leftResult
                    }
                }
            }
            if (node.left == null) {
                if (node.right != null) {
                    return@getOrPut visit(node.right).map { rightResult ->
                        listOf(node.value) + rightResult
                    }
                }
            }
            val results = mutableListOf<List<Int>>()
            visit(node.left!!).forEach { leftResult ->
                visit(node.right!!).forEach { rightResult ->
                    val list1 = listOf(node.value) + leftResult + rightResult
                    val list2 = listOf(node.value) + rightResult + leftResult
                    results.add(list1)
                    results.add(list2)
                }
            }
            return@getOrPut results
        }
    }
    println(visit(root))
}
