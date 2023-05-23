package ru.nsu.fit.konstantinov.task_2_2_1

import org.junit.jupiter.api.Test
import ru.nsu.fit.konstantinov.task_2_2_1.models.BakerModel
import ru.nsu.fit.konstantinov.task_2_2_1.models.CourierModel
import ru.nsu.fit.konstantinov.task_2_2_1.persons.Baker
import ru.nsu.fit.konstantinov.task_2_2_1.persons.Courier
import ru.nsu.fit.konstantinov.task_2_2_1.persons.Customer
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.Pizzeria
import ru.nsu.fit.konstantinov.task_2_2_1.utils.ConfigParser.Companion.getConfigModelFromFile
import ru.nsu.fit.konstantinov.task_2_2_1.work.CustomerWork
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class PizzeriaTest {
    @Test
    fun testConfigParser() {
        val configModel =
            getConfigModelFromFile("src/test/resources/ru.nsu.fit.konstantinov.task_2_2_1/PizzeriaTestConfig.json")
        assertEquals(configModel.bakers.size, 3)
        assertEquals(configModel.bakers[0].cookingTime, 1000)
        assertEquals(configModel.bakers[1].cookingTime, 1001)
        assertEquals(configModel.bakers[2].cookingTime, 1000)

        assertEquals(configModel.couriers.size, 3)
        assertEquals(configModel.couriers[0].deliveryTime, 1000)
        assertEquals(configModel.couriers[0].bagCapacity, 1)
        assertEquals(configModel.couriers[1].deliveryTime, 1001)
        assertEquals(configModel.couriers[1].bagCapacity, 2)
        assertEquals(configModel.couriers[2].deliveryTime, 1000)
        assertEquals(configModel.couriers[2].bagCapacity, 1)

        assertEquals(configModel.warehouse.capacity, 1)
    }
    @Test
    fun testOrderSuccess() {
        val configModel =
            getConfigModelFromFile("src/test/resources/ru.nsu.fit.konstantinov.task_2_2_1/PizzeriaTestConfig.json")
        val pizzeria = Pizzeria(configModel)

        val bakerModel: BakerModel = configModel.bakers[0]
        val courierModel: CourierModel = configModel.couriers[0]
        val warehouse = pizzeria.warehouse

        pizzeria.createOrder(4)
        val bakerThread = Thread(Baker(warehouse, pizzeria, bakerModel.cookingTime))
        bakerThread.start()
        assertFalse(warehouse.isFull())

        Thread.sleep((bakerModel.cookingTime + 100).toLong())
        assertTrue(pizzeria.isNoOrders())
        assertTrue(warehouse.isFull())

        val courierThread = Thread(Courier(warehouse, courierModel.bagCapacity, courierModel.deliveryTime))
        courierThread.start()
        Thread.sleep(courierModel.deliveryTime.toLong())

        assertTrue(bakerThread.isAlive)
        assertTrue(courierThread.isAlive)

        bakerThread.interrupt()
        courierThread.interrupt()
        Thread.sleep(50)
        assertFalse(bakerThread.isAlive)
        assertFalse(courierThread.isAlive)
    }

    @Test
    fun testCustomerWork() {
        val pizzeria =
            Pizzeria(getConfigModelFromFile("src/test/resources/ru.nsu.fit.konstantinov.task_2_2_1/PizzeriaTestConfig.json"))

        assertTrue(pizzeria.isNoOrders())
        val customerWork = CustomerWork(pizzeria)
        val customerWorkThread = Thread(customerWork)
        customerWorkThread.start()
        Thread.sleep(100)
        assertFalse(pizzeria.isNoOrders())
        customerWork.stopWork()
        while (!pizzeria.isNoOrders()) {
            pizzeria.getOrder()
        }
        Thread.sleep(300)
        assertTrue(pizzeria.isNoOrders())
        println(customerWorkThread.isAlive)

        pizzeria.forceClosing()
    }

    @Test
    fun testWork() {
        val pizzeria =
            Pizzeria(getConfigModelFromFile("src/test/resources/ru.nsu.fit.konstantinov.task_2_2_1/PizzeriaTestConfig.json"))

        val customerThread = Thread(Customer(pizzeria, 1))
        customerThread.start()
        Thread.sleep(100)
        assertFalse(pizzeria.isNoOrders())
        customerThread.interrupt()

        val bakerWork = pizzeria.bakerWork
        val bakerWorkThread = Thread(bakerWork)
        bakerWorkThread.start()
        Thread.sleep(10000)
        assertTrue(pizzeria.isNoOrders())

        bakerWork.stopWork()
        Thread.sleep(100)
        assertFalse(bakerWorkThread.isAlive)

        val courierWork = pizzeria.courierWork
        val courierWorkThread = Thread(courierWork)
        courierWorkThread.start()
        assertFalse(pizzeria.warehouse.isEmpty())
        Thread.sleep(500)
        assertTrue(pizzeria.warehouse.isEmpty())

        courierWork.stopWork()
        Thread.sleep(100)
        assertFalse(courierWorkThread.isAlive)

        pizzeria.gracefulClosing()
    }
}
