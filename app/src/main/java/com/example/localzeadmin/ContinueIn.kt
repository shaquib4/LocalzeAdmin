package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ContinueIn : AppCompatActivity() {
    private lateinit var btnCustomer:Button
    private lateinit var btnSeller:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue_in)

        btnCustomer=findViewById(R.id.btnCustomer)
        btnSeller=findViewById(R.id.btnseller)

        btnCustomer.setOnClickListener {

        }

        btnSeller.setOnClickListener {

        }
    }
}