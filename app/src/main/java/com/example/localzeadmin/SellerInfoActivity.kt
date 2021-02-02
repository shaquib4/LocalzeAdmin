package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Adapters.AdapterSellerInfo
import com.example.localzeadmin.Modals.ModalSellerInfo
import com.google.firebase.database.*

class SellerInfoActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var recyclerInfoSeller: RecyclerView
    private lateinit var sellerInfoList: List<ModalSellerInfo>
    private lateinit var adapterSellerInfo: AdapterSellerInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_info)
        recyclerInfoSeller = findViewById(R.id.recycler_seller_info)
        recyclerInfoSeller.layoutManager = LinearLayoutManager(this)
        sellerInfoList = ArrayList<ModalSellerInfo>()
        databaseReference = FirebaseDatabase.getInstance().reference.child("seller")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (sellerInfoList as ArrayList<ModalSellerInfo>).clear()
                for (i in snapshot.children) {
                    val obj = ModalSellerInfo(
                        i.child("shop_name").value.toString(),
                        i.child("name").value.toString(),
                        i.child("phone").value.toString(),
                        i.child("address").value.toString()
                    )
                    (sellerInfoList as ArrayList<ModalSellerInfo>).add(obj)
                }
                adapterSellerInfo = AdapterSellerInfo(this@SellerInfoActivity, sellerInfoList)
                recyclerInfoSeller.adapter = adapterSellerInfo
            }
        })
    }
}