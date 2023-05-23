package ru.nsu.fit.konstantinov

import ru.nsu.fit.konstantinov.game.snake.SnakeGameConsole
import ru.nsu.fit.konstantinov.game.snake.SnakeGameFXML
import java.util.*

fun main() {
    println("Snake")
    println("Enter 1 to launch console version!")
    println("Enter 2 to launch fxml version")
    when (Scanner(System.`in`).nextInt()) {
        1 -> SnakeGameConsole().startGame()
        2 -> SnakeGameFXML().startGame()
        else -> {
            println("Illegal number")
        }
    }
}
