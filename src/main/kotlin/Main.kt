package org.example.coroutines

import kotlinx.coroutines.*

fun main() {
    eleventthCoroutine()
}

private fun eleventthCoroutine() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { counter ->
                println("Iteration number $counter")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("Finished the loop")
                delay(1000L)
                println("Delayed for one second because I'm non-cancellable")
            }
        }
    }
    delay(1300L)
    println("Tired of waiting")
    job.cancelAndJoin()
    println("Done")
}

private fun tenthCoroutine() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { counter ->
                println("Iteration number $counter")
                delay(500L)
            }
        } finally {
            println("Finished the loop")
        }
    }
    delay(1300L)
    println("Tired of waiting")
    job.cancelAndJoin()
    println("Done")
}

private fun ninthCoroutine() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

private fun eighthCoroutine() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
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