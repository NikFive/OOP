package ru.nsu.fit.konstantinov.dto.fxml

import ru.nsu.fit.konstantinov.model.pixel.PixelType
import ru.nsu.fit.konstantinov.utils.GameConfiguration

class FXMLSnakeDto(x: Int, y: Int) : FXMLDto(x, y, GameConfiguration.gameConfiguration.snakeColor) {
    override var pixelType: PixelType? = PixelType.SNAKE
}
