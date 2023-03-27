package ru.nsu.fit.konstantinov.task_2_2_1.work

import java.util.concurrent.atomic.AtomicInteger

class PizzeriaWork {
    var currentOrderId = 0
    private var numOfBakers = 0
    private var numOfDeliveryWorkers = 0
    private var numOfBakersFinishedWork = 0
    private val numOfDeliveryWorkersFinishedWork: AtomicInteger = AtomicInteger(0)
    var isRestaurantClosed = false

    /**
     * All bakers finished their work.
     *
     * @return true if all bakers finished their work.
     */
    fun areAllBakersFinishedWork(): Boolean = numOfBakers == numOfBakersFinishedWork

    /**
     * All delivery workers finished their work.
     *
     * @return true if all delivery workers finished their work.
     */
    fun areAllDeliveryWorkersFinishedWork(): Boolean = numOfDeliveryWorkers == numOfDeliveryWorkersFinishedWork.get()

    /**
     * Updates order's id.
     */
    fun updateCurrentOrderId() {
        currentOrderId++
    }

    /**
     * Sets number of bakers.
     *
     * @param numOfBakers
     */
    fun setNumberOfBakers(numOfBakers: Int) {
        this.numOfBakers = numOfBakers
    }

    /**
     * Sets number of delivery workers.
     *
     * @param numOfDeliveryWorkers
     */
    fun setNumberOfDeliveryWorkers(numOfDeliveryWorkers: Int) {
        this.numOfDeliveryWorkers = numOfDeliveryWorkers
    }

    /**
     * Ends shift for baker.
     */
    fun endShiftForBaker() {
        numOfBakersFinishedWork++
    }

    /**
     * Ends shift for delivery worker.
     */
    fun endShiftForDeliveryWorker() {
        numOfDeliveryWorkersFinishedWork.incrementAndGet()
    }

    /**
     * Set status of pizzeria as closed.
     */
    fun closePizzeria() {
        isRestaurantClosed = true
    }
}
