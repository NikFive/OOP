package ru.nsu.fit.konstantinov.task_2_2_1

import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.Pizzeria
import ru.nsu.fit.konstantinov.task_2_2_1.utils.ConfigParser
import java.util.*

fun main() {
    val pizzeria =
        Pizzeria(ConfigParser.getConfigModelFromFile("src/test/resources/ru.nsu.fit.konstantinov.task_2_2_1/PizzeriaTestConfig.json"))
    var isWorking = true
    while (isWorking) {
        when (Scanner(System.`in`).nextLine()) {
            "start" -> {
                println("Starting work")
                Thread(pizzeria).start()
            }
            "stop" -> {
                println("Stopping work")
                pizzeria.forceClosing()
            }
            "close" -> {
                pizzeria.gracefulClosing()
                println("Ending work")
                isWorking = false
            }
            else -> println("Unknown command. You can only write: start, stop, close.")
        }
    }
}
