package ru.nsu.fit.konstantinov.task_2_3_1.view.gamefield

import javafx.scene.canvas.GraphicsContext
import ru.nsu.fit.konstantinov.task_2_3_1.config.BLOCK_SIZE_DOUBLE
import ru.nsu.fit.konstantinov.task_2_3_1.config.SNAKE_COLOR
import ru.nsu.fit.konstantinov.task_2_3_1.model.Point
import java.util.*

fun snakeView(snakeHeadLocation: Point, snakeTail: Deque<Point>, context: GraphicsContext) {
    context.fill = SNAKE_COLOR
    context.fillRect(
        snakeHeadLocation.x.toDouble(),
        snakeHeadLocation.y.toDouble(),
        BLOCK_SIZE_DOUBLE,
        BLOCK_SIZE_DOUBLE
    )
    snakeTail.forEach {
        context.fillRect(
            it.x.toDouble(),
            it.y.toDouble(),
            BLOCK_SIZE_DOUBLE,
            BLOCK_SIZE_DOUBLE
        )
    }
}
