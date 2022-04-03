package context

import kotlinx.coroutines.*
import java.rmi.server.LogStream.log

fun main() = runBlocking<Unit>() {
    println("My job is ${coroutineContext[Job]}")

    launch {
        println("My job is ${coroutineContext[Job]}")
    }

    async {
        println("My job is ${coroutineContext[Job]}")
    }
}