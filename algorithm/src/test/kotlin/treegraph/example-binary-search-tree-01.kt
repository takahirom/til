// This file was automatically generated from 2.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleBinarySearchTree01

import com.github.takahirom.tree.*

fun main() {
    val tree = BinarySearchTree.of(listOf(0, 1, 2, 3, 4, 5, 6))
    tree.root.printTree()

    val tree2 = BinarySearchTree.of(listOf(0, 1, 2, 3, 4))
    tree2.root.printTree()
}
