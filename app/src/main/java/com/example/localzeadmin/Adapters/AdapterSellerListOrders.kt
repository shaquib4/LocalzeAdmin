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
import com.example.localzeadmin.SellerListOrderDetails
import java.text.SimpleDateFormat
import java.util.*

class AdapterSellerListOrders(
    val context: Context,
    private var sellerListOrders: List<ModalOrderList>
) : RecyclerView.Adapter<AdapterSellerListOrders.HolderSellerListOrders>() {
    class HolderSellerListOrders(view: View) : RecyclerView.ViewHolder(view) {
        val orderId: TextView = view.findViewById(R.id.orderIdTv)
        val totalItems: TextView = view.findViewById(R.id.totalItemsTv)
        val totalAmount: TextView = view.findViewById(R.id.orderAmountTv)
        val customerName: TextView = view.findViewById(R.id.txtOrderedBy)
        val customerMobileNumber: TextView = view.findViewById(R.id.txtMobileOrderedBy)
        val cod: TextView = view.findViewById(R.id.txtCod1)
        val paid: TextView = view.findViewById(R.id.txtCod2)
        val orderStatus: TextView = view.findViewById(R.id.orderStatusTv)
        val orderTime: TextView = view.findViewById(R.id.orderDateTv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSellerListOrders {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row_order_seller, parent, false)
        return HolderSellerListOrders(view)

    }

    override fun getItemCount(): Int {
        return sellerListOrders.size

    }

    override fun onBindViewHolder(holder: HolderSellerListOrders, position: Int) {
        val list_orders = sellerListOrders[position]
        holder.orderId.text = "OD${list_orders.orderId}"
        holder.totalItems.text = list_orders.totalItems + " items"
        holder.totalAmount.text = "Amount:- ₹" + list_orders.orderCost
        holder.customerName.text = list_orders.orderByName
        holder.customerMobileNumber.text = list_orders.orderByMobile
        holder.orderStatus.text = list_orders.orderStatus
        when (list_orders.paymentMode) {
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
        val sdf = SimpleDateFormat("dd/MM/yyyy,hh:mm a")
        val date = Date(list_orders.orderTime.toLong())
        val formattedDate = sdf.format(date)
        holder.orderTime.text = formattedDate
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SellerListOrderDetails::class.java)
            intent.putExtra("orderId", list_orders.orderId)
            intent.putExtra("orderBy", list_orders.orderBy)
            intent.putExtra("orderTo", list_orders.orderTo)
            context.startActivity(intent)
        }
    }
}