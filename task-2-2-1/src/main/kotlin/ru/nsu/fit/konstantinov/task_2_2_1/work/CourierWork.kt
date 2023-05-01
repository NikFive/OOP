package ru.nsu.fit.konstantinov.task_2_2_1.work

import ru.nsu.fit.konstantinov.task_2_2_1.persons.Courier
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CourierWork(private val couriers: List<Courier>) : Work {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(couriers.size)

    override fun stopWork() {
        executorService.shutdownNow()
    }

    override fun run() {
        couriers.forEach(executorService::execute)
    }
}
