package ru.nsu.fit.konstantinov.task_2_3_1.view.menu

import javafx.scene.canvas.GraphicsContext
import ru.nsu.fit.konstantinov.task_2_3_1.config.HEIGHT
import ru.nsu.fit.konstantinov.task_2_3_1.config.TEXT_COLOR
import ru.nsu.fit.konstantinov.task_2_3_1.config.WIDTH

fun endGameAlertView(text: String, context: GraphicsContext, textWidth: Double) {
    context.fill = TEXT_COLOR
    context.fillText(text, (WIDTH / 2) - (textWidth / 2), HEIGHT / 2 - 48.toDouble())
}
