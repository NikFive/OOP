package ru.nsu.fit.konstantinov.task_2_3_1.model

import ru.nsu.fit.konstantinov.task_2_3_1.config.BLOCK_SIZE
import ru.nsu.fit.konstantinov.task_2_3_1.config.HEIGHT
import ru.nsu.fit.konstantinov.task_2_3_1.config.WIDTH
import java.util.*
import kotlin.math.roundToInt

class Food(var point: Point = Point(WIDTH / 2, HEIGHT / 2)) {
    fun foodRest() = run { point = Point(WIDTH / 2, HEIGHT / 2) }

    fun touchFood(snake: Snake) = snake.headLocation == point

    fun addFood() {
        val random = Random()

        var y = (random.nextInt(HEIGHT).toDouble() / BLOCK_SIZE).roundToInt() * BLOCK_SIZE
        var x = (random.nextInt(WIDTH).toDouble() / BLOCK_SIZE).roundToInt() * BLOCK_SIZE

        if (y >= HEIGHT) {
            y -= BLOCK_SIZE
        }
        if (x >= WIDTH) {
            x -= BLOCK_SIZE
        }
        point = Point(x, y)
    }
}
