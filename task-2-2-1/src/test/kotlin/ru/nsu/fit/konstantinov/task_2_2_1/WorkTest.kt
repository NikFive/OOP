package ru.nsu.fit.konstantinov.task_2_2_1

import org.junit.jupiter.api.Test
import ru.nsu.fit.konstantinov.task_2_2_1.models.BakerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.models.DeliveryWorkerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.employees.Baker
import ru.nsu.fit.konstantinov.task_2_2_1.employees.DeliveryWorker
import ru.nsu.fit.konstantinov.task_2_2_1.employees.Employees
import ru.nsu.fit.konstantinov.task_2_2_1.models.Order
import ru.nsu.fit.konstantinov.task_2_2_1.work.BakerWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.DeliveryWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.PizzeriaWork
import java.io.File
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class WorkTest {
    @Test
    fun allDeliveryWorkersFinishedTheirWorkSuccessfully() {
        val bc1 = BakerConfig()
        bc1.cookingTime = 10000
        bc1.id = 1
        val bc2 = BakerConfig()
        bc2.cookingTime = 10000
        bc2.id = 2
        val bc3 = BakerConfig()
        bc3.cookingTime = 10000
        bc3.id = 3
        val bakerConfigs: MutableList<BakerConfig> = LinkedList()
        bakerConfigs.add(bc1)
        bakerConfigs.add(bc2)
        bakerConfigs.add(bc3)
        val dw1 = DeliveryWorkerConfig()
        dw1.capacity = 3
        dw1.deliveryTime = 5000
        dw1.id = 1
        val dw2 = DeliveryWorkerConfig()
        dw2.capacity = 3
        dw2.deliveryTime = 6000
        dw2.id = 2
        val dw3 = DeliveryWorkerConfig()
        dw3.capacity = 3
        dw3.deliveryTime = 5000
        dw3.id = 3
        val deliveryWorkerConfigs: MutableList<DeliveryWorkerConfig?> = LinkedList()
        deliveryWorkerConfigs.add(dw1)
        deliveryWorkerConfigs.add(dw2)
        deliveryWorkerConfigs.add(dw3)
        val deliveryWorkers = DeliveryWork()
        val pizzeriaWork = PizzeriaWork()
        val itemsInStorage = ArrayBlockingQueue<Order>(9)
        val employees = Employees(
            bakerConfigs,
            deliveryWorkerConfigs,
            itemsInStorage,
            LinkedBlockingQueue(),
            pizzeriaWork
        )
        assertEquals(3, employees.numberOfBakers)
        assertEquals(3, employees.numberOfDeliveryWorkers)
        val dwc1 = DeliveryWorkerConfig()
        dwc1.id = 1
        dwc1.deliveryTime = 5000
        dwc1.capacity = 3
        val dwc2 = DeliveryWorkerConfig()
        dwc2.id = 2
        dwc2.deliveryTime = 6000
        dwc1.capacity = 3
        val dwc3 = DeliveryWorkerConfig()
        dwc3.id = 3
        dwc3.deliveryTime = 5000
        dwc3.capacity = 3
        val deliveryWorker1 = DeliveryWorker(dwc1, pizzeriaWork, itemsInStorage)
        val deliveryWorker2 = DeliveryWorker(dwc2, pizzeriaWork, itemsInStorage)
        val deliveryWorker3 = DeliveryWorker(dwc3, pizzeriaWork, itemsInStorage)
        deliveryWorker1.setDeliveryWorkers(deliveryWorkers)
        deliveryWorker2.setDeliveryWorkers(deliveryWorkers)
        deliveryWorker3.setDeliveryWorkers(deliveryWorkers)
        pizzeriaWork.setNumberOfDeliveryWorkers(3)
        val thread1 = Thread(deliveryWorker1)
        val thread2 = Thread(deliveryWorker2)
        val thread3 = Thread(deliveryWorker3)
        thread1.start()
        thread2.start()
        thread3.start()
        pizzeriaWork.closePizzeria()
        try {
            thread1.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        try {
            thread2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        try {
            thread3.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        assertTrue(pizzeriaWork.areAllDeliveryWorkersFinishedWork())
    }

    @Test
    @Throws(InterruptedException::class)
    fun pizzeriaAndAllStaffFinishedTheirWorkSuccessfully() {
        val deliveryFile = File("src/test/kotlin/ru/nsu/fit/konstantinov/task_2_2_1/deliveryWorkers")
        val bakerFile = File("src/test/kotlin/ru/nsu/fit/konstantinov/task_2_2_1/bakers")
        val waitingOrders = LinkedBlockingQueue<Order>()
        val itemsInStorage = ArrayBlockingQueue<Order>(9)
        val pizzeria = Pizzeria(bakerFile, deliveryFile, itemsInStorage, waitingOrders)
        val pizzeriaOverview = pizzeria.start()
        assertTrue(pizzeriaOverview.areAllBakersFinishedWork())
        assertTrue(pizzeriaOverview.areAllDeliveryWorkersFinishedWork())
        assertTrue(pizzeriaOverview.isRestaurantClosed)
    }

    @Test
    @Throws(InterruptedException::class)
    fun allBakersFinishedTheirWorkSuccessfully() {
        val waitingOrders = LinkedBlockingQueue<Order>()
        val bakers = BakerWork()
        val pizzeriaWork = PizzeriaWork()
        val itemsInStorage = ArrayBlockingQueue<Order>(9)
        val bakerConfig1 = BakerConfig()
        bakerConfig1.id = 1
        bakerConfig1.cookingTime = 10000
        val bakerConfig2 = BakerConfig()
        bakerConfig2.id = 2
        bakerConfig2.cookingTime = 10000
        val bakerConfig3 = BakerConfig()
        bakerConfig3.id = 3
        val baker1 = Baker(bakerConfig1, waitingOrders, pizzeriaWork, itemsInStorage)
        val baker2 = Baker(bakerConfig2, waitingOrders, pizzeriaWork, itemsInStorage)
        val baker3 = Baker(bakerConfig3, waitingOrders, pizzeriaWork, itemsInStorage)
        baker1.setBakers(bakers)
        baker2.setBakers(bakers)
        baker3.setBakers(bakers)
        pizzeriaWork.setNumberOfBakers(3)
        val thread1 = Thread(baker1)
        val thread2 = Thread(baker2)
        val thread3 = Thread(baker3)
        thread1.start()
        thread2.start()
        thread3.start()
        waitingOrders.put(Order(1))
        waitingOrders.put(Order(2))
        waitingOrders.put(Order(3))
        waitingOrders.put(Order(4))
        Thread.sleep(3000)
        pizzeriaWork.closePizzeria()
        try {
            thread1.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        try {
            thread2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        try {
            thread3.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        assertTrue(pizzeriaWork.areAllBakersFinishedWork())
    }
}
