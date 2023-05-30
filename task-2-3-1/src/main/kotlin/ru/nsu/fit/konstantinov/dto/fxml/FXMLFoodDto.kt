package ru.nsu.fit.konstantinov.dto.fxml

import ru.nsu.fit.konstantinov.model.pixel.PixelType
import ru.nsu.fit.konstantinov.utils.GameConfiguration

class FXMLFoodDto(x: Int, y: Int) : FXMLDto(x, y, GameConfiguration.gameConfiguration.foodColor) {
    override var pixelType: PixelType? = PixelType.FOOD
}
