package ru.nsu.fit.konstantinov.task_2_3_1.config

import javafx.scene.paint.Color

const val WIDTH = 800

const val HEIGHT = 800

const val WIDTH_DOUBLE = WIDTH.toDouble()

const val HEIGHT_DOUBLE = HEIGHT.toDouble()

const val BUTTON_WIDTH = 100.0

const val BUTTON_HEIGHT = 36.0

const val BLOCK_SIZE = 40

const val BLOCK_SIZE_DOUBLE = BLOCK_SIZE.toDouble()

const val UPDATE_PERIOD = 100L

const val UPDATE_DELAY = 100L

const val BUTTON_NAME = "Start"

const val STAGE_NAME = "Snake"

val SNAKE_COLOR = Color.rgb(122, 193, 218)!!

val BLOCK_COLOR = Color.rgb(245, 245, 245)!!

val FOOD_COLOR = Color.rgb(247, 163, 111)!!

val LINE_COLOR = Color.rgb(233, 230, 239)!!

val TEXT_COLOR = Color.BLACK!!

val text = { str: Int ->
    """Game over
 |Score:$str""".trimMargin()
}
