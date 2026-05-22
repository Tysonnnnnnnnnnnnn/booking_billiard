package com.alex.goldenbreak

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        // Bind Views
        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        val tvHoursDetail = findViewById<TextView>(R.id.tvHoursDetail)
        val tvServices = findViewById<TextView>(R.id.tvServices)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)

        // Set default text (opsional, bisa diganti kapan saja)
        tvAddress.text = "Jl. Gajah Mada No.541, Kuta, Badung, Bali"
        tvHoursDetail.text = "10.00 AM – 11.00 PM"
        tvServices.text = """
            • Regular Tables
            • VIP Room
            • Cue & Ball Rental
            • Drinks & Snacks
            • Membership Benefits
        """.trimIndent()

        // --- WA Click Action ---
        tvPhone.setOnClickListener {
            val phone = "6288578325997" // Format WA harus 62
            val url = "https://wa.me/$phone"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}
