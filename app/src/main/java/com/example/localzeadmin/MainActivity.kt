package com.example.localzeadmin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var edt: EditText
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        btn = findViewById(R.id.btnRegButton)
        edt = findViewById(R.id.edtmobiles)
        btn.setOnClickListener {
            subscribeToTopic()
            savePrefData()
            register()
        }
    }

    private fun savePrefData() {
        val pref: SharedPreferences = applicationContext.getSharedPreferences(
            "Enabled",
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putBoolean("isEnabled", true)
        editor.apply()
    }

    private fun subscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("PUSH_NOTIFICATIONS")
            .addOnCompleteListener { task ->
                var msg = "Notifications Are Enabled"
                if (!task.isSuccessful) {
                    msg = "Notifications are not enabled"
                }
            }
    }

    private fun register() {
        val phone = edt.text.trim().toString()
        if (edt.text.trim().toString().isEmpty()) {
            edt.error = "please enter your phone number"
            return
        }
        val intent = Intent(this, Verify::class.java)
        intent.putExtra("phone", phone)
        startActivity(intent)
        finish()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {

            startActivity(Intent(this, ContinueIn::class.java))
            finish()
        }
    }
}