// This file was automatically generated from 6.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class PalindromeTest {
    @Test
    fun testExample401() {
        captureOutput("Example401") { com.github.takahirom.algorithm.example401.main() }.verifyOutputLines(
            "true",
            "true",
            "false",
            "true",
            "true"
        )
    }
}
