// This file was automatically generated from 6.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class BinarySearchTreeNextNodeTest {
    @Test
    fun testExampleBinarySearchNodeNext01() {
        captureOutput("ExampleBinarySearchNodeNext01") { com.github.takahirom.algorithm.exampleBinarySearchNodeNext01.main() }.verifyOutputLines(
            "3",
            " 1",
            "  0",
            "  2",
            " 5",
            "  4",
            "  6",
            "BinaryTreeNode(4)"
        )
    }
}
