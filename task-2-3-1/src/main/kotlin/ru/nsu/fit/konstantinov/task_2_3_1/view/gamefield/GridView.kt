package ru.nsu.fit.konstantinov.task_2_3_1.view.gamefield

import javafx.scene.canvas.GraphicsContext
import ru.nsu.fit.konstantinov.task_2_3_1.config.*

fun gridView(context: GraphicsContext) {
    context.fill = BLOCK_COLOR
    context.fillRect(0.0, 0.0, WIDTH_DOUBLE, HEIGHT_DOUBLE)

    context.stroke = LINE_COLOR
    context.lineWidth = 0.5

    for (element in 0 until WIDTH step BLOCK_SIZE) {
        context.strokeLine(element.toDouble(), 0.0, element.toDouble(), element + HEIGHT_DOUBLE)
    }

    for (element in 0 until HEIGHT step BLOCK_SIZE) {
        context.strokeLine(0.0, element.toDouble(), element + HEIGHT_DOUBLE, element.toDouble())
    }
}
