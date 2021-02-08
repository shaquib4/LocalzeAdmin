package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import com.android.volley.Response
import com.android.volley.toolbox.Volley

class CustomNotificationActivity : AppCompatActivity() {
    private lateinit var btnAddProduct: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_notification)
        btnAddProduct = findViewById(R.id.btnAddProductNotification)
        btnAddProduct.setOnClickListener {
            prepareNotificationMessage()
        }
    }

    private fun prepareNotificationMessage() {
        val NOTIFICATION_TOPIC =
            "/topics/PUSH_NOTIFICATIONS"
        val NOTIFICATION_TITLE = "Add Products in Your Shop"
        val NOTIFICATION_MESSAGE = "Customers viewing your shop ❤ ,add more products...."
        val NOTIFICATION_TYPE = "AddProduct"
        val notificationJs = JSONObject()
        val notificationBodyJs = JSONObject()
        try {
            notificationBodyJs.put("notificationType", NOTIFICATION_TYPE)
            notificationBodyJs.put("notificationTitle", NOTIFICATION_TITLE)
            notificationBodyJs.put("notificationMessage", NOTIFICATION_MESSAGE)
            notificationJs.put("to", NOTIFICATION_TOPIC)
            notificationJs.put("data", notificationBodyJs)
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        sendFcmNotification(notificationJs)
    }

    private fun sendFcmNotification(notificationJs: JSONObject) {
        val jsonObjectRequest =
            object : JsonObjectRequest(
                "https://fcm.googleapis.com/fcm/send",
                notificationJs,
                Response.Listener {

                },
                Response.ErrorListener {

                }) {
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