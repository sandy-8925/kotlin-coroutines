package org.example.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    secondCoroutine()
}

private fun secondCoroutine() {
    runBlocking {
        launch { doWorld() }
        println("Hello")
    }
}

private suspend fun doWorld() {
    delay(1000L)
    println("World")
}

private fun firstCoroutine() {
    runBlocking {
        launch {
            delay(1000L)
            println("World")
        }
        println("Hello")
    }
}