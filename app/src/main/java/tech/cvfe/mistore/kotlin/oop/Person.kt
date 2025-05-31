package tech.cvfe.mistore.kotlin.oop

open class Person(var name: String, var age: Int) {
    fun eat() {
        println("${this.name} is eating.")
        println("${this.name} is ${age} years old")
    }
}

class Student(private val grade: Int, name: String, age: Int) : Person(name, age) {

    init {
        println("name: $name, age:$age, grade:${grade}")
    }

    constructor(name: String, age: Int) : this(0, name, age) {
        println("name: $name, age:$age, grade:${grade}")
    }

    constructor() : this("unknown", 0) {
        println("name: $name, age:$age, grade:${grade}")
    }
}

fun main() {
    val person = Person("Jack", 32)
    person.name = "Jack1"
    person.eat()
    println("stu1 init")
    val stu1 = Student(1, "Jack Son", 12);
    println("stu2 init")
    val stu2 = Student("Jackson", 12)
    println("stu3 init")
    val stu3 = Student();

    var user = User("Jack", 12, "123")
    var user1 = User("Jack", 12, "123")
    println("user: $user, user1: $user1")
    println("user==user1: ${user == user1}")

    Logger.info("我是日志")
    Logger.debug("我是日志")
}