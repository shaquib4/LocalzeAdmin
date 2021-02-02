package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Adapters.AdapterCartCustomerOrder
import com.example.localzeadmin.Adapters.AdapterListCustomerOrder
import com.example.localzeadmin.Modals.ModalOrderCart
import com.example.localzeadmin.Modals.ModalOrderList
import com.google.firebase.database.*

class CustomerOrder : AppCompatActivity() {
    private lateinit var cart:List<ModalOrderCart>
    private lateinit var cartRel:RelativeLayout
    private lateinit var listRel:RelativeLayout
    private lateinit var list:List<ModalOrderList>
    private lateinit var searchCart:EditText
    private lateinit var searchList:EditText
    private lateinit var uid:String
    private lateinit var adapterList:AdapterListCustomerOrder
    private lateinit var recyclerNo:RelativeLayout
    private lateinit var cartText:TextView
    private lateinit var listText:TextView
    private lateinit var adapterOrderUser:AdapterCartCustomerOrder
    private lateinit var currentOrderHistoryDatabase:DatabaseReference
    private lateinit var listRef:DatabaseReference
    private lateinit var recyclerCustomer:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_order)
        uid=intent.getStringExtra("uid").toString()
        cartText=findViewById(R.id.txtCartCurrent)
        searchCart=findViewById(R.id.searchCart)
        searchList=findViewById(R.id.searchList)
        listText=findViewById(R.id.txtlistCurrent)
        cartRel=findViewById(R.id.rl_cartCurrent)
        listRel=findViewById(R.id.rl_listCurrent)
        recyclerNo=findViewById(R.id.rl_Current_orders)
        recyclerCustomer=findViewById(R.id.recyclerCurrentOrders)

        cart=ArrayList<ModalOrderCart>()
        list=ArrayList<ModalOrderList>()
        recyclerCustomer.layoutManager=LinearLayoutManager(this)
        currentOrderHistoryDatabase =
            FirebaseDatabase.getInstance().reference.child("users").child(uid).child("MyOrders")
        listRef=FirebaseDatabase.getInstance().reference.child("users").child(uid).child("MyOrderList")
        cartRel.setOnClickListener {
            cartOrders()
        }
        listRel.setOnClickListener {
            listOrder()
        }
        searchCart.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               searchCartOrder(s.toString())
            }
        })

        searchList.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               searchListOrder(s.toString())
            }
        })



    }

    private fun listOrder() {
        searchList.visibility=View.VISIBLE
        searchCart.visibility=View.GONE
        cartText.setTextColor(this.resources.getColor(R.color.black))
        listText.setTextColor(this.resources.getColor(R.color.colorPrimary))

        listRef.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (list as ArrayList<ModalOrderList>).clear()
                for (i in snapshot.children){
                    val obj=ModalOrderList(
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
                    (list as ArrayList<ModalOrderList>).add(obj)
                }



                    adapterList =
                        AdapterListCustomerOrder(
                            this@CustomerOrder,
                            list
                        )
                    recyclerCustomer.adapter = adapterList


            }
        })
    }

    private fun cartOrders() {
        searchList.visibility=View.GONE
        searchCart.visibility=View.VISIBLE
        cartText.setTextColor(this.resources.getColor(R.color.colorPrimary))
        listText.setTextColor(this.resources.getColor(R.color.black))


            currentOrderHistoryDatabase.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    (cart as ArrayList<ModalOrderCart>).clear()
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


                            (cart as ArrayList<ModalOrderCart>).add(obj)
                    }




                        adapterOrderUser =
                            AdapterCartCustomerOrder(
                                this@CustomerOrder,
                                cart
                            )
                        recyclerCustomer.adapter = adapterOrderUser


                }
            })
    }

    private fun searchListOrder(str: String) {
        val queryShop =
            listRef.orderByChild("orderId")
                .startAt(str)
                .endAt(str + "\uf8ff")
        queryShop.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (list as ArrayList<ModalOrderList>).clear()
                for (i in snapshot.children){
                    val obj=ModalOrderList(
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
                    (list as ArrayList<ModalOrderList>).add(obj)
                }
                (list as ArrayList<ModalOrderList>).reverse()


                    adapterList =
                        AdapterListCustomerOrder(
                            this@CustomerOrder,
                            list
                        )
                    recyclerCustomer.adapter = adapterList

            }
        })
    }

    private fun searchCartOrder(str: String) {
        val queryShop =
            currentOrderHistoryDatabase.orderByChild("orderId")
                .startAt(str)
                .endAt(str + "\uf8ff")
        queryShop.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (cart as ArrayList<ModalOrderCart>).clear()
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


                    (cart as ArrayList<ModalOrderCart>).add(obj)
                }

                    adapterOrderUser =
                        AdapterCartCustomerOrder(
                            this@CustomerOrder,
                            cart
                        )
                    recyclerCustomer.adapter = adapterOrderUser
                }

        })
    }
}