package com.example.localzeadmin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Modals.ModelList
import com.example.localzeadmin.R

class AdapterListOrders(val context: Context, val listDetails: List<ModelList>) :
    RecyclerView.Adapter<AdapterListOrders.HolderListOrder>() {
    class HolderListOrder(view: View) : RecyclerView.ViewHolder(view) {
        val txtSNo: TextView = view.findViewById(R.id.txtNo)
        val txtItem_Name: TextView = view.findViewById(R.id.txtItem_Name)
        val item_quan: TextView = view.findViewById(R.id.quantity)
        val item_price: TextView = view.findViewById(R.id.edtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderListOrder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_single_row_seller_order, parent, false)
        return HolderListOrder(view)
    }

    override fun getItemCount(): Int {
        return listDetails.size
    }

    override fun onBindViewHolder(holder: HolderListOrder, position: Int) {
        val list_details = listDetails[position]
        holder.txtSNo.text = (position + 1).toString() + "."
        holder.txtItem_Name.text = list_details.itemName
        holder.item_quan.text = list_details.itemQuantity
        holder.item_price.text = "₹" + list_details.itemCost
    }
}