package ru.nsu.fit.konstantinov.presenter

import com.googlecode.lanterna.input.KeyStroke
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import ru.nsu.fit.konstantinov.converter.Converter
import ru.nsu.fit.konstantinov.dto.DefaultDto
import ru.nsu.fit.konstantinov.model.*
import ru.nsu.fit.konstantinov.model.pixel.PixelType
import ru.nsu.fit.konstantinov.model.pixel.SnakePixel
import ru.nsu.fit.konstantinov.model.pixel.WallPixel
import ru.nsu.fit.konstantinov.timer.Timer
import ru.nsu.fit.konstantinov.utils.Direction
import ru.nsu.fit.konstantinov.view.View

open class DefaultPresenter<T : DefaultDto>(val view: View) {
    private val fieldEnvironment: FieldEnvironment = FieldEnvironment()
    private val snake: Snake = fieldEnvironment.snake
    private val food: List<Food> = fieldEnvironment.food
    private val field: Wall = fieldEnvironment.field
    private val collisionValidator: CollisionValidator = fieldEnvironment.collisionValidator
    private val timer: Timer = view.setTimer { makeGameStep() }

    private fun makeGameStep() {
        collisionValidator.removePixel(snake.tailBlock)
        collisionValidator.addPixel(snake.headBlock)
        snake.move()
        val collisionElement = collisionValidator.validateCollision(snake.headBlock)
        when (collisionElement.pixelType) {
            PixelType.FOOD -> {
                val food = collisionElement as Food
                collisionValidator.removePixel(food)
                food.getNewCoordinates(field, collisionValidator)
                collisionValidator.addPixel(snake.generateNewSnakeBlock())
            }

            PixelType.SNAKE, PixelType.WALL -> {
                view.endGame()
                timer.stop()
                return
            }

            PixelType.EMPTY -> {}
        }
        view.clearScreen()
        view.render()
    }

    fun start() {
        timer.start()
    }

    fun <T : DefaultDto> getDtoList(converter: Converter<T>): List<T> = ArrayList<T>().apply {
        this.add(converter.convert(snake.headBlock))
        this.addAll(snake.body.stream().map { element: SnakePixel -> converter.convert(element) }.toList())
        this.add(converter.convert(snake.tailBlock))
        this.addAll(food.stream().map { element: Food -> converter.convert(element) }.toList())
        this.addAll(field.walls.stream().map { element: WallPixel -> converter.convert(element) }.toList())
    }

    fun handleKey(event: KeyEvent) {
        when (event.code) {
            KeyCode.RIGHT, KeyCode.D -> snake.direction = Direction.RIGHT
            KeyCode.LEFT, KeyCode.A -> snake.direction = Direction.LEFT
            KeyCode.UP, KeyCode.W -> snake.direction = Direction.UP
            KeyCode.DOWN, KeyCode.S -> snake.direction = Direction.DOWN
            KeyCode.SPACE -> {
                view.pause()
                timer.stop()
            }

            else -> {}
        }
    }

    fun handleKey(stroke: KeyStroke) {
        when (stroke.character) {
            'W', 'w' -> snake.direction = Direction.UP
            'D', 'd' -> snake.direction = Direction.RIGHT
            'S', 's' -> snake.direction = Direction.DOWN
            'A', 'a' -> snake.direction = Direction.LEFT
            else -> {}
        }
    }
}
