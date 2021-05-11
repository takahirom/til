// This file was automatically generated from 5.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleIsBinarySearchNode01

import com.github.takahirom.tree.*

fun main() {
    val tree = BinarySearchTree.of(listOf(0, 1, 2, 3, 4, 5, 6))
    tree.root.printTree()
    println(tree.isSearchTree())

    val tree2 = BinaryTree(
        BinaryTreeNode(
            2,
            BinaryTreeNode(
                1,
                null,
                BinaryTreeNode(
                    4,
                    BinaryTreeNode(
                        1
                    )
                )
            ),
            BinaryTreeNode(
                3
            ),
        )
    )
    println(tree2.isSearchTree())
}
