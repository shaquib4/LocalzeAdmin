package com.example.localzeadmin.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Modals.ModalSellerInfo
import com.example.localzeadmin.R
import com.example.localzeadmin.SellerSectionActivity
import com.example.localzeadmin.UserDetailsActivity

class AdapterSellerInfo(val context: Context, private val sellerDetails: List<ModalSellerInfo>) :
    RecyclerView.Adapter<AdapterSellerInfo.HolderSellerInfo>() {
    class HolderSellerInfo(view: View) : RecyclerView.ViewHolder(view) {
        val shopName: TextView = view.findViewById(R.id.ShopName)
        val sellerName: TextView = view.findViewById(R.id.Name)
        val phone: TextView = view.findViewById(R.id.Number)
        val address: TextView = view.findViewById(R.id.City)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSellerInfo {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_seller_info, parent, false)
        return HolderSellerInfo(view)
    }

    override fun getItemCount(): Int {
        return sellerDetails.size
    }

    override fun onBindViewHolder(holder: HolderSellerInfo, position: Int) {
        val seller_details = sellerDetails[position]
        holder.shopName.text = seller_details.shopName
        holder.sellerName.text = seller_details.sellerName
        holder.phone.text = seller_details.phoneNumber
        holder.address.text = seller_details.shopAddress
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SellerSectionActivity::class.java)
            intent.putExtra("shopId", seller_details.shopId)
            context.startActivity(intent)
        }
    }
}