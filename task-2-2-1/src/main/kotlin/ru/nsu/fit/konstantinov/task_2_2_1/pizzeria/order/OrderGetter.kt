package ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order

interface OrderGetter {
    fun getOrder(): Order
    fun isNoOrders(): Boolean
}
