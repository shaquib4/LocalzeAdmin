package com.example.localzeadmin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Modals.ModelList
import com.example.localzeadmin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AdapterUserOrderList(val context: Context, val listDetails: ArrayList<ModelList>) :
    RecyclerView.Adapter<AdapterUserOrderList.HolderOrderList>() {
    class HolderOrderList(view: View) : RecyclerView.ViewHolder(view) {
        val txtNoCustomer: TextView = view.findViewById(R.id.txtNoCustomer)
        val txtItem_NameCustomer: TextView = view.findViewById(R.id.txtItem_NameCustomer)
        val txtQuanCustomer: TextView = view.findViewById(R.id.txtQuanCustomer)
        val txtPriceCustomer: TextView = view.findViewById(R.id.txtPriceCustomer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderOrderList {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_single_row_customer_order, parent, false)
        return HolderOrderList(view)
    }

    override fun getItemCount(): Int {
        return listDetails.size
    }

    override fun onBindViewHolder(holder: HolderOrderList, position: Int) {
        val userOrderList = listDetails[position]
        holder.txtNoCustomer.text = (position + 1).toString() + "."
        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().reference.child("users").child(uid).child("MyOrderList")
                .child(orderId)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.child("listStatus").value.toString() == "Confirm") {
                    if (userOrderList.itemCost == 0.toString()) {
                        holder.txtPriceCustomer.text = "Not Available"
                    } else {
                        holder.txtPriceCustomer.text = "₹"+userOrderList.itemCost
                    }
                } else {
                    holder.txtPriceCustomer.visibility = View.GONE
                }
            }

        })
        holder.txtItem_NameCustomer.text = userOrderList.itemName
        holder.txtQuanCustomer.text = "X" + userOrderList.itemQuantity
    }
}