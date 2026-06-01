package com.daniil.orderapp

object FakeOrderRepository {

    fun getOrders(): List<FoodOrder> {
        return listOf(
            FoodOrder(
                id = "1001",
                customerName = "Sarah M.",
                items = listOf("Burger", "Fries", "Coke"),
                status = OrderStatus.PENDING,
                totalCents = 2450,
                createdAtMillis = System.currentTimeMillis() - 1000 * 60 * 5,
                isPriority = false
            ),
            FoodOrder(
                id = "1002",
                customerName = "Michael T.",
                items = listOf("Chicken Bowl", "Water"),
                status = OrderStatus.ACCEPTED,
                totalCents = 1899,
                createdAtMillis = System.currentTimeMillis() - 1000 * 60 * 12,
                isPriority = true
            ),
            FoodOrder(
                id = "1003",
                customerName = "Jenny R.",
                items = listOf("Pizza", "Garlic Dip"),
                status = OrderStatus.PREPARING,
                totalCents = 3199,
                createdAtMillis = System.currentTimeMillis() - 1000 * 60 * 20,
                isPriority = false
            ),
            FoodOrder(
                id = "1004",
                customerName = "David K.",
                items = listOf("Sushi Combo", "Miso Soup", "Green Tea"),
                status = OrderStatus.READY,
                totalCents = 4275,
                createdAtMillis = System.currentTimeMillis() - 1000 * 60 * 30,
                isPriority = false
            ),
            FoodOrder(
                id = "1005",
                customerName = "Alyssa P.",
                items = listOf("Pasta", "Caesar Salad"),
                status = OrderStatus.DELIVERED,
                totalCents = 2850,
                createdAtMillis = System.currentTimeMillis() - 1000 * 60 * 50,
                isPriority = true
            )
        )
    }
}