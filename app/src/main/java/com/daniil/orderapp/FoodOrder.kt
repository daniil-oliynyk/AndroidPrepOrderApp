package com.daniil.orderapp

data class FoodOrder(
    val id: String,
    val customerName: String,
    val items: List<String>,
    var status: OrderStatus,
    val totalCents: Int,
    val createdAtMillis: Long,
    val isPriority: Boolean = false,
    var isExpanded: Boolean = false
)