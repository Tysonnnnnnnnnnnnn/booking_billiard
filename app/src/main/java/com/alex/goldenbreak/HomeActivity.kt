package com.alex.goldenbreak

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // ==========================================
        // LIKING / LOGIKA BAWAAN (TIDAK BERUBAH)
        // ==========================================
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

        // ==========================================
        // LOGIKA BARU: BOTTOM NAVIGATION BAR
        // ==========================================

        // Karena ini ada di HomeActivity, klik tab Home cukup scroll ke atas atau kosongi
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        navHome.setOnClickListener {
            // Opsional: Tambahkan feedback visual jika diperlukan saat tab diklik kembali
        }

        // Pindah ke Halaman Reservations
        val navReservations = findViewById<LinearLayout>(R.id.navReservations)
        navReservations.setOnClickListener {
            // Ganti 'ReservationActivity::class.java' sesuai dengan nama activity-mu
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0) // Menghilangkan animasi transisi agar kerasa seperti tab sungguhan
        }

        // Pindah ke Halaman Info
        val navInfo = findViewById<LinearLayout>(R.id.navInfo)
        navInfo.setOnClickListener {
            // Ganti 'InfoActivity::class.java' sesuai dengan nama activity-mu
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        // Pindah ke Halaman Profil
        val navProfil = findViewById<LinearLayout>(R.id.navProfil)
        navProfil.setOnClickListener {
            // Ganti 'ProfileActivity::class.java' sesuai dengan nama activity-mu
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}