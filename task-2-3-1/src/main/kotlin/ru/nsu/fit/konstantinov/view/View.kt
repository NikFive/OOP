package ru.nsu.fit.konstantinov.view

import ru.nsu.fit.konstantinov.timer.Timer
import ru.nsu.fit.konstantinov.utils.SceneManager

interface View {
    fun endGame()

    fun clearScreen()

    fun render()

    fun setTimer(gameStep: Runnable): Timer

    fun pause()

    var sceneManager: SceneManager
}