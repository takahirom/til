// This file was automatically generated from 8.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class CommonAncestorTest {
    @Test
    fun testExampleCommonAncestor01() {
        captureOutput("ExampleCommonAncestor01") { com.github.takahirom.algorithm.exampleCommonAncestor01.main() }.verifyOutputLines(
            "3"
        )
    }
}
