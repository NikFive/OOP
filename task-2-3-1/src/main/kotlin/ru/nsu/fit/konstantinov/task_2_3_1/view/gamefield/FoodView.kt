package ru.nsu.fit.konstantinov.task_2_3_1.view.gamefield

import javafx.scene.canvas.GraphicsContext
import ru.nsu.fit.konstantinov.task_2_3_1.config.BLOCK_SIZE_DOUBLE
import ru.nsu.fit.konstantinov.task_2_3_1.config.FOOD_COLOR
import ru.nsu.fit.konstantinov.task_2_3_1.model.Point

fun foodView(foodPoint: Point, context: GraphicsContext) {
    context.fill = FOOD_COLOR
    context.fillRect(
        foodPoint.x.toDouble(),
        foodPoint.y.toDouble(),
        BLOCK_SIZE_DOUBLE,
        BLOCK_SIZE_DOUBLE
    )
}
