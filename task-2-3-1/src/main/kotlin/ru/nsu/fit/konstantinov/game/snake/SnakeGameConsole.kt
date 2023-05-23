package ru.nsu.fit.konstantinov.game.snake

import ru.nsu.fit.konstantinov.game.Game
import ru.nsu.fit.konstantinov.view.console.ConsoleView

class SnakeGameConsole : Game {
    override fun startGame() {
        ConsoleView()
    }
}
