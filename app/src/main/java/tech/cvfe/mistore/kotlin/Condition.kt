package tech.cvfe.mistore.kotlin

fun main() {
    checkNumber(1.2f)
}

fun checkNumber(num: Number) = when (num) {
    is Int -> println("int")
    is Float -> println("float")
    else -> println("other")
}