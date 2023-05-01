package ru.nsu.fit.konstantinov.task_2_2_1.work

import ru.nsu.fit.konstantinov.task_2_2_1.persons.Baker
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class BakerWork(private val bakers: List<Baker>) : Work {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(bakers.size)

    override fun stopWork() {
        executorService.shutdownNow()
    }

    override fun run() {
        bakers.forEach(executorService::execute)
    }
}
