// This file was automatically generated from 4.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class MyQueueTest {
    @Test
    fun testExampleQueueByStack01() {
        captureOutput("ExampleQueueByStack01") { com.github.takahirom.algorithm.exampleQueueByStack01.main() }.verifyOutputLines(
            "1",
            "2"
        )
    }
}
