// This file was automatically generated from 7.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleCommonNode401

import java.util.*
import kotlin.collections.ArrayList

data class Node(val value: Int, val next: Node?)

fun commonNode(node1: Node, node2: Node): Node? {
    val queue1 = LinkedList<Node>()
    val queue2 = LinkedList<Node>()
    var currentNode = node1
    while (true) {
        queue1.add(currentNode)
        val next = currentNode.next ?: break
        currentNode = next
    }
    currentNode = node2
    while (true) {
        queue2.add(currentNode)
        val next = currentNode.next ?: break
        currentNode = next
    }
    while (true) {
        if (queue1.size < queue2.size) {
            queue2.poll()
        } else if (queue1.size == queue2.size) {
            break
        } else {
            queue1.poll()
        }
    }
//    println("1:"+queue1)
//    println("2:"+queue2)

    var resultCandidate: Node? = null
    while (true) {
        if (queue1.size == 0) {
            return resultCandidate
        } else {
            val currentNode1 = queue1.poll()
            val currentNode2 = queue2.poll()
            if (currentNode1.value == currentNode2.value) {
                if (resultCandidate == null) resultCandidate = currentNode1
            } else {
                resultCandidate = null
            }
//            println("candiate:$resultCandidate")
        }
    }
    return null
}

fun main() {
    println(
        commonNode(
            Node(1, Node(2, Node(3, null))),
            Node(4, Node(3, Node(2, Node(3, null))))
        )
    )
}
