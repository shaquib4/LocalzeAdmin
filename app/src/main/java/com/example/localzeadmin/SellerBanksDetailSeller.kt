package com.example.localzeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SellerBanksDetailSeller : AppCompatActivity() {
    private lateinit var accNumber: TextView
    private lateinit var beneficiaryName: TextView
    private lateinit var accountType: TextView
    private lateinit var razorPayId:TextView
    private var shopId=""
    private lateinit var accountLayout: RelativeLayout
    private lateinit var noAccountLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_banks_detail_seller)
        shopId=intent.getStringExtra("shopId").toString()
        accNumber = findViewById(R.id.acc_numberSeller)
        beneficiaryName = findViewById(R.id.beneficiary_NameSeller)
        accountType = findViewById(R.id.acc_typeSeller)
        accountLayout = findViewById(R.id.account)
        noAccountLayout = findViewById(R.id.noAccount)
        razorPayId=findViewById(R.id.RIdSeller)
        FirebaseDatabase.getInstance().reference.child("seller").child(shopId).child("razorpayId").addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    razorPayId.text=snapshot.value.toString()
                }else{
                    razorPayId.text=""
                }
            }
        })

        FirebaseDatabase.getInstance().reference.child("seller").child(shopId).child("Account_Details")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {

                        FirebaseDatabase.getInstance().reference.child("customers").child(shopId)
                            .child("AccountDetails")
                            .addValueEventListener(object : ValueEventListener {
                                override fun onCancelled(error: DatabaseError) {

                                }

                                override fun onDataChange(snapshot: DataSnapshot) {
                                    val accountNo = snapshot.child("Account_no").value.toString()
                                    val benName =
                                        snapshot.child("Beneficiary_name").value.toString()
                                    val accType = snapshot.child("Account_type").value.toString()
                                    accNumber.text = "XXXXXXXX" + accountNo.takeLast(4)
                                    beneficiaryName.text = benName
                                    accountType.text = accType
                                }
                            })

                    } else {
                        accountLayout.visibility = View.INVISIBLE
                        noAccountLayout.visibility = View.VISIBLE

                    }
                }
            })
    }
}