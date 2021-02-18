package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import com.android.volley.Response
import com.android.volley.toolbox.Volley

class CustomNotificationActivity : AppCompatActivity() {
    private lateinit var btnAddProduct: Button
    private lateinit var title: EditText
    private lateinit var message: EditText
    private lateinit var selectPerson: Spinner
    private lateinit var selectReason: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_notification)
        btnAddProduct = findViewById(R.id.btnAddProductNotification)
        title = findViewById(R.id.edtTitle)
        message = findViewById(R.id.edtMessage)
        selectPerson = findViewById(R.id.spnSelect)
        selectReason = findViewById(R.id.spnProduct)
      /*  if (selectReason.selectedItem.toString() == "Add More Product") {
            btnAddProduct.setOnClickListener {
                prepareNotificationMessage(title.text.toString(),message.text.toString(),selectPerson.selectedItem.toString(),selectReason.selectedItem.toString())
            }
        } else if (selectReason.selectedItem.toString() == "Offer") {
            btnAddProduct.setOnClickListener {
                prepareNewNotificationMessage(title.text.toString(),message.text.toString(),selectPerson.selectedItem.toString(),selectReason.selectedItem.toString())
            }
        }else if (selectReason.selectedItem.toString()=="No Product Added"){
            btnAddProduct.setOnClickListener {
                prepareNoProductNotificationMessage(title.text.toString(),message.text.toString(),selectPerson.selectedItem.toString(),selectReason.selectedItem.toString())
            }
        }*/
        btnAddProduct.setOnClickListener {
            when (selectReason.selectedItem.toString().trim()){
                "Add More Product"->{
                    prepareNotificationMessage(title.text.toString(),message.text.toString(),selectPerson.selectedItem.toString(),selectReason.selectedItem.toString())

                }
                "Offer"->{
                    prepareNewNotificationMessage(title.text.toString(),message.text.toString(),selectPerson.selectedItem.toString(),selectReason.selectedItem.toString())
                }
                "No Product"->{
                    prepareNoProductNotificationMessage(title.text.toString(),message.text.toString(),selectPerson.selectedItem.toString(),selectReason.selectedItem.toString())
                }
            }
        }


    }

    private fun prepareNoProductNotificationMessage
                ( title: String,
                  message: String,
                  selectPerson: String,
                  selectReason: String) {
        val NOTIFICATION_TOPIC =
            "/topics/PUSH_NOTIFICATIONS"
        val NOTIFICATION_TITLE =title
        val NOTIFICATION_MESSAGE=message
        val NOTIFICATION_TYPE="NoProduct"
        val notificationJs = JSONObject()
        val notificationBodyJs = JSONObject()
        try {
            notificationBodyJs.put("notificationType", NOTIFICATION_TYPE)
            notificationBodyJs.put("notificationTitle", NOTIFICATION_TITLE)
            notificationBodyJs.put("notificationMessage", NOTIFICATION_MESSAGE)
            notificationBodyJs.put("person",selectPerson)
            notificationBodyJs.put("reason",selectReason)
            notificationJs.put("to", NOTIFICATION_TOPIC)
            notificationJs.put("data", notificationBodyJs)
        }catch (e:Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        sendFcmNotification(notificationJs)

    }

    private fun prepareNewNotificationMessage(
        title: String,
        message: String,
        selectPerson: String,
        selectReason: String
    ) {
        val NOTIFICATION_TOPIC =
            "/topics/PUSH_NOTIFICATIONS"
        val NOTIFICATION_TITLE =title
        val NOTIFICATION_MESSAGE=message
        val NOTIFICATION_TYPE="Offer"
        val notificationJs = JSONObject()
        val notificationBodyJs = JSONObject()
        try {
            notificationBodyJs.put("notificationType", NOTIFICATION_TYPE)
            notificationBodyJs.put("notificationTitle", NOTIFICATION_TITLE)
            notificationBodyJs.put("notificationMessage", NOTIFICATION_MESSAGE)
            notificationBodyJs.put("person",selectPerson)
            notificationBodyJs.put("reason",selectReason)
            notificationJs.put("to", NOTIFICATION_TOPIC)
            notificationJs.put("data", notificationBodyJs)
        }catch (e:Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        sendFcmNotification(notificationJs)
    }

    private fun prepareNotificationMessage(
        title: String,
        message: String,
        selectPerson: String,
        selectReason: String
    ) {
        val NOTIFICATION_TOPIC =
            "/topics/PUSH_NOTIFICATIONS"
        val NOTIFICATION_TITLE = title
        val NOTIFICATION_MESSAGE = message
        val NOTIFICATION_TYPE = "AddProduct"
        val notificationJs = JSONObject()
        val notificationBodyJs = JSONObject()
        try {
            notificationBodyJs.put("notificationType", NOTIFICATION_TYPE)
            notificationBodyJs.put("notificationTitle", NOTIFICATION_TITLE)
            notificationBodyJs.put("notificationMessage", NOTIFICATION_MESSAGE)
            notificationBodyJs.put("person",selectPerson)
            notificationBodyJs.put("reason",selectReason)
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