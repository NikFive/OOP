package ru.nsu.fit.konstantinov.view.fxml

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import ru.nsu.fit.konstantinov.utils.GameConfiguration
import ru.nsu.fit.konstantinov.utils.SceneManager


class SettingsView {
    private val sceneManager = SceneManager()

    private val stringToTheme: MutableMap<String, Color> = HashMap<String, Color>().apply {
        this["Light"] = Color.WHITE
        this["Dark"] = Color.BLACK
    }

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
        val value: Double
        if (radioButton1.isSelected) {
            value = 1.0
        } else if (radioButton2.isSelected) {
            value = 2.0
        } else {
            value = 3.0
        }
        GameConfiguration.gameConfiguration.gameSpeed = value
        val colorString = colorChoice.value
        val color = stringToTheme[colorString]
        GameConfiguration.gameConfiguration.snakeColor = color!!
    }

    @FXML
    fun handleBackButton() {
        sceneManager.changeScene("mainScreen.fxml", radioButton1.scene)
    }
}
