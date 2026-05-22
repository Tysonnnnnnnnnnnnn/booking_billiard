package com.alex.goldenbreak

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegulerActivity : AppCompatActivity() {

    private var selectedTable: Int? = null
    private lateinit var tableButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reguler)

        // ---- AMBIL BUTTON 1–10 ----
        tableButtons = listOf(
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9),
            findViewById(R.id.btn10)
        )

        // ---- SET LISTENER UNTUK MAPPING BTN → TABLE NUMBER 1–10 ----
        tableButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                // index 0 → table 1
                // index 1 → table 2
                // ...
                // index 9 → table 10
                selectTable(index + 1)
            }
        }


        val btnReserve = findViewById<Button>(R.id.btnReserve)
        btnReserve.setOnClickListener {
            if (selectedTable == null) {
                Toast.makeText(this, "Please select a table first", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ReservationActivity::class.java)
                intent.putExtra("roomType", "Reguler")
                intent.putExtra("selectedTable", selectedTable)
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
