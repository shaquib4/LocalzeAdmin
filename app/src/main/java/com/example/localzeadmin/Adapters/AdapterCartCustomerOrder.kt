package com.example.localzeadmin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Modals.ModalOrderCart
import com.example.localzeadmin.R

class AdapterCartCustomerOrder(val context: Context, val list:List<ModalOrderCart>):(RecyclerView.Adapter<AdapterCartCustomerOrder.HolderCartOrder>)() {
    class HolderCartOrder(view: View) : RecyclerView.ViewHolder(view) {


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderCartOrder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_row_order_user,parent,false)
        return AdapterCartCustomerOrder.HolderCartOrder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: HolderCartOrder, position: Int) {

    }
}