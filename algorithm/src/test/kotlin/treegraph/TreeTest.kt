// This file was automatically generated from 2.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class TreeTest {
    @Test
    fun testExampleBinarySearchTree01() {
        captureOutput("ExampleBinarySearchTree01") { com.github.takahirom.algorithm.exampleBinarySearchTree01.main() }.verifyOutputLines(
            "3",
            " 1",
            "  0",
            "  2",
            " 5",
            "  4",
            "  6",
            "3",
            " 1",
            "  0",
            "  2",
            " 4"
        )
    }
}
