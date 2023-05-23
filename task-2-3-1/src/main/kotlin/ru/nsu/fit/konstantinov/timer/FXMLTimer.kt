package ru.nsu.fit.konstantinov.timer

import javafx.animation.AnimationTimer

class FXMLTimer(private val animationTimer: AnimationTimer) : Timer {
    override fun start() {
        animationTimer.start()
    }

    override fun stop() {
        animationTimer.stop()
    }
}