package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class SellerInfoActivity : AppCompatActivity() {
    private lateinit var shopName: TextView
    private lateinit var sellerName: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var shopAddress: TextView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var recyclerInfoSeller: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_info)
        shopName = findViewById(R.id.shopName)
        sellerName = findViewById(R.id.sellerName)
        phoneNumber = findViewById(R.id.phoneNumber)
        shopAddress = findViewById(R.id.sellerAddress)
        recyclerInfoSeller = findViewById(R.id.recycler_seller_info)
        recyclerInfoSeller.layoutManager = LinearLayoutManager(this)
        databaseReference = FirebaseDatabase.getInstance().reference.child("seller")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

            }

        })
    }
}