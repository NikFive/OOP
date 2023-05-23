package ru.nsu.fit.konstantinov.dto.fxml

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import ru.nsu.fit.konstantinov.dto.DefaultDto
import ru.nsu.fit.konstantinov.model.pixel.PixelType

open class FXMLDto(x: Int, y: Int, private val color: Color?) : DefaultDto(x, y) {
    private val pixelSize = 20
    open var pixelType: PixelType? = null
    open fun render(context: GraphicsContext) {
        context.fill = Color.rgb(233, 230, 239)
        context.fillRect(
            (x * pixelSize).toDouble(),
            (y * pixelSize).toDouble(),
            pixelSize.toDouble(),
            pixelSize.toDouble()
        )
        context.fill = color
        context.fillRect(
            (x * pixelSize).toDouble(),
            (y * pixelSize).toDouble(),
            pixelSize - 1.toDouble(),
            pixelSize - 1.toDouble()
        )
    }
}
