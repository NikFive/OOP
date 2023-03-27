package ru.nsu.fit.konstantinov.task_2_2_1.work

import ru.nsu.fit.konstantinov.task_2_2_1.employees.Employees
import ru.nsu.fit.konstantinov.task_2_2_1.models.FutureSubjectPair
import java.util.concurrent.Executors
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class BakerWork {
    val lock: Lock
    private val bakersAndPizzas: MutableList<FutureSubjectPair>

    init {
        bakersAndPizzas = ArrayList()
        lock = ReentrantLock(true)
    }

    fun getBakersAndPizzas(): List<FutureSubjectPair> = bakersAndPizzas
    fun run(employees: Employees, pizzeriaWork: PizzeriaWork) {
        pizzeriaWork.setNumberOfBakers(employees.numberOfBakers)
        val executorService = Executors.newFixedThreadPool(employees.numberOfBakers)
        for (baker in employees.getBakers()) {
            baker.setBakers(this)
            bakersAndPizzas.add(FutureSubjectPair(baker, executorService.submit(baker)))
        }
    }
}
