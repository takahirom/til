// This file was automatically generated from 8.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleCycle01

import java.util.*
import kotlin.collections.ArrayList

data class Node(val value: Int, var next: Node?) {
    override fun toString(): String {
        return "Node(value = $value, next.value = ${next?.value})"
    }
}

fun cycleNode(head: Node): Node? {
    var slowPointer = head
    var fastPointer = head
    while (true) {
        slowPointer = slowPointer.next ?:return null
        fastPointer = fastPointer.next?.next ?: return null
        if (slowPointer == fastPointer) break
    }
    slowPointer = head
    while (true) {
        slowPointer = slowPointer.next ?:return null
        fastPointer = fastPointer.next ?:return null

        if (slowPointer == fastPointer) break
    }
    return slowPointer
}

fun main() {
    val cycle = Node(4, null)
    val startNode = Node(2, Node(3, cycle))
    cycle.next = startNode
    val node = Node(1, startNode)
    println(
        cycleNode(
            node
        )
    )
}
