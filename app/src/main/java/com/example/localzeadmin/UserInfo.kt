package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.localzeadmin.Modals.ModalUserInfo
import com.google.firebase.database.*

class UserInfo : AppCompatActivity() {
    private lateinit var userArrayList: List<ModalUserInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_info)
        userArrayList= ArrayList<ModalUserInfo>()

        val ref=FirebaseDatabase.getInstance().reference.child("users").addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                (userArrayList as ArrayList<ModalUserInfo>).clear()
                for (i in snapshot.children){
                    val obj=ModalUserInfo(
                        i.child("phone").value.toString(),
                        i.child("name").value.toString(),
                        i.child("current_address").child("city").value.toString()
                    )
                    (userArrayList as ArrayList<ModalUserInfo>).add(obj)
                }
            }
        })

    }
}