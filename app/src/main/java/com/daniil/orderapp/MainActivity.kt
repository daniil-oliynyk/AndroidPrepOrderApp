package com.daniil.orderapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniil.orderapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var orderAdapter: OrderAdapter

    private var expandedOrderId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadOrders()
    }

    private fun setupRecyclerView() {
        orderAdapter = OrderAdapter(onOrderClicked = { order -> handleOrderClicked(order) } , onAdvanceOrderButtonClick =  { order -> handleAdvanceOrderClick(order)})

        binding.ordersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = orderAdapter
        }
    }
    private fun handleAdvanceOrderClick(order: FoodOrder) {
        order.status = advanceOrderStatus(order.status)
        orderAdapter.notifyDataSetChanged()
    }
    private fun handleOrderClicked(order: FoodOrder) {
        expandedOrderId = if (expandedOrderId == order.id) {
            null
        } else {
            order.id
        }

        orderAdapter.setExpandableOrderId(expandedOrderId)
    }

    private fun loadOrders() {
        val orders = FakeOrderRepository.getOrders()
        orderAdapter.submitOrders(orders)
    }
}