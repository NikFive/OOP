package ru.nsu.fit.konstantinov.model

import ru.nsu.fit.konstantinov.model.pixel.WallPixel
import ru.nsu.fit.konstantinov.utils.GameConfiguration
import java.util.*

class FieldEnvironment {
    private val gameConfiguration: GameConfiguration = GameConfiguration.gameConfiguration
    val collisionValidator: CollisionValidator = CollisionValidator(
        gameConfiguration.pixelHeight,
        gameConfiguration.pixelWidth
    )
    val field: Wall = Wall(
        gameConfiguration.pixelWidth,
        gameConfiguration.pixelHeight
    ).apply {
        for (i in 0 until gameConfiguration.extraWalls) {
            this.addWalls(
                generateWall(
                    4,
                    gameConfiguration.pixelWidth,
                    gameConfiguration.pixelHeight
                )
            )
        }
        collisionValidator.addPixels(this.walls)
    }
    val snake: Snake = Snake.generate(field, collisionValidator).apply {
        collisionValidator.addPixel(this.headBlock)
    }
    val food: MutableList<Food> = ArrayList<Food>().apply {
        for (i in 0 until gameConfiguration.foodCount) {
            this.add(Food.generate(field, collisionValidator))
        }
        collisionValidator.addPixels(this.map { it })
    }

    private fun generateWall(maxSize: Int, boxHeight: Int, boxLength: Int): List<WallPixel> {
        val random = Random()
        val height = random.nextInt(boxHeight - maxSize)
        val length = random.nextInt(boxLength - maxSize)
        val walls: MutableList<WallPixel> = ArrayList<WallPixel>().apply {
            if (random.nextBoolean()) {
                for (i in height until height + random.nextInt(maxSize) + 2) {
                    this.add(WallPixel(i, length))
                }
            } else {
                for (i in length until length + random.nextInt(maxSize) + 2) {
                    this.add(WallPixel(height, i))
                }
            }
        }
        return walls
    }
}
