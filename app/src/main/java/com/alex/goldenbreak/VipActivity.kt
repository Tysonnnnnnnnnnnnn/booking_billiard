package com.alex.goldenbreak

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VipActivity : AppCompatActivity() {

    private var selectedTable: Int? = null
    private lateinit var tableButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vip)

        // ---- AMBIL BUTTON 11–20 ----
        tableButtons = listOf(
            findViewById(R.id.btn11),
            findViewById(R.id.btn12),
            findViewById(R.id.btn13),
            findViewById(R.id.btn14),
            findViewById(R.id.btn15),
            findViewById(R.id.btn16),
            findViewById(R.id.btn17),
            findViewById(R.id.btn18),
            findViewById(R.id.btn19),
            findViewById(R.id.btn20)
        )

        tableButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                selectTable(index + 1)
            }
        }


        val btnReserve = findViewById<Button>(R.id.btnReserve)
        btnReserve.setOnClickListener {
            if (selectedTable == null) {
                Toast.makeText(this, "Please select a table first", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ReservationActivity::class.java)
                intent.putExtra("roomType", "VIP")
                intent.putExtra("selectedTable", selectedTable) // kirim 1–10
                startActivity(intent)
            }
        }
    }


    private fun selectTable(table: Int) {
        selectedTable = table


        tableButtons.forEach {
            it.setBackgroundColor(Color.parseColor("#6200EE"))
            it.setTextColor(Color.WHITE)
        }


        val selectedBtn = tableButtons[table - 1]
        selectedBtn.setBackgroundColor(Color.parseColor("#003366"))
        selectedBtn.setTextColor(Color.WHITE)
    }
}
