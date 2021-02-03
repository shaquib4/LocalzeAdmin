package com.example.localzeadmin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SellerCartOrderDetails : AppCompatActivity() {
    private lateinit var orderId: TextView
    private lateinit var orderDate: TextView
    private lateinit var orderStatus: TextView
    private lateinit var totalItems: TextView
    private lateinit var deliveryCharge: TextView
    private lateinit var totalAmount: TextView
    private lateinit var paymentStatus: TextView
    private lateinit var deliveryAddress: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_cart_order_details)
        orderId = findViewById(R.id.txtOrderId)
        orderDate = findViewById(R.id.txtOrderDate)
        orderStatus = findViewById(R.id.txtOrderStatus)
    }
}