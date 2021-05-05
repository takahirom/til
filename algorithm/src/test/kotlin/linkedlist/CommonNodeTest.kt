// This file was automatically generated from 7.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class CommonNodeTest {
    @Test
    fun testExampleCommonNode401() {
        captureOutput("ExampleCommonNode401") { com.github.takahirom.algorithm.exampleCommonNode401.main() }.verifyOutputLines(
            "Node(value=2, next=Node(value=3, next=null))"
        )
    }
}
