package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Exception

class SingleNotification : AppCompatActivity() {
    private lateinit var edtTitle:EditText
    private lateinit var edtMessage: EditText
    private lateinit var btnSend:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_notification)
        edtTitle=findViewById(R.id.edtTitleSingle)
        edtMessage=findViewById(R.id.edtMessageSingle)
        btnSend=findViewById(R.id.btnSendSingle)

        btnSend.setOnClickListener {
            val selected=intent.getStringExtra("selected").toString()
            val uid=intent.getStringExtra("uid").toString()
            prepareNotification(selected,uid)
        }
    }

    private fun prepareNotification(selected: String, uid: String) {
        val NOTIFICATION_TOPIC =
            "/topics/PUSH_NOTIFICATIONS"
        val NOTIFICATION_TITLE = edtTitle.text.toString()
        val NOTIFICATION_MESSAGE = edtMessage.text.toString()
        val NOTIFICATION_TYPE = "SingleNotification"
        val notificationJs=JSONObject()
        val notificationBodyJs=JSONObject()
        try {
            notificationBodyJs.put("notificationType", NOTIFICATION_TYPE)
            notificationBodyJs.put("notificationTitle", NOTIFICATION_TITLE)
            notificationBodyJs.put("notificationMessage", NOTIFICATION_MESSAGE)
            notificationBodyJs.put("selectedPerson", selected)
            notificationBodyJs.put("uid",uid)
            notificationJs.put("to", NOTIFICATION_TOPIC)
            notificationJs.put("data", notificationBodyJs)
        }catch (e:Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        sendFcmNotification(notificationJs)
    }

    private fun sendFcmNotification(notificationJs: JSONObject) {
        val jsonObjectRequest = object : JsonObjectRequest(
            "https://fcm.googleapis.com/fcm/send",
            notificationJs,
            Response.Listener {
                Toast.makeText(this,"Successfully Sent",Toast.LENGTH_LONG).show()
            }, Response.ErrorListener {
                Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Authorization"] =
                    "key=AAAA0TgW0AY:APA91bGNGMLtISkxVjfP-Mvu6GCZeeTcoDzvFtUg0Pq1SrJ9SshsFXDuXR9i3-lOqtlUjVmGqmv4C0sSRbsIphiacRau5c1ERQEUBukLxV-EXGVGv1ZmTN796LyLs1Wd7s1Tnu60e_2D"
                return headers
            }
        }
        Volley.newRequestQueue(this).add(jsonObjectRequest)

    }

}