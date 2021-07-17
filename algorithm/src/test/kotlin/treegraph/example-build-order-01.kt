// This file was automatically generated from 7.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleBuildOrder01

import com.github.takahirom.tree.*
import java.util.*

fun main() {
    val projects = listOf("a", "b", "c", "d", "e", "f")
    val list = listOf("b" to "a", "d" to "a", "e" to "d", "c" to "e", "c" to "b")
    val adjacencyList = mutableMapOf<String, MutableList<String>>()
    list.forEach { (dependentProject, project) ->
        val dependencies = adjacencyList.getOrPut(project) {
            mutableListOf<String>()
        }
        dependencies.add(dependentProject)
    }
    // O(n)

    // find the root
    val dependants = list.map { it.first }.toSet()
    val roots = projects.filter { project ->
        !dependants.contains(project)
    }
//    println(roots)

    val visitNodes = mutableSetOf<String>()

    fun visit(node: String) {
        if(visitNodes.contains(node)) return
        adjacencyList[node]?.forEach {
            visit(it)
        }
        visitNodes.add(node)
    }
    roots.forEach { node ->
        visit(node)
    }
    println(visitNodes)
}
