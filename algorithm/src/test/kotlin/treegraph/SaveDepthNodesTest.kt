// This file was automatically generated from 3.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class SaveDepthNodesTest {
    @Test
    fun testExampleSameDepthNodes01() {
        captureOutput("ExampleSameDepthNodes01") { com.github.takahirom.algorithm.exampleSameDepthNodes01.main() }.verifyOutputLines(
            "3",
            " 1",
            "  0",
            "  2",
            " 5",
            "  4",
            "  6",
            "[[BinaryTreeNode(3)], [BinaryTreeNode(1), BinaryTreeNode(5)], [BinaryTreeNode(0), BinaryTreeNode(2), BinaryTreeNode(4), BinaryTreeNode(6)]]"
        )
    }
}
