package com.example.localzeadmin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.localzeadmin.Adapters.AdapterOrderedItems
import com.example.localzeadmin.Modals.ModalOrderCart
import com.example.localzeadmin.Modals.ModelOrderedItems
import com.google.firebase.database.*

class SellerCartOrderDetails : AppCompatActivity() {
    private lateinit var orderId: TextView
    private lateinit var orderDate: TextView
    private lateinit var orderStatus: TextView
    private lateinit var totalItems: TextView
    private lateinit var deliveryCharge: TextView
    private lateinit var totalAmount: TextView
    private lateinit var paymentStatus: TextView
    private lateinit var deliveryAddress: TextView
    private var orderID: String? = "100"
    private var orderBy: String? = "200"
    private var orderTo: String? = "300"
    private lateinit var sellerDatabase: DatabaseReference
    private lateinit var orderedItems: List<ModelOrderedItems>
    private lateinit var adapterOrderedItems:AdapterOrderedItems
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_cart_order_details)
        orderId = findViewById(R.id.txtOrderId)
        orderDate = findViewById(R.id.txtOrderDate)
        orderStatus = findViewById(R.id.txtOrderStatus)
        totalItems = findViewById(R.id.txtItems)
        deliveryCharge = findViewById(R.id.etDeliveryCharge)
        totalAmount = findViewById(R.id.txtOrderCost)
        paymentStatus = findViewById(R.id.paymentStatusDetailsSeller)
        deliveryAddress = findViewById(R.id.txtOrderDeliveryAddress)
        orderedItems = ArrayList<ModelOrderedItems>()
        orderID = intent.getStringExtra("orderId")
        orderBy = intent.getStringExtra("orderBy")
        orderTo = intent.getStringExtra("orderTo")
        sellerDatabase =
            FirebaseDatabase.getInstance().reference.child("seller").child(orderTo.toString())
                .child("Orders").child(orderID.toString())
        sellerDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val ordersId = snapshot.child("orderId").value.toString()
                val orderTime = snapshot.child("orderTime").value.toString()
                val deliveryAddress = snapshot.child("deliveryAddress").value.toString()
                val ordersStatus = snapshot.child("orderStatus").value.toString()
                val totalItem = snapshot.child("orderQuantity").value.toString()
                val totalAmounts = snapshot.child("orderCost").value.toString()
                if (snapshot.child("deliveryFee").exists()) {
                    val deliveryFees = snapshot.child("deliveryFee").value.toString()
                } else {
                    val deliveryFees = "Delivery Fee must be updated Soon"
                }
                val paymentMode = snapshot.child("paymentMode").value.toString()


            }

        })
        sellerDatabase.child("Items").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (orderedItems as ArrayList<ModelOrderedItems>).clear()
                for (i in snapshot.children) {
                    val obj = ModelOrderedItems(
                        i.child("productId").value.toString(),
                        i.child("productTitle").value.toString(),
                        i.child("finalPrice").value.toString(),
                        i.child("priceEach").value.toString(),
                        i.child("finalQuantity").value.toString()
                    )
                    (orderedItems as ArrayList<ModelOrderedItems>).add(obj)
                }


            }

        })
    }
}