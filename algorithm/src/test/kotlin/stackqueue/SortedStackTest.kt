// This file was automatically generated from 6.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class SortedStackTest {
    @Test
    fun testExampleSortedstack01() {
        captureOutput("ExampleSortedstack01") { com.github.takahirom.algorithm.exampleSortedstack01.main() }.verifyOutputLines(
            "1",
            "2",
            "3",
            "4"
        )
    }
}
