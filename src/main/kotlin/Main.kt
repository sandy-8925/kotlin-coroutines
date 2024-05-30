package org.example.coroutines

import kotlinx.coroutines.*

fun main() {
    seventhCoroutine()
}

private fun seventhCoroutine() = runBlocking {
    val job = launch {
        repeat(1000) { ctr ->
            println("Sleeping, counter = $ctr")
            delay(500L)
        }
    }
    delay(1300L)
    println("Tired of waiting")
    job.cancelAndJoin()
    println("Done")
}

private fun sixthCoroutine() = runBlocking {
    repeat(50_000) { // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}

private fun fifthCoroutine() = runBlocking {
    val job = launch {
        delay(1000L)
        println("World")
    }
    println("Hello")
    job.join()
    println("Done")
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