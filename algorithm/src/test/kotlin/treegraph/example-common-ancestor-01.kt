// This file was automatically generated from 8.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleCommonAncestor01

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
 *    5
 *     3
 * 2    4
 * 1
 */

fun main() {
    val targets = listOf(1, 4)
    val root = Node(
        5,
        null,
        Node(
            3,
            Node(
                2,
                Node(1, null, null),
                null
            ),
            Node(4, null, null)
        )
    )


    fun visit(node: Node): Result {
        if (targets.contains(node.value)) return Result.OneFound
        val resultLeft = node.left?.let {
            visit(it)
        }
        val resultRight = node.right?.let {
            visit(it)
        }
        if (resultLeft is Result.TwoFound) {
            return resultLeft
        }
        if (resultRight is Result.TwoFound) {
            return resultRight
        }
        if (resultLeft is Result.OneFound && resultRight is Result.OneFound) {
            return Result.TwoFound(node)
        }
        if (resultLeft is Result.OneFound) {
            return Result.OneFound
        }
        if (resultRight is Result.OneFound) {
            return Result.OneFound
        }
        return Result.NotFound
    }
    println((visit(root) as Result.TwoFound).node.value)
}
