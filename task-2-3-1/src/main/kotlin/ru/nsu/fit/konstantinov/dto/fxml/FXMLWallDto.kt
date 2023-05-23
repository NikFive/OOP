package ru.nsu.fit.konstantinov.dto.fxml

import javafx.scene.paint.Color
import ru.nsu.fit.konstantinov.model.pixel.PixelType

class FXMLWallDto(x: Int, y: Int) : FXMLDto(x, y, Color.GREY) {
    override var pixelType: PixelType? = PixelType.WALL
}
