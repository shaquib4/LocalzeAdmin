package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Adapters.AdapterListOrders
import com.example.localzeadmin.Adapters.AdapterOrderedItems
import com.example.localzeadmin.Adapters.AdapterSellerListOrders
import com.example.localzeadmin.Modals.ModelList
import com.example.localzeadmin.Modals.ModelOrderedItems
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SellerListOrderDetails : AppCompatActivity() {
    private lateinit var listOrderId: TextView
    private lateinit var listOrderDate: TextView
    private lateinit var listOrderStatus: TextView
    private lateinit var listTotalItems: TextView
    private lateinit var listTotalAmount: TextView
    private lateinit var listDeliveryFee: TextView
    private lateinit var listPaymentStatus: TextView
    private lateinit var listDeliveryAddress: TextView
    private lateinit var recyclerListOrder: RecyclerView
    private lateinit var adapterListOrder: AdapterListOrders
    private lateinit var orderedListItems: List<ModelList>
    private var orderID: String? = "100"
    private var orderBy: String? = "200"
    private var orderTo: String? = "300"
    private lateinit var sellerListDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_list_order_details)
        listOrderId = findViewById(R.id.txtListOrderId)
        listOrderDate = findViewById(R.id.txtListOrderDate)
        listOrderStatus = findViewById(R.id.txtListOrderStatus)
        listTotalItems = findViewById(R.id.txtListItems)
        listTotalAmount = findViewById(R.id.txtListOrderCost)
        listDeliveryFee = findViewById(R.id.deliverySellerCharge)
        listPaymentStatus = findViewById(R.id.paymentStatusSeller)
        listDeliveryAddress = findViewById(R.id.txtListOrderDeliveryAddress)
        recyclerListOrder = findViewById(R.id.recyclerOrderedSellerItem)
        recyclerListOrder.layoutManager = LinearLayoutManager(this)
        orderedListItems = ArrayList<ModelList>()
        orderID = intent.getStringExtra("orderId")
        orderBy = intent.getStringExtra("orderBy")
        orderTo = intent.getStringExtra("orderTo")
        sellerListDatabase =
            FirebaseDatabase.getInstance().reference.child("seller").child(orderTo.toString())
                .child("OrdersLists")
        sellerListDatabase.child(orderID.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val ordersId = snapshot.child("orderId").value.toString()
                    val orderTime = snapshot.child("orderTime").value.toString()
                    val deliverysAddress = snapshot.child("deliveryAddress").value.toString()
                    val ordersStatus = snapshot.child("orderStatus").value.toString()
                    val totalItem = snapshot.child("totalItems").value.toString()
                    val totalAmounts = snapshot.child("orderCost").value.toString()
                    if (snapshot.child("deliveryFee").exists()) {
                        val deliveryFees = snapshot.child("deliveryFee").value.toString()
                        listDeliveryFee.text = "₹${deliveryFees}"
                    } else {
                        val deliveryFees = "Delivery Fee must be updated Soon"
                        listDeliveryFee.text = deliveryFees
                    }
                    val paymentMode = snapshot.child("paymentMode").value.toString()
                    listOrderId.text = "OD${ordersId}"
                    val sdf = SimpleDateFormat("dd/MM/yyyy,hh:mm a")
                    val date = Date(orderTime.toLong())
                    val formattedDate = sdf.format(date)
                    listOrderDate.text = formattedDate
                    listDeliveryAddress.text = deliverysAddress
                    listOrderStatus.text = ordersStatus
                    listTotalItems.text = totalItem
                    listTotalAmount.text = "₹$totalAmounts"
                }

            })
        sellerListDatabase.child(orderID.toString()).child("ListItems")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    (orderedListItems as ArrayList<ModelList>).clear()
                    for (i in snapshot.children) {
                        val obj = ModelList(
                            i.child("itemId").value.toString(),
                            i.child("itemName").value.toString(),
                            i.child("itemQuantity").value.toString(),
                            i.child("itemCost").value.toString(),
                            i.child("shopId").value.toString()
                        )
                        (orderedListItems as ArrayList<ModelList>).add(obj)
                    }
                    adapterListOrder =
                        AdapterListOrders(this@SellerListOrderDetails, orderedListItems)
                    recyclerListOrder.adapter = adapterListOrder
                }
            })
    }
}