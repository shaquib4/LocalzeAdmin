package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localzeadmin.Adapters.AdapterUserInfo
import com.example.localzeadmin.Modals.ModalUserInfo
import com.google.firebase.database.*

class UserInfo : AppCompatActivity() {
    private lateinit var userArrayList: List<ModalUserInfo>
    private lateinit var recyclerUser: RecyclerView
    private lateinit var userAdapter:AdapterUserInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_info)
        userArrayList= ArrayList<ModalUserInfo>()
        recyclerUser=findViewById(R.id.recycler_userInfo)
        recyclerUser.layoutManager=LinearLayoutManager(this)


        val ref=FirebaseDatabase.getInstance().reference.child("users").addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (userArrayList as ArrayList<ModalUserInfo>).clear()
                for (i in snapshot.children){
                    val obj=ModalUserInfo(
                        i.child("name").value.toString(),
                        i.child("phone").value.toString(),
                        i.child("current_address").child("city").value.toString(),
                        i.child("uid").value.toString()

                    )
                    (userArrayList as ArrayList<ModalUserInfo>).add(obj)
                }
                if (userArrayList.isNotEmpty()){
                    userAdapter= AdapterUserInfo(this@UserInfo,userArrayList)
                    recyclerUser.adapter=userAdapter
                }
            }
        })

    }
}