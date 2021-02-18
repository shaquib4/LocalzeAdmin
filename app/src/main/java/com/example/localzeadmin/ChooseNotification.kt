package com.example.localzeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class ChooseNotification : AppCompatActivity() {
    private lateinit var simple:CardView
    private lateinit var custom:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_notification)
        simple=findViewById(R.id.CardSimple)
        custom=findViewById(R.id.cardNotificationChoose)
        simple.setOnClickListener {
            startActivity(Intent(this,ActivityNotification::class.java))
        }
        custom.setOnClickListener {
            startActivity(Intent(this,CustomNotificationActivity::class.java))
        }
    }
}