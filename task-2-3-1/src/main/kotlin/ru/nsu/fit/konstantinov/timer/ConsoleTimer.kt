package ru.nsu.fit.konstantinov.timer

class ConsoleTimer(private val thread: Thread) : Timer {
    override fun start() {
        thread.start()
    }

    override fun stop() {
        thread.interrupt()
    }
}