package ru.nsu.fit.konstantinov.utils

import com.google.gson.GsonBuilder
import javafx.scene.paint.Color
import ru.nsu.fit.konstantinov.utils.themes.LightTheme
import java.io.FileReader

class GameConfiguration {
    var consoleScreenWidth = 0
    var consoleScreenHeight = 0
    var foodCount = 0
    var pixelHeight = 0
    var pixelWidth = 0
    var extraWalls = 0

    @Transient
    var snakeColor: Color = LightTheme.snakeColor

    @Transient
    var wallColor: Color = LightTheme.wallColor

    @Transient
    var backgroundColor: Color = LightTheme.backgroundColor

    @Transient
    var foodColor: Color = LightTheme.foodColor

    @Transient
    var gameSpeed = 1.0

    companion object {
        var gameConfiguration: GameConfiguration = GsonBuilder().create()
            .fromJson(FileReader("task-2-3-1/src/main/resources/gameConfig.json"), GameConfiguration::class.java)
    }
}