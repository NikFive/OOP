package ru.nsu.fit.konstantinov.dto.fxml

import javafx.scene.paint.Color
import ru.nsu.fit.konstantinov.model.pixel.PixelType

class FXMLFoodDto(x: Int, y: Int) : FXMLDto(x, y, Color.rgb(247, 163, 111)) {
    override var pixelType: PixelType? = PixelType.FOOD
}
