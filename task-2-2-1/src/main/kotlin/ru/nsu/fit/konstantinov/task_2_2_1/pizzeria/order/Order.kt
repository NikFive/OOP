package ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order

class Order (val id: Int, val pizzaCount: Int) {
    var pizza: Pizza? = null
    var customer: Runnable? = null
}
class Pizza
