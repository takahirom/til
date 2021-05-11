// This file was automatically generated from 4.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleIsSelfBalancingNode01

import com.github.takahirom.tree.*

fun main() {
    val tree = BinarySearchTree.of(listOf(0, 1, 2, 3, 4, 5, 6))
    tree.root.printTree()
    println(tree.isSelfBalancingTree())

    val tree2 = BinaryTree(
        BinaryTreeNode(
            1,
            BinaryTreeNode(
                2,
                BinaryTreeNode(
                    4,
                    BinaryTreeNode(
                        5
                    )
                )
            ),
            BinaryTreeNode(
                3
            ),
        )
    )
    println(tree2.isSelfBalancingTree())
}
