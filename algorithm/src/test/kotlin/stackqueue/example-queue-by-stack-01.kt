// This file was automatically generated from 4.md by Knit tool. Do not edit.
package com.github.takahirom.algorithm.exampleQueueByStack01

import java.util.*

class MyQueue {
    val storeStack = Stack<Int>()
    val tempStack = Stack<Int>()

    fun push(value: Int) {
        while(storeStack.isNotEmpty()) {
            tempStack.push(storeStack.pop())
        }
        storeStack.push(value)
        while(tempStack.isNotEmpty()){
            storeStack.push(tempStack.pop())
        }
    }

    fun pop(): Int {
        return storeStack.pop()
    }
}

fun main() {
    val myQueue = MyQueue()
    myQueue.push(1)
    myQueue.push(2)
    myQueue.push(3)
    println(myQueue.pop())
    myQueue.push(4)
    println(myQueue.pop())
}
