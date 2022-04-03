package coroutineprinciple

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

// CPS Simulation

fun main() {
    println("main in")
    myCoroutine(MyContinuation())
    println("\nmain out")
}

fun myCoroutine(cont: MyContinuation) {
    when(cont.label) {
        0 -> {
            println("\nmyCoroutine(), label: ${cont.label}")
            cont.label = 1
            fetchUserData(cont)
        }
        1 -> {
            println("\nmyCoroutine(), label: ${cont.label}")
            val userData = cont.result
            cont.label = 2
            cacheUserData(userData, cont)
        }
        2 -> {
            println("\nmyCoroutine(), label: ${cont.label}")
            val userCache = cont.result
            updateTextView(userCache)
        }
    }
}

// suspend function
fun fetchUserData(cont: MyContinuation) {
    println("fetch user data")
    val result = "User info from a server"
    println("completed: $result")
    cont.resumeWith(Result.success(result))
}

// suspend function
fun cacheUserData(user: String, cont: MyContinuation) {
    println("cache user data")
    val result = "cached $user"
    println("completed: $result")
    cont.resumeWith(Result.success(result))
}

fun updateTextView(user: String) {
    println("update text view")
    println("completed: [print $user on textview")
}

class MyContinuation(override val context: CoroutineContext = EmptyCoroutineContext) : Continuation<String> {
    var label = 0
    var result = ""

    override fun resumeWith(result: Result<String>) {
        this.result = result.getOrThrow()
        println("Continuation.resumeWith()")
        myCoroutine(this)
    }
}