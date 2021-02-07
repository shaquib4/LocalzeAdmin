package com.example.localzeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.android.volley.Response
import org.json.JSONObject

class ActivityNotification : AppCompatActivity() {
    private lateinit var messageTitle: EditText
    private lateinit var messageText: EditText
    private lateinit var selectedPerson: Spinner
    private lateinit var sendMessage: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity_notification)
        messageTitle = findViewById(R.id.edtTitle)
        messageText = findViewById(R.id.edtMessage)
        selectedPerson = findViewById(R.id.spnSelect)
        sendMessage = findViewById(R.id.btnSend)
        val title = messageTitle.text.toString()
        val message = messageText.text.toString()
        val selected = selectedPerson.selectedItem.toString()
        sendMessage.setOnClickListener {
            prepareNotificationMessage(title, message, selected)
        }
    }

    private fun prepareNotificationMessage(title: String, message: String, selected: String) {
        val NOTIFICATION_TOPIC =
            "/topics/PUSH_NOTIFICATIONS"
        val NOTIFICATION_TITLE = title
        val NOTIFICATION_MESSAGE = message
        val NOTIFICATION_TYPE = "AdminApp"
        val notificationJs = JSONObject()
        val notificationBodyJs = JSONObject()
        try {
            notificationBodyJs.put("notificationType", NOTIFICATION_TYPE)
            notificationBodyJs.put("notificationTitle", NOTIFICATION_TITLE)
            notificationBodyJs.put("notificationMessage", NOTIFICATION_MESSAGE)
            notificationBodyJs.put("selectedPerson", selected)
            notificationJs.put("to", NOTIFICATION_TOPIC)
            notificationJs.put("data", notificationBodyJs)
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        sendFcmNotification(notificationJs)

    }

    private fun sendFcmNotification(notificationJs: JSONObject) {
        val jsonObjectRequest = object : JsonObjectRequest(
            "https://fcm.googleapis.com/fcm/send",
            notificationJs,
            Response.Listener {

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