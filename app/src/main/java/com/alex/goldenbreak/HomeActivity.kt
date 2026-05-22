package com.alex.goldenbreak

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val username = intent.getStringExtra("username") ?: "USER"
        val tvWelcome = findViewById<TextView>(R.id.textView4)
        tvWelcome.text = "HI, ${username.uppercase()}!"


        val reserveRegular = findViewById<TextView>(R.id.txtReserveRegular)
        reserveRegular.setOnClickListener {
            val intent = Intent(this, RegulerActivity::class.java)
            intent.putExtra("roomType", "Regular")
            startActivity(intent)
        }


        val reserveVip = findViewById<TextView>(R.id.txtReserveVip)
        reserveVip.setOnClickListener {
            val intent = Intent(this, VipActivity::class.java)
            intent.putExtra("roomType", "VIP")
            startActivity(intent)

        }
    }
}
