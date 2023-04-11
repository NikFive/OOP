package ru.nsu.fit.konstantinov.task_2_3_1

import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque
import kotlin.math.roundToInt


data class Point(val x: Int, val y: Int)


data class Food(var point: Point = Point(WIDTH / 2, HEIGHT / 2))


enum class Direction {
    UP, DOWN, LEFT, RIGHT;
}

data class Snake(
    var direction: Direction = Direction.RIGHT,
    val tail: Deque<Point> = ConcurrentLinkedDeque(),
    var isCollidedWithWall: Boolean = false,
    var headLocation: Point = Point(BLOCK_SIZE, BLOCK_SIZE)
)


fun Food.foodRest() = run { point = Point(WIDTH / 2, HEIGHT / 2) }


fun Food.touchFood(snake: Snake) = snake.headLocation == point

fun Food.addFood() {
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

fun Snake.snakeUpdate() {
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

fun Snake.isTouchTheTail() = tail.contains(headLocation)

fun Snake.addTail() = tail.offerFirst(Point(headLocation.x, headLocation.y))
