package ru.nsu.fit.konstantinov.model

import ru.nsu.fit.konstantinov.model.pixel.SnakePixel
import ru.nsu.fit.konstantinov.utils.Direction
import java.util.*

class Snake(x: Int, y: Int) {
    var body: Deque<SnakePixel> = ArrayDeque()
    var direction: Direction = Direction.RIGHT
    var headBlock: SnakePixel = SnakePixel(x, y)
    var tailBlock: SnakePixel = SnakePixel(x - 1, y)

    fun move() {
        tailBlock.y = headBlock.y
        tailBlock.x = headBlock.x
        direction.movePixel(headBlock, tailBlock)
        body.addFirst(headBlock)
        headBlock = tailBlock
        tailBlock = body.removeLast()!!
    }

    fun generateNewSnakeBlock(): SnakePixel {
        val newTail = direction.generatePixel(headBlock)
        body.addLast(tailBlock)
        tailBlock = newTail
        return newTail
    }

    companion object {
        fun generate(field: Wall, collisionValidator: CollisionValidator): Snake {
            var snake: Snake
            do {
                val random = Random()
                val x = random.nextInt(field.width - 2) + 1
                val y = random.nextInt(field.height - 2) + 1
                snake = Snake(x, y)
            } while (!collisionValidator.addPixel(snake.headBlock))
            return snake
        }
    }
}
