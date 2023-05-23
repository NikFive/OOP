package ru.nsu.fit.konstantinov.view.fxml

import javafx.animation.AnimationTimer
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import ru.nsu.fit.konstantinov.dto.fxml.FXMLDto
import ru.nsu.fit.konstantinov.presenter.FXMLPresenter
import ru.nsu.fit.konstantinov.timer.FXMLTimer
import ru.nsu.fit.konstantinov.utils.GameConfiguration
import ru.nsu.fit.konstantinov.utils.SceneManager
import ru.nsu.fit.konstantinov.view.View
import java.net.URL
import java.util.*


class FXMLView(override var sceneManager: SceneManager = SceneManager()) : Initializable, View {
    @FXML
    lateinit var exitButton: Button

    @FXML
    lateinit var continueButton: Button

    @FXML
    lateinit var gameCanvas: Canvas

    @FXML
    lateinit var box: VBox

    @FXML
    lateinit var pauseMenu: AnchorPane

    private var isPaused = false

    private var presenter = FXMLPresenter(this)

    @FXML
    fun handleKeyPressed(keyEvent: KeyEvent) {
        if (keyEvent.code == KeyCode.SPACE) {
            togglePause()
            return
        }
        presenter.handleKey(keyEvent)
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        presenter = FXMLPresenter(this)
        box.onKeyPressed = EventHandler { event: KeyEvent? ->
            (presenter).handleKey(event!!)
        }
        gameCanvas.onKeyPressed = EventHandler { event: KeyEvent? ->
            (presenter).handleKey(event!!)
        }
        (presenter).start()
    }

    override fun endGame() {
        sceneManager.changeScene("gameOverScreen.fxml", gameCanvas.scene)
    }

    override fun clearScreen() {
        gameCanvas.graphicsContext2D.fill = Color.rgb(245, 245, 245)!!
        gameCanvas.graphicsContext2D.fillRect(0.0, 0.0, 1000.0, 1000.0)
    }

    override fun render() {
        val dtoList = presenter.getDtoList()
        dtoList.forEach { dto: FXMLDto -> dto.render(gameCanvas.graphicsContext2D) }
    }

    override fun setTimer(gameStep: Runnable): FXMLTimer {
        return FXMLTimer(object : AnimationTimer() {
            var lastTick: Long = 0
            override fun handle(now: Long) {
                val TIMER_TICK: Double = 100000000 / GameConfiguration.gameConfiguration.gameSpeed
                if (now - lastTick > TIMER_TICK || lastTick == 0L) {
                    lastTick = now
                    gameStep.run()
                }
            }
        })
    }

    override fun pause() {
        gameCanvas.isVisible = false
    }

    fun handleExitButton() {
        sceneManager.exit(box.scene)
    }

    fun handleContinueButton() {
        togglePause()
    }

    private fun togglePause() {
        if (isPaused) {
            presenter.start()
        }
        pauseMenu.isVisible = !isPaused
        gameCanvas.isVisible = true
        isPaused = !isPaused
    }
}
