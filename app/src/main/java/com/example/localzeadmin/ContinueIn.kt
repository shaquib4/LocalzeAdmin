package com.example.localzeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar

class ContinueIn : AppCompatActivity() {
    private lateinit var progress:ProgressBar
    private lateinit var btnCustomer:Button
    private lateinit var btnSeller:Button
    private lateinit var btnNotification:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue_in)
        progress=findViewById(R.id.progress_continue)
        btnNotification=findViewById(R.id.notification)
        btnCustomer=findViewById(R.id.btnCustomer)
        btnSeller=findViewById(R.id.btnseller)
        progress.visibility= View.GONE
        btnCustomer.setOnClickListener {
            progress.visibility=View.VISIBLE
           startActivity(Intent(this,UserInfo::class.java))
            progress.visibility= View.GONE
        }

        btnSeller.setOnClickListener {
            progress.visibility=View.VISIBLE
         startActivity(Intent(this,SellerInfoActivity::class.java))
            progress.visibility= View.GONE
        }

        btnNotification.setOnClickListener {
            startActivity(Intent(this,ActivityNotification::class.java))
        }
    }
}