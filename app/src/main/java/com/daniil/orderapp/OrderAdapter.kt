package com.daniil.orderapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniil.orderapp.databinding.ItemOrderBinding
import java.text.NumberFormat
import java.util.Locale

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private val orders = mutableListOf<FoodOrder>()

    fun submitOrders(newOrders: List<FoodOrder>) {
        orders.clear()
        orders.addAll(newOrders)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class OrderViewHolder(
        private val binding: ItemOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(order: FoodOrder) {
            binding.orderIdTextView.text = "Order #${order.id}"
            binding.customerNameTextView.text = order.customerName
            binding.statusTextView.text = order.status.name
            binding.statusTextView.setTextColor(mapStatusColor(order.status))
            binding.itemCountTextView.text = "${order.items.size} items"
            binding.totalTextView.text = formatPrice(order.totalCents)

            binding.priorityTextView.text = if (order.isPriority) {
                "Priority"
            } else {
                ""
            }
        }

        private fun formatPrice(totalCents: Int): String {
            val dollars = totalCents / 100.0
            return NumberFormat
                .getCurrencyInstance(Locale.CANADA)
                .format(dollars)
        }
    }
}