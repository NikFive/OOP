package ru.nsu.fit.konstantinov.view.console

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import ru.nsu.fit.konstantinov.dto.console.ConsoleDto
import ru.nsu.fit.konstantinov.presenter.ConsolePresenter
import ru.nsu.fit.konstantinov.timer.ConsoleTimer
import ru.nsu.fit.konstantinov.timer.Timer
import ru.nsu.fit.konstantinov.utils.GameConfiguration
import ru.nsu.fit.konstantinov.utils.SceneManager
import ru.nsu.fit.konstantinov.view.View
import kotlin.system.exitProcess

class ConsoleView(override var sceneManager: SceneManager = SceneManager()) : View {
    private val columnsCount: Int = GameConfiguration.gameConfiguration.consoleScreenWidth
    private val rowsCount: Int = GameConfiguration.gameConfiguration.consoleScreenHeight
    private val presenter = ConsolePresenter(this).also {
        createScene()
        it.start()
    }
    private var screen: Screen? = null
    private var graphics: TextGraphics? = null
    private var terminal: Terminal? = null

    private fun createScene() {
        terminal = DefaultTerminalFactory()
            .setInitialTerminalSize(TerminalSize(columnsCount, rowsCount))
            .createTerminal()
        screen = TerminalScreen(terminal)
        graphics = terminal!!.newTextGraphics()
        terminal!!.setCursorVisible(false)
    }

    override fun endGame() {
        terminal!!.clearScreen()
        graphics!!.putString(TerminalPosition.OFFSET_1x1, "GAME OVER")
        terminal!!.flush()
        screen!!.refresh()
        var keyStroke: KeyStroke?
        do {
            keyStroke = screen!!.pollInput()
        } while (keyStroke == null)
        exitProcess(0)
    }

    override fun clearScreen() {
        screen!!.clear()
        terminal!!.clearScreen()
    }

    override fun render() {
        val consoleDtoList = presenter.getDtoList()
        consoleDtoList.forEach { dto: ConsoleDto -> renderDto(dto) }
        terminal!!.flush()
        screen!!.refresh()
    }

    private fun renderDto(dto: ConsoleDto?) {
        graphics!!.drawLine(dto!!.x, dto.y, dto.x, dto.y, dto.character)
    }

    override fun setTimer(gameStep: Runnable): Timer {
        return ConsoleTimer(Thread {
            while (true) {
                val keyStroke = screen!!.pollInput()
                if (keyStroke != null) {
                    presenter.handleKey(keyStroke)
                }
                gameStep.run()
                Thread.sleep(200)
            }
        })
    }

    override fun pause() {}
}