// This file was automatically generated from 8.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class LoopNodeTest {
    @Test
    fun testExampleCycle01() {
        captureOutput("ExampleCycle01") { com.github.takahirom.algorithm.exampleCycle01.main() }.verifyOutputLines(
            "Node(value = 2, next.value = 3)"
        )
    }
}
