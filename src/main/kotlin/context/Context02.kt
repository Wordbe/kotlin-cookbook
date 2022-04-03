package context

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.rmi.server.LogStream.log

// Jump between Threads
fun main() {
    newSingleThreadContext("context1").use { ctx1 ->
        newSingleThreadContext("context2").use { ctx2 ->
            runBlocking(ctx1) {
                println("started in context1")

                withContext(ctx2) {
                    println("working in context2")
                }

                println("back to context1")
            }
        }
    }
}