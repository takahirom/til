// This file was automatically generated from 6.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class IsBinarySearchTreeTest {
    @Test
    fun testExampleIsBinarySearchNode01() {
        captureOutput("ExampleIsBinarySearchNode01") { com.github.takahirom.algorithm.exampleIsBinarySearchNode01.main() }.verifyOutputLines(
            "3",
            " 1",
            "  0",
            "  2",
            " 5",
            "  4",
            "  6",
            "true",
            "false"
        )
    }
}
