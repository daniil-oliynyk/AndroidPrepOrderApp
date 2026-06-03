package com.daniil.orderapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniil.orderapp.databinding.ItemOrderBinding
import java.text.NumberFormat
import java.util.Locale

class OrderAdapter(
    private val onOrderClicked: (FoodOrder) -> Unit,
    private val onAdvanceOrderButtonClick: (FoodOrder) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private val orders = mutableListOf<FoodOrder>()
    private var expandableOrderId: String? = null


    fun setExpandableOrderId(orderId: String?) {
        expandableOrderId = orderId
        notifyDataSetChanged()
    }

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

        return OrderViewHolder(binding, onOrderClicked, onAdvanceOrderButtonClick)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        val isExpanded = order.id == expandableOrderId

        holder.bind(order, isExpanded)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class OrderViewHolder(
        private val binding: ItemOrderBinding,
        private val onOrderClicked: (FoodOrder) -> Unit,
        private val onAdvanceOrderButtonClick: (FoodOrder) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(order: FoodOrder, isExpanded: Boolean) {
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


            binding.itemsTextView.text = order.items.joinToString(separator = "\n") { item ->
                "• $item"
            }

            binding.orderDetailsContainer.visibility = if (isExpanded) android.view.View.VISIBLE else android.view.View.GONE
            binding.advanceOrderButton.isEnabled = if (order.status == OrderStatus.DELIVERED || order.status == OrderStatus.CANCELLED) false else true

            binding.root.setOnClickListener { onOrderClicked(order) }
            binding.advanceOrderButton.setOnClickListener { onAdvanceOrderButtonClick(order)}
        }

        private fun formatPrice(totalCents: Int): String {
            val dollars = totalCents / 100.0
            return NumberFormat
                .getCurrencyInstance(Locale.CANADA)
                .format(dollars)
        }
    }
}