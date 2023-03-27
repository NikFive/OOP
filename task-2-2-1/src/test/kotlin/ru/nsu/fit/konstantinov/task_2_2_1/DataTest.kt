package ru.nsu.fit.konstantinov.task_2_2_1


import org.junit.jupiter.api.Test
import ru.nsu.fit.konstantinov.task_2_2_1.employees.Baker
import ru.nsu.fit.konstantinov.task_2_2_1.employees.Employees
import ru.nsu.fit.konstantinov.task_2_2_1.models.BakerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.models.DeliveryWorkerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.models.Order
import ru.nsu.fit.konstantinov.task_2_2_1.utils.JSONReader
import ru.nsu.fit.konstantinov.task_2_2_1.work.BakerWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.DeliveryWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.PizzeriaWork
import java.io.File
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class DataTest {
    @Test
    fun correctlyReadData() {
        val jsonReader = JSONReader()
        val deliveryFile = File("src/test/kotlin/ru/nsu/fit/konstantinov/task_2_2_1/deliveryWorkers")
        val bakerFile = File("src/test/kotlin/ru/nsu/fit/konstantinov/task_2_2_1/bakers")
        val bakersConfig = jsonReader.readBakers(bakerFile)
        for (i in 0..2) {
            assertEquals(i + 1, bakersConfig[i].id)
            assertEquals(10000, bakersConfig[i].cookingTime)
        }
        val deliveryWorkerConfigs = jsonReader.readDeliveryWorkers(deliveryFile)
        assertEquals(1, deliveryWorkerConfigs[0].id)
        assertEquals(2, deliveryWorkerConfigs[1].id)
        assertEquals(3, deliveryWorkerConfigs[2].id)
        assertEquals(5000, deliveryWorkerConfigs[0].deliveryTime)
        assertEquals(6000, deliveryWorkerConfigs[1].deliveryTime)
        assertEquals(5000, deliveryWorkerConfigs[2].deliveryTime)
        assertEquals(3, deliveryWorkerConfigs[0].capacity)
        assertEquals(3, deliveryWorkerConfigs[1].capacity)
        assertEquals(3, deliveryWorkerConfigs[2].capacity)
    }

    @Test
    fun correctlyConstructedClasses() {
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
        val employees = Employees(
            bakerConfigs,
            deliveryWorkerConfigs,
            ArrayBlockingQueue(9),
            LinkedBlockingQueue(),
            PizzeriaWork()
        )
        assertEquals(3, employees.numberOfBakers)
        assertEquals(1, employees.getBakers()[0].id)
        assertEquals(2, employees.getBakers()[1].id)
        assertEquals(10000, employees.getBakers()[0].cookingTime)
        assertEquals(10000, employees.getBakers()[2].cookingTime)
        assertEquals(2, employees.getDeliveryWorkers()[1].id)
        assertEquals(3, employees.getDeliveryWorkers()[2].id)
        assertEquals(6000, employees.getDeliveryWorkers()[1].deliveryTime)
        assertEquals(5000, employees.getDeliveryWorkers()[2].deliveryTime)
        assertEquals(3, employees.getDeliveryWorkers()[0].capacity)
        assertEquals(3, employees.getDeliveryWorkers()[2].capacity)
    }

    @Test
    fun correctlyReadAndInterpretedData() {
        val itemsInStorage = ArrayBlockingQueue<Order>(9)
        val pizzeriaWork = PizzeriaWork()
        val waitingOrders = LinkedBlockingQueue<Order>()
        val deliveryWorkers = DeliveryWork()
        val bakers = BakerWork()
        var numberOfBakers = 0
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
        val employees = Employees(
            bakerConfigs, deliveryWorkerConfigs, itemsInStorage, waitingOrders, pizzeriaWork
        )
        deliveryWorkers.run(employees, pizzeriaWork)
        bakers.run(employees, pizzeriaWork)
        var baker: Baker
        for (a in bakers.getBakersAndPizzas()) {
            numberOfBakers++
            baker = a.subject as Baker
            assertTrue(baker.id >= 1)
        }
        assertEquals(3, numberOfBakers)
        assertEquals(3, deliveryWorkers.getOrders().size)
    }
}
