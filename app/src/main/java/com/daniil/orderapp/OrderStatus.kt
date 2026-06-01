package com.daniil.orderapp

import android.graphics.Color
import androidx.core.view.insets.ColorProtection

enum class OrderStatus {
    PENDING,
    ACCEPTED,
    PREPARING,
    READY,
    DELIVERED,
    CANCELLED
}

fun mapStatusColor(orderStatus: OrderStatus): Int {

    return when(orderStatus) {
        OrderStatus.PENDING -> Color.YELLOW
        OrderStatus.ACCEPTED -> Color.BLUE
        OrderStatus.PREPARING -> Color.CYAN
        OrderStatus.READY -> Color.GREEN
        OrderStatus.DELIVERED -> Color.GREEN
        OrderStatus.CANCELLED -> Color.RED
    }


}