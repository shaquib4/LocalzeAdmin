package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Adapters.AdapterSellerInfo
import com.example.localzeadmin.Modals.ModalSellerInfo
import com.example.localzeadmin.Modals.ModelList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BankDetails : AppCompatActivity() {
    private lateinit var adapterBankDetails: AdapterSellerInfo
    private lateinit var listSellersDetails: List<ModalSellerInfo>
    private lateinit var recyclerSellersDetails: RecyclerView
    private lateinit var editSearch: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_details)
        listSellersDetails = ArrayList<ModalSellerInfo>()
        recyclerSellersDetails = findViewById(R.id.recycler_seller_info)
        editSearch = findViewById(R.id.searchPhoneBank)
        recyclerSellersDetails.layoutManager = LinearLayoutManager(this)
        val databaseReference = FirebaseDatabase.getInstance().reference.child("seller")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children) {
                    if (i.child("Account_Details").value.toString() == "true" && !i.child("razorpayId")
                            .exists()
                    ) {
                        val shopId = i.child("shopId").value.toString()
                        val shopName = i.child("shop_name").value.toString()
                        val sellerName = i.child("name").value.toString()
                        val phoneNumber = i.child("phone").value.toString()
                        val shopAddress = i.child("address").value.toString()
                        val obj =
                            ModalSellerInfo(shopId, shopName, sellerName, phoneNumber, shopAddress)
                        (listSellersDetails as ArrayList<ModalSellerInfo>).add(obj)
                        adapterBankDetails =
                            AdapterSellerInfo(this@BankDetails, listSellersDetails, "BankDetails")
                        recyclerSellersDetails.adapter = adapterBankDetails
                    }
                }
            }

        })
    }
}