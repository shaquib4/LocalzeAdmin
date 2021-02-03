package com.example.localzeadmin.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Modals.ModalOrderList
import com.example.localzeadmin.R
import com.example.localzeadmin.UserOrderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AdapterListCustomerOrder(val context: Context,val list:List<ModalOrderList>):(RecyclerView.Adapter<AdapterListCustomerOrder.HolderListOrder>)() {
    class HolderListOrder(view:View):RecyclerView.ViewHolder(view) {
        val orderId: TextView = view.findViewById(R.id.orderIdCustomerTv)
        val totalItems: TextView = view.findViewById(R.id.totalItemsCustomerTv)
        val totalAmount: TextView = view.findViewById(R.id.orderAmountCustomerTv)
        val shopName: TextView = view.findViewById(R.id.txtShopName)
        val shopMobile: TextView = view.findViewById(R.id.txtShopMobile)
        val cod: TextView = view.findViewById(R.id.txtCod1)
        val paid: TextView = view.findViewById(R.id.txtCod2)
        val orderStatus: TextView = view.findViewById(R.id.orderStatusCustomerTv)
        val orderTime: TextView = view.findViewById(R.id.orderDateTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderListOrder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_row_order_user,parent,false)
        return AdapterListCustomerOrder.HolderListOrder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: HolderListOrder, position: Int) {
        val listDetails = list[position]
        val databaseReference =
            FirebaseDatabase.getInstance().reference.child("seller").child(listDetails.orderTo)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                holder.shopName.text = snapshot.child("shop_name").value.toString()
                holder.shopMobile.text = snapshot.child("phone").value.toString()
            }

        })
        holder.orderId.text = "OD${listDetails.orderId}"
        holder.totalItems.text = listDetails.totalItems + " items"
        holder.totalAmount.text = "Amount:- ₹${listDetails.orderCost}"
        holder.orderStatus.text = listDetails.orderStatus
        val sdf = SimpleDateFormat("dd/MM/yyyy,hh:mm a")
      try{  val date = Date(listDetails.orderTime.toLong())
        val formattedDate = sdf.format(date)
        holder.orderTime.text = formattedDate}catch (e:Exception){
          e.printStackTrace()
      }
        when (listDetails.paymentMode) {
            "" -> {
                holder.paid.visibility = View.GONE
                holder.cod.visibility = View.GONE
            }
            "Cash on Delivery" -> {
                holder.paid.visibility = View.GONE
                holder.cod.visibility = View.VISIBLE
            }
            else -> {
                holder.cod.visibility = View.GONE
                holder.paid.visibility = View.VISIBLE
            }


        }
        holder.itemView.setOnClickListener {
            val intent= Intent(context,UserOrderDetails::class.java)
            intent.putExtra("uid",listDetails.orderBy)
            intent.putExtra("orderId",listDetails.orderId)
            intent.putExtra("orderTo",listDetails.orderTo)
            intent.putExtra("totalCost",listDetails.orderCost)
            context.startActivity(intent)
        }
    }
}