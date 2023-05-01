package ru.nsu.fit.konstantinov.task_2_3_1

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage
import ru.nsu.fit.konstantinov.task_2_3_1.config.*
import ru.nsu.fit.konstantinov.task_2_3_1.model.Direction
import ru.nsu.fit.konstantinov.task_2_3_1.model.Food
import ru.nsu.fit.konstantinov.task_2_3_1.model.Snake
import ru.nsu.fit.konstantinov.task_2_3_1.view.gamefield.foodView
import ru.nsu.fit.konstantinov.task_2_3_1.view.gamefield.gridView
import ru.nsu.fit.konstantinov.task_2_3_1.view.gamefield.snakeView
import ru.nsu.fit.konstantinov.task_2_3_1.view.menu.endGameAlertView
import java.util.*

class Game : Application() {

    private lateinit var timerTask: TimerTask

    private lateinit var context: GraphicsContext

    lateinit var animationTimer: AnimationTimer

    private var paused = false

    var snake: Snake = Snake()

    val food: Food = Food()

    private var timer: Timer? = null

    var inProgress = false

    var gameOver = false

    override fun start(primaryStage: Stage?) {

        val canvas = Canvas(WIDTH_DOUBLE, HEIGHT_DOUBLE)
        context = canvas.graphicsContext2D

        val button = Button().apply {
            text = BUTTON_NAME
            minHeight = BUTTON_HEIGHT
            minWidth = BUTTON_WIDTH
            setOnAction {
                inProgress = true
                gameOver = false
                isVisible = false
                timer ?: apply {
                    timerTask = createTask()
                    timer = Timer()
                    timer?.scheduleAtFixedRate(timerTask, UPDATE_DELAY, UPDATE_PERIOD)
                    animationTimer.start()
                }
            }
        }

        val vBox = VBox().apply {
            prefWidthProperty().bind(canvas.widthProperty())
            prefHeightProperty().bind(canvas.heightProperty())
            alignment = Pos.CENTER
            children.add(button)
        }

        val root = Group().apply {
            children.addAll(canvas, vBox)
        }

        val myScene = Scene(root).apply {
            setOnKeyPressed {
                when (it.code) {
                    KeyCode.UP, KeyCode.W -> {
                        if (snake.direction != Direction.DOWN) {
                            snake.direction = Direction.UP
                        }
                    }

                    KeyCode.DOWN, KeyCode.S -> {
                        if (snake.direction != Direction.UP) {
                            snake.direction = Direction.DOWN
                        }
                    }

                    KeyCode.LEFT, KeyCode.A -> {
                        if (snake.direction != Direction.RIGHT) {
                            snake.direction = Direction.LEFT
                        }
                    }

                    KeyCode.RIGHT, KeyCode.D -> {
                        if (snake.direction != Direction.LEFT) {
                            snake.direction = Direction.RIGHT
                        }
                    }

                    else -> {
                        if (paused) {
                            timerTask = createTask()
                            timer = Timer()
                            timer?.scheduleAtFixedRate(timerTask, UPDATE_DELAY, UPDATE_PERIOD)
                            paused = false
                        } else {
                            timer?.cancel()
                            timer = null
                            paused = true
                        }
                    }
                }
            }
        }

        gridView(context)

        primaryStage?.apply {
            title = STAGE_NAME
            scene = myScene
        }?.show()

        animationTimer = object : AnimationTimer() {
            override fun handle(now: Long) {
                if (inProgress) {
                    gridView(context)
                    snakeView(snake.headLocation, snake.tail, context)
                    foodView(food.point, context)
                } else if (gameOver) {
                    animationTimer.stop()
                    endGameAlertView(text(snake.tail.size + 1), context, getTextWidth(text(snake.tail.size + 1)))
                    button.isVisible = true
                    food.foodRest()
                    snake = Snake()
                }
            }
        }
        animationTimer.start()
        timerTask = createTask()
        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, UPDATE_DELAY, UPDATE_PERIOD)
    }

    override fun stop() {
        timer?.cancel()
        timer = null
    }

    private fun endGame() {
        timer?.cancel()
        timer = null
        inProgress = false
        gameOver = true
    }

    private fun getTextWidth(string: String): Double {
        val text = Text(string)
        Scene(Group(text))
        text.applyCss()
        return text.layoutBounds.width
    }

    private fun createTask() = object : TimerTask() {
        override fun run() {
            if (inProgress) {
                snake.snakeUpdate()
                if (snake.isCollidedWithWall || snake.isTouchTheTail()) endGame()
                if (food.touchFood(snake)) {
                    snake.addTail()
                    food.addFood()
                }
            }
        }
    }
}
