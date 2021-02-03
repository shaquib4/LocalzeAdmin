package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Adapters.AdapterSellerStaff
import com.example.localzeadmin.Modals.ModalSellerStaff
import com.google.firebase.database.*

class SellerStaffSection : AppCompatActivity() {
    private var shopId: String? = "400"
    private lateinit var recyclerStaff: RecyclerView
    private lateinit var adapterStaff: AdapterSellerStaff
    private lateinit var listStaff: List<ModalSellerStaff>
    private lateinit var staffDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_staff_section)
        shopId = intent.getStringExtra("shopId")
        recyclerStaff = findViewById(R.id.recyclerStaff)
        recyclerStaff.layoutManager = LinearLayoutManager(this)
        listStaff = ArrayList<ModalSellerStaff>()
        staffDatabase =
            FirebaseDatabase.getInstance().reference.child("seller").child(shopId.toString())
                .child("MyStaff")
        staffDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (listStaff as ArrayList<ModalSellerStaff>).clear()
                for (i in snapshot.children) {
                    val obj = ModalSellerStaff(
                        i.child("name").value.toString(),
                        i.child("phone").value.toString(),
                        i.child("access").value.toString()
                    )
                    (listStaff as ArrayList<ModalSellerStaff>).add(obj)
                }
                adapterStaff = AdapterSellerStaff(this@SellerStaffSection, listStaff)
                recyclerStaff.adapter = adapterStaff
            }
        })


    }
}