package com.example.localzeadmin.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Modals.ModalSellerStaff
import com.example.localzeadmin.R

class AdapterSellerStaff(val context: Context, private val staffDetails: List<ModalSellerStaff>) :
    RecyclerView.Adapter<AdapterSellerStaff.HolderSellerStaff>() {
    class HolderSellerStaff(view: View) : RecyclerView.ViewHolder(view) {
        val staffName: TextView = view.findViewById(R.id.StaffName)
        val staffMobile: TextView = view.findViewById(R.id.StaffNumber)
        val staffAccess: TextView = view.findViewById(R.id.StaffAccess)
        val call: ImageView = view.findViewById(R.id.imgMakeCallStaff)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSellerStaff {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.staff_single_row, parent, false)
        return HolderSellerStaff(view)

    }

    override fun getItemCount(): Int {
        return staffDetails.size
    }

    override fun onBindViewHolder(holder: HolderSellerStaff, position: Int) {
        val staff_details = staffDetails[position]
        holder.staffName.text = staff_details.staffName
        holder.staffMobile.text = staff_details.staffMobile
        holder.staffAccess.text = staff_details.staffAccess
        holder.call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${staff_details.staffMobile}")
            val chooser = Intent.createChooser(intent, "Call With")
            context.startActivity(chooser)
        }
    }
}