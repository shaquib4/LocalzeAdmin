package com.example.localzeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class SelectionActivityUsers : AppCompatActivity() {
    private lateinit var btnNotification: CardView
    private lateinit var btnOrders: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_users)
        btnNotification = findViewById(R.id.cardNotification)
        btnOrders = findViewById(R.id.OrdersCard)

        btnNotification.setOnClickListener {
            val uid = intent.getStringExtra("uid")
            val intent = Intent(this, SingleNotification::class.java)
            intent.putExtra("selected", "users")
            intent.putExtra("uid", uid)
            startActivity(intent)
        }
        btnOrders.setOnClickListener {
            val uid = intent.getStringExtra("uid")
            val intent = Intent(this, CustomerOrder::class.java)
            intent.putExtra("uid", uid)
            startActivity(intent)
        }
    }
}