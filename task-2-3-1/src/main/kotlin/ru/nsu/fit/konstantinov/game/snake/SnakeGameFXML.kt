package ru.nsu.fit.konstantinov.game.snake

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import ru.nsu.fit.konstantinov.game.Game
import java.util.*

class SnakeGameFXML : Application(), Game {
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(Objects.requireNonNull(javaClass.classLoader.getResource("mainScreen.fxml")))
        primaryStage.apply {
            this.title = "Snake game"
            this.scene = Scene(root)
            this.show()
        }
    }

    override fun startGame() {
        launch()
    }
}
