// This file was automatically generated from 6.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleBinarySearchNodeNext01

import com.github.takahirom.tree.*

fun main() {
    val tree = BinarySearchTree.of(listOf(0, 1, 2, 3, 4, 5, 6))
    tree.root.printTree()
    println(tree.getNextNodeInOrderByValue(2))
}
