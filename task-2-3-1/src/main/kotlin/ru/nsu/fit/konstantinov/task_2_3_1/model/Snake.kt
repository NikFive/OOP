package ru.nsu.fit.konstantinov.task_2_3_1.model

import ru.nsu.fit.konstantinov.task_2_3_1.config.BLOCK_SIZE
import ru.nsu.fit.konstantinov.task_2_3_1.config.HEIGHT
import ru.nsu.fit.konstantinov.task_2_3_1.config.WIDTH
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque

class Snake(
    var direction: Direction = Direction.RIGHT,
    val tail: Deque<Point> = ConcurrentLinkedDeque(),
    var isCollidedWithWall: Boolean = false,
    var headLocation: Point = Point(BLOCK_SIZE, BLOCK_SIZE)
) {
    fun snakeUpdate() {
        if (tail.isNotEmpty()) {
            tail.removeLast()
            tail.offerFirst(Point(headLocation.x, headLocation.y))
        }

        when (direction) {
            Direction.UP -> {
                headLocation = Point(headLocation.x, headLocation.y - BLOCK_SIZE)
                if (headLocation.y < 0) {
                    isCollidedWithWall = true
                    headLocation = Point(headLocation.x, 0)
                }
            }

            Direction.RIGHT -> {
                headLocation = Point(headLocation.x + BLOCK_SIZE, headLocation.y)
                if (headLocation.x >= WIDTH) {
                    isCollidedWithWall = true
                    headLocation = Point(WIDTH - BLOCK_SIZE, headLocation.y)
                }
            }

            Direction.LEFT -> {
                headLocation = Point(headLocation.x - BLOCK_SIZE, headLocation.y)
                if (headLocation.x < 0) {
                    isCollidedWithWall = true
                    headLocation = Point(0, headLocation.y)
                }
            }

            Direction.DOWN -> {
                headLocation = Point(headLocation.x, headLocation.y + BLOCK_SIZE)
                if (headLocation.y >= HEIGHT) {
                    isCollidedWithWall = true
                    headLocation = Point(headLocation.x, HEIGHT - BLOCK_SIZE)
                }
            }
        }
    }

    fun isTouchTheTail() = tail.contains(headLocation)

    fun addTail() = tail.offerFirst(Point(headLocation.x, headLocation.y))
}
