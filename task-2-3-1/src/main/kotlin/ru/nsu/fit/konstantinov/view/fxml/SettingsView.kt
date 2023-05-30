package ru.nsu.fit.konstantinov.view.fxml

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.AnchorPane
import ru.nsu.fit.konstantinov.utils.GameConfiguration
import ru.nsu.fit.konstantinov.utils.SceneManager
import ru.nsu.fit.konstantinov.utils.themes.DarkTheme
import ru.nsu.fit.konstantinov.utils.themes.LightTheme


class SettingsView {
    private val sceneManager = SceneManager()

    @FXML
    lateinit var difficulty: ToggleGroup

    @FXML
    lateinit var radioButton1: RadioButton

    @FXML
    lateinit var radioButton2: RadioButton

    @FXML
    lateinit var radioButton3: RadioButton

    @FXML
    lateinit var saveButton: Button

    @FXML
    lateinit var backButton: Button

    @FXML
    lateinit var settingsPane: AnchorPane

    @FXML
    lateinit var colorChoice: ChoiceBox<String>

    @FXML
    fun handleSaveButton() {
        val value: Double = if (radioButton1.isSelected) {
            1.0
        } else if (radioButton2.isSelected) {
            2.0
        } else {
            3.0
        }
        GameConfiguration.gameConfiguration.gameSpeed = value
        val colorString = colorChoice.value
        if (colorString == "Light") {
            GameConfiguration.gameConfiguration.apply {
                snakeColor = LightTheme.snakeColor
                foodColor = LightTheme.foodColor
                wallColor = LightTheme.wallColor
                backgroundColor = LightTheme.backgroundColor
            }
        } else {
            GameConfiguration.gameConfiguration.apply {
                snakeColor = DarkTheme.snakeColor
                foodColor = DarkTheme.foodColor
                wallColor = DarkTheme.wallColor
                backgroundColor = DarkTheme.backgroundColor
            }
        }
    }

    @FXML
    fun handleBackButton() {
        sceneManager.changeScene("mainScreen.fxml", radioButton1.scene)
    }
}
