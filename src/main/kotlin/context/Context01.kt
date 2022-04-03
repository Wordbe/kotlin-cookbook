package context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch { // 부모 컨텍스트, main runBlocking coroutine
        println("main runBlocking       :" +
                " I'm working in thread ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) { // 갇히지 않고, main thread 와 같이 일한다.
        println("Unconfined             :" +
                " I'm working in thread ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) { // DefaultDispatcher 쓰레드에 보내진다.
        println("Default                :" +
                " I'm working in thread ${Thread.currentThread().name}")
    }

    newSingleThreadContext("MyOwnThread").use { // use 를 써야 새로운 쓰레드 생성시 close 를 같이 해준다.
        launch(it) {
            println("newSingleThreadContext :" +
                    " I'm working in thread ${Thread.currentThread().name}")
        }
    }
}