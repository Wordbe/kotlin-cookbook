package delegates

import kotlin.reflect.KProperty

class MyDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("'$value' has been assigned to '${property.name}' in $thisRef.")
    }
}

fun main() {
    var p: String by MyDelegate()
    println(p)
    p = "NEW"
}