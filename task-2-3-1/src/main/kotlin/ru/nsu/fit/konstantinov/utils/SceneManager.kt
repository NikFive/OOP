package ru.nsu.fit.konstantinov.utils

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class SceneManager {
    fun changeScene(fxmlPath: String?, scene: Scene) {
        val newRoot = FXMLLoader(javaClass.classLoader.getResource(fxmlPath)).load<Parent>()
        val stage = scene.window as Stage
        stage.scene = Scene(newRoot)
    }

    fun exit(scene: Scene) {
        val stage = scene.window as Stage
        stage.close()
    }
}