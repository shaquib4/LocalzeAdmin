package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var cartOrders: RelativeLayout
    private lateinit var listOrders: RelativeLayout
    private lateinit var searchCart: EditText
    private lateinit var searchList: EditText
    private lateinit var recyclerOrders: RecyclerView
    private var shopID: String? = "200"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        cartOrders = findViewById(R.id.rl_cartOrders)
        listOrders = findViewById(R.id.rl_listOrders)
        searchCart = findViewById(R.id.searchCartOrders)
        searchList = findViewById(R.id.searchListOrders)
        recyclerOrders = findViewById(R.id.recyclerOrders)
        shopID = intent.getStringExtra("shopId")
        cartOrders.setOnClickListener {
            allCartOrders(shopID.toString())
        }
        listOrders.setOnClickListener {
            allListOrders(shopID.toString())
        }
        searchCart.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchCartOrders(s.toString())
            }

        })
        searchList.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchListOrders(s.toString())

            }
        })
    }

    private fun allListOrders(s: String) {
        FirebaseDatabase.getInstance().reference.child("seller").child(s).child("OrdersLists")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {

                }

            })


    }

    private fun allCartOrders(s: String) {
        FirebaseDatabase.getInstance().reference.child("seller").child(s).child("Orders")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    
                }

            })

    }

    private fun searchListOrders(s: String) {
        val querySellerDatabase =
            FirebaseDatabase.getInstance().reference.child("seller").child(shopID.toString())
                .child("OrdersLists").orderByChild("orderId").startAt(s).endAt(s + "\uF8FF")
        querySellerDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

            }

        })

    }

    private fun searchCartOrders(s: String) {
        val querySellerDatabase =
            FirebaseDatabase.getInstance().reference.child("seller").child(shopID.toString())
                .child("Orders").orderByChild("orderId").startAt(s).endAt(s + "\uF8FF")
        querySellerDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

            }

        })

    }
}