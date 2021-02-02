package com.example.localzeadmin.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.CustomerOrder
import com.example.localzeadmin.Modals.ModalUserInfo
import com.example.localzeadmin.R

class AdapterUserInfo(val context: Context,val userList:List<ModalUserInfo>):
    RecyclerView.Adapter<AdapterUserInfo.HolderUserInfo>() {
    class HolderUserInfo(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.UserName)
        val phone: TextView = view.findViewById(R.id.StaffNumber)
        val address: TextView = view.findViewById(R.id.StaffAccess)

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterUserInfo.HolderUserInfo {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.user_single_row, parent, false)
        return AdapterUserInfo.HolderUserInfo(view)
    }

    override fun getItemCount(): Int {
      return userList.size

    }

    override fun onBindViewHolder(holder: AdapterUserInfo.HolderUserInfo, position: Int) {
       val userInfo=userList[position]
        holder.address.text=userInfo.userLocation
        holder.phone.text=userInfo.userMobile
        holder.userName.text=userInfo.userName
        holder.itemView.setOnClickListener {
            val intent= Intent(context,CustomerOrder::class.java)
             intent.putExtra("uid",userInfo.uid)
             context.startActivity(intent)
        }
    }
}