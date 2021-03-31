package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SellerBankDetails : AppCompatActivity() {
    private var shopId: String? = "400"
    private lateinit var accountNumber: TextView
    private lateinit var beneficiaryName: TextView
    private lateinit var accountType: TextView
    private lateinit var razorpay_Id: EditText
    private lateinit var enterButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_bank_details)
        shopId = intent.getStringExtra("shopId")
        accountNumber = findViewById(R.id.acc_number)
        beneficiaryName = findViewById(R.id.txtaccName)
        accountType = findViewById(R.id.acc_type)
        razorpay_Id = findViewById(R.id.edtRazorpay)
        enterButton = findViewById(R.id.addRazorpay)
        val databaseReference = FirebaseDatabase.getInstance().reference.child("customers")
        databaseReference.child(shopId.toString()).child("AccountDetails")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {


                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val accountNo = snapshot.child("Account_no").value.toString()
                    val benName =
                        snapshot.child("Beneficiary_name").value.toString()
                    val accType = snapshot.child("Account_type").value.toString()
                    accountNumber.text = accountNo
                    beneficiaryName.text = benName
                    accountType.text = accType
                }
            })
        enterButton.setOnClickListener {
            if (razorpay_Id.text.isNotEmpty()) {
                val headers = HashMap<String, Any>()
                headers["razorpayId"] = razorpay_Id.text.toString()
                FirebaseDatabase.getInstance().reference.child("seller").child(shopId.toString())
                    .updateChildren(headers).addOnSuccessListener {
                        Toast.makeText(this, "Razorpay Id added Successfully", Toast.LENGTH_LONG)
                            .show()
                    }
            }
        }

    }
}