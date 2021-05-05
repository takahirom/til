// This file was automatically generated from 6.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleSortedstack01

import java.util.*

class SortStack(initialList: List<Int>) {
    private val storeStack = Stack<Int>()
    private val tempStack = Stack<Int>()

    init {
        initialList.forEach {
            tempStack.push(it)
        }
    }
    
    private fun Stack<Int>.peekOrNull(): Int? {
        if(isEmpty()) return null
        return peek()
    }

    fun sort() {
        while(!tempStack.empty()) {
            val temp = tempStack.pop()
            while(storeStack.peekOrNull() ?: Int.MAX_VALUE < temp) {
                tempStack.push(storeStack.pop())
            }
            storeStack.push(temp)
        }
    }

    fun pop(): Int {
        return storeStack.pop()
    }
}

fun main() {
    val sortStack = SortStack(listOf(1, 3, 2, 4))
    sortStack.sort()
    println(sortStack.pop())
    println(sortStack.pop())
    println(sortStack.pop())
    println(sortStack.pop())
}
