package ru.nsu.fit.konstantinov.model

import ru.nsu.fit.konstantinov.model.pixel.DefaultPixel
import ru.nsu.fit.konstantinov.model.pixel.PixelType
import java.util.*

class Food(x: Int, y: Int) : DefaultPixel(x, y, PixelType.FOOD) {

    fun getNewCoordinates(field: Wall, collisionValidator: CollisionValidator?) {
        do {
            val random = Random()
            this.x = random.nextInt(field.width - 2) + 1
            this.y = random.nextInt(field.height - 2) + 1
        } while (!collisionValidator!!.addPixel(this))
    }

    companion object {
        fun generate(field: Wall, collisionValidator: CollisionValidator): Food {
            val food = Food(0, 0)
            do {
                val random = Random()
                food.x = random.nextInt(field.width - 2) + 1
                food.y = random.nextInt(field.height - 2) + 1
            } while (!collisionValidator.addPixel(food))
            return food
        }
    }
}

