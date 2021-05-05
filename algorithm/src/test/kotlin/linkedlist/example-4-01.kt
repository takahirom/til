// This file was automatically generated from 6.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.example401


import java.util.*

data class Node(val value: Int, val next: Node?)

fun isPalindrome(node: Node): Boolean {
    var nodeSize = 1
    var currentNode = node
    while (currentNode.next != null) {
        currentNode = currentNode.next!!
        nodeSize++
    }
    if (nodeSize == 1) {
        return true
    }

    val stack = Stack<Node>()
    val isOdd = nodeSize % 2 == 1
    currentNode = node
    repeat(nodeSize / 2) {
        stack.push(currentNode)
        currentNode = currentNode.next!!
    }
    if (isOdd) currentNode = currentNode.next!!
    while (true) {
        val popedValue = stack.pop().value
        val currentValue = currentNode.value
        if (popedValue != currentValue) return false
        if(currentNode.next == null) break
        currentNode = currentNode.next!!
    }
    return true
}

fun main() {
    println(isPalindrome(Node(1, null)))
    println(isPalindrome(Node(1, Node(1, null))))
    println(isPalindrome(Node(1, Node(2, null))))
    println(isPalindrome(Node(1, Node(2, Node(1, null)))))
    println(isPalindrome(Node(1, Node(2, Node(2, Node(1, null))))))
}
