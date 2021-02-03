package com.example.localzeadmin.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Modals.ModalOrderCart
import com.example.localzeadmin.R
import com.example.localzeadmin.SellerCartOrderDetails
import java.text.SimpleDateFormat
import java.util.*

class AdapterSellerOrders(val context: Context, private var sellerOrder: List<ModalOrderCart>) :
    RecyclerView.Adapter<AdapterSellerOrders.HolderSellerOrders>() {
    class HolderSellerOrders(view: View) : RecyclerView.ViewHolder(view) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSellerOrders {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row_order_seller, parent, false)
        return HolderSellerOrders(view)

    }

    override fun getItemCount(): Int {
        return sellerOrder.size

    }

    override fun onBindViewHolder(holder: HolderSellerOrders, position: Int) {
        val seller_orders = sellerOrder[position]
        holder.orderId.text = "OD${seller_orders.orderId}"
        holder.totalItems.text = seller_orders.orderQuantity + " items"
        holder.totalAmount.text = "Amount:- ₹" + seller_orders.orderCost
        holder.customerName.text = seller_orders.orderByName
        holder.customerMobileNumber.text = seller_orders.orderByMobile
        holder.orderStatus.text = seller_orders.orderStatus
        val sdf = SimpleDateFormat("dd/MM/yyyy,hh:mm a")
        val date = Date(seller_orders.orderTime.toLong())
        val formattedDate = sdf.format(date)
        holder.orderTime.text = formattedDate
        when (seller_orders.paymentMode) {
            "Cash on Delivery" -> {
                holder.paid.visibility = View.GONE
                holder.cod.visibility = View.VISIBLE
            }
            "Paytm" -> {
                holder.paid.visibility = View.GONE
                holder.cod.visibility = View.VISIBLE
            }
            else -> {
                holder.paid.visibility = View.GONE
                holder.cod.visibility = View.GONE
            }
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SellerCartOrderDetails::class.java)
            intent.putExtra("orderId", seller_orders.orderId)
            intent.putExtra("orderBy", seller_orders.orderBy)
            intent.putExtra("orderTo", seller_orders.orderTo)
            context.startActivity(intent)
        }
    }
}