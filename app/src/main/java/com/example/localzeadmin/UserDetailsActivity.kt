package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Adapters.AdapterSellerListOrders
import com.example.localzeadmin.Adapters.AdapterSellerOrders
import com.example.localzeadmin.Modals.ModalOrderCart
import com.example.localzeadmin.Modals.ModalOrderList
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
    private lateinit var sellerOrderAdapter: AdapterSellerOrders
    private lateinit var mSellerOrders: List<ModalOrderCart>
    private lateinit var listAdapter: AdapterSellerListOrders
    private lateinit var mListOrders: List<ModalOrderList>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        cartOrders = findViewById(R.id.rl_cartOrders)
        listOrders = findViewById(R.id.rl_listOrders)
        searchCart = findViewById(R.id.searchCartOrders)
        searchList = findViewById(R.id.searchListOrders)
        recyclerOrders = findViewById(R.id.recyclerOrders)
        recyclerOrders.layoutManager = LinearLayoutManager(this)
        mSellerOrders = ArrayList<ModalOrderCart>()
        mListOrders = ArrayList<ModalOrderList>()
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
        searchCart.visibility = View.GONE
        searchList.visibility = View.VISIBLE
        FirebaseDatabase.getInstance().reference.child("seller").child(s).child("OrdersLists")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    (mListOrders as ArrayList<ModalOrderList>).clear()
                    for (i in snapshot.children) {
                        val obj = ModalOrderList(
                            i.child("orderId").value.toString(),
                            i.child("orderTime").value.toString(),
                            i.child("orderStatus").value.toString(),
                            i.child("orderCost").value.toString(),
                            i.child("orderBy").value.toString(),
                            i.child("orderTo").value.toString(),
                            i.child("deliveryAddress").value.toString(),
                            i.child("totalItems").value.toString(),
                            i.child("listStatus").value.toString(),
                            i.child("orderByName").value.toString(),
                            i.child("orderByMobile").value.toString(),
                            i.child("paymentMode").value.toString()
                        )
                        (mListOrders as ArrayList<ModalOrderList>).add(obj)
                    }
                    listAdapter = AdapterSellerListOrders(this@UserDetailsActivity, mListOrders)
                    recyclerOrders.adapter = listAdapter

                }

            })


    }

    private fun allCartOrders(s: String) {
        searchList.visibility = View.GONE
        searchCart.visibility = View.VISIBLE
        FirebaseDatabase.getInstance().reference.child("seller").child(s).child("Orders")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    (mSellerOrders as ArrayList<ModalOrderCart>).clear()
                    for (i in snapshot.children) {
                        val obj = ModalOrderCart(
                            i.child("orderId").value.toString(),
                            i.child("orderTime").value.toString(),
                            i.child("orderStatus").value.toString(),
                            i.child("orderCost").value.toString(),
                            i.child("orderBy").value.toString(),
                            i.child("orderTo").value.toString(),
                            i.child("orderQuantity").value.toString(),
                            i.child("deliveryAddress").value.toString(),
                            i.child("paymentMode").value.toString(),
                            i.child("orderByName").value.toString(),
                            i.child("orderByMobile").value.toString()
                        )
                        (mSellerOrders as ArrayList<ModalOrderCart>).add(obj)
                    }
                    sellerOrderAdapter =
                        AdapterSellerOrders(this@UserDetailsActivity, mSellerOrders)
                    recyclerOrders.adapter = sellerOrderAdapter
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
                (mListOrders as ArrayList<ModalOrderList>).clear()
                for (i in snapshot.children) {
                    val obj = ModalOrderList(
                        i.child("orderId").value.toString(),
                        i.child("orderTime").value.toString(),
                        i.child("orderStatus").value.toString(),
                        i.child("orderCost").value.toString(),
                        i.child("orderBy").value.toString(),
                        i.child("orderTo").value.toString(),
                        i.child("deliveryAddress").value.toString(),
                        i.child("totalItems").value.toString(),
                        i.child("listStatus").value.toString(),
                        i.child("orderByName").value.toString(),
                        i.child("orderByMobile").value.toString(),
                        i.child("paymentMode").value.toString()
                    )
                    (mListOrders as ArrayList<ModalOrderList>).add(obj)
                }
                listAdapter = AdapterSellerListOrders(this@UserDetailsActivity, mListOrders)
                recyclerOrders.adapter = listAdapter

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
                (mSellerOrders as ArrayList<ModalOrderCart>).clear()
                for (i in snapshot.children) {
                    val obj = ModalOrderCart(
                        i.child("orderId").value.toString(),
                        i.child("orderTime").value.toString(),
                        i.child("orderStatus").value.toString(),
                        i.child("orderCost").value.toString(),
                        i.child("orderBy").value.toString(),
                        i.child("orderTo").value.toString(),
                        i.child("orderQuantity").value.toString(),
                        i.child("deliveryAddress").value.toString(),
                        i.child("paymentMode").value.toString(),
                        i.child("orderByName").value.toString(),
                        i.child("orderByMobile").value.toString()
                    )
                    (mSellerOrders as ArrayList<ModalOrderCart>).add(obj)
                }
                sellerOrderAdapter =
                    AdapterSellerOrders(this@UserDetailsActivity, mSellerOrders)
                recyclerOrders.adapter = sellerOrderAdapter
            }
        })

    }

    override fun onStart() {
        super.onStart()
        shopID = intent.getStringExtra("shopId")
        allCartOrders(shopID.toString())
    }
}