// This file was automatically generated from 4.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class IsSelfBalancingTest {
    @Test
    fun testExampleIsSelfBalancingNode01() {
        captureOutput("ExampleIsSelfBalancingNode01") { com.github.takahirom.algorithm.exampleIsSelfBalancingNode01.main() }.verifyOutputLines(
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
