// This file was automatically generated from 7.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm

import org.junit.Test
import kotlinx.knit.test.*

class BuildOrderTest {
    @Test
    fun testExampleBuildOrder01() {
        captureOutput("ExampleBuildOrder01") { com.github.takahirom.algorithm.exampleBuildOrder01.main() }.verifyOutputLines(
            "[c, e, d, a, b, f]"
        )
    }
}
