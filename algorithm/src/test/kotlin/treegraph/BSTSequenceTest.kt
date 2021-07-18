// This file was automatically generated from 9.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class BSTSequenceTest {
    @Test
    fun testExampleBstSequence01() {
        captureOutput("ExampleBstSequence01") { com.github.takahirom.algorithm.exampleBstSequence01.main() }.verifyOutputLines(
            "[[4, 2, 1, 3, 5], [4, 5, 2, 1, 3], [4, 2, 3, 1, 5], [4, 5, 2, 3, 1]]"
        )
    }
}
