package tech.cvfe.mistore.kotlin.oop


//接口类
interface KInterface {
    fun eat() {
        println("eat default 实现")
    }

    fun say()
}

class KPerson(var name: String, var age: Int) : KInterface {
    override fun say() {
        println("I am $name, age: $age")
    }
}

fun main() {
    val kPerson = KPerson("cvfe", 18)
    kPerson.eat()
    kPerson.say()
}