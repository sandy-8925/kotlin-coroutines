package org.example.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    fourthCoroutine()
}

private fun fourthCoroutine() = runBlocking {
    fourthDoWorld()
    println("Done")
}

private suspend fun fourthDoWorld() = coroutineScope {
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}

private fun thirdCoroutine() = runBlocking { thirdDoWorld() }

private suspend fun thirdDoWorld() = coroutineScope {
    launch {
        delay(1000L)
        println("World")
    }
    println("Hello")
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