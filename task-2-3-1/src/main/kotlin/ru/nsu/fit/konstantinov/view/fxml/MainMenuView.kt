package ru.nsu.fit.konstantinov.view.fxml

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import ru.nsu.fit.konstantinov.utils.SceneManager
import java.net.URL
import java.util.*

class MainMenuView : Initializable {
    private val sceneManager = SceneManager()

    @FXML
    lateinit var settingsButton: Button

    @FXML
    lateinit var playButton: Button

    @FXML
    lateinit var rootPane: AnchorPane

    @FXML
    private lateinit var exitButton: Button

    @FXML
    fun handleSettingsButton() {
        sceneManager.changeScene("settingsScreen.fxml", exitButton.scene)
    }

    @FXML
    fun handleStartButton() {
        sceneManager.changeScene("gameScreen.fxml", exitButton.scene)
    }

    @FXML
    fun handleExitButton() {
        sceneManager.exit(exitButton.scene)
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {}
}
