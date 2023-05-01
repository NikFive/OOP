package ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order

interface OrderGetter {
    val order: Order
    val isNoOrders: Boolean
}
