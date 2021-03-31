package com.example.localzeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class SellerSectionActivity : AppCompatActivity() {
    private lateinit var cardStaff:CardView
    private lateinit var cardNotification:CardView
    private lateinit var cardOrders:CardView
    private lateinit var bankDetails:CardView
    private var shopId: String? = "200"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_section)
        cardStaff = findViewById(R.id.cardStaff)
        cardOrders = findViewById(R.id.cardOrders)
        cardNotification=findViewById(R.id.cardNotificationSeller)
        bankDetails=findViewById(R.id.cardBankSeller)
        shopId = intent.getStringExtra("shopId")
        cardOrders.setOnClickListener {
            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("shopId", shopId.toString())
            startActivity(intent)
            finish()
        }
        cardStaff.setOnClickListener {
            val intent = Intent(this, SellerStaffSection::class.java)
            intent.putExtra("shopId", shopId.toString())
            startActivity(intent)
            finish()
        }
        cardNotification.setOnClickListener {
            val intent=Intent(this,SingleNotification::class.java)
            intent.putExtra("selected","seller")
            intent.putExtra("uid",shopId.toString())
            startActivity(intent)
        }
        bankDetails.setOnClickListener {
            val intent=Intent(this,SellerBanksDetailSeller::class.java)
            intent.putExtra("shopId",shopId.toString())
            startActivity(intent)
        }
    }
}