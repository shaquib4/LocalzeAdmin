package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
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
    private lateinit var searchMobile: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_info)
        recyclerInfoSeller = findViewById(R.id.recycler_seller_info)
        searchMobile = findViewById(R.id.searchPhone)
        recyclerInfoSeller.layoutManager = LinearLayoutManager(this)
        sellerInfoList = ArrayList<ModalSellerInfo>()
        searchMobile.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchPhone(s.toString())
            }

        })
        databaseReference = FirebaseDatabase.getInstance().reference.child("seller")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (sellerInfoList as ArrayList<ModalSellerInfo>).clear()
                for (i in snapshot.children) {
                    val obj = ModalSellerInfo(
                        i.child("shopId").value.toString(),
                        i.child("shop_name").value.toString(),
                        i.child("name").value.toString(),
                        i.child("phone").value.toString(),
                        i.child("city").value.toString()
                    )
                    (sellerInfoList as ArrayList<ModalSellerInfo>).add(obj)
                }
                adapterSellerInfo = AdapterSellerInfo(this@SellerInfoActivity, sellerInfoList,"Details")
                recyclerInfoSeller.adapter = adapterSellerInfo
            }
        })
    }

    private fun searchPhone(s: String) {
        val querySellerDatabase =
            FirebaseDatabase.getInstance().reference.child("seller").orderByChild("phone")
                .startAt(s).endAt(s + "\uf8ff")
        querySellerDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (sellerInfoList as ArrayList<ModalSellerInfo>).clear()
                for (i in snapshot.children) {
                    val obj = ModalSellerInfo(
                        i.child("shopId").value.toString(),
                        i.child("shop_name").value.toString(),
                        i.child("name").value.toString(),
                        i.child("phone").value.toString(),
                        i.child("city").value.toString()
                    )
                    (sellerInfoList as ArrayList<ModalSellerInfo>).add(obj)
                }
                adapterSellerInfo = AdapterSellerInfo(this@SellerInfoActivity, sellerInfoList,"Details")
                recyclerInfoSeller.adapter = adapterSellerInfo
            }
        })
    }
}