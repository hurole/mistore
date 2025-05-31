package tech.cvfe.mistore.kotlin.oop

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//单例类
object Logger {
     fun info(message: String) {
        println("[INFO] ${now()} $message")
    }

    fun debug(message: String) {
        println("[DEBUG] ${now()} $message")
    }

    private fun now(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

}