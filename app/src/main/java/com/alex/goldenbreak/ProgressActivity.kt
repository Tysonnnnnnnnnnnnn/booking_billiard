package com.alex.goldenbreak

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        // --- DATA DARI RESERVATION ---
        val name = intent.getStringExtra("name") ?: "-"
        val date = intent.getStringExtra("date") ?: "-"
        val time = intent.getStringExtra("time") ?: "-"
        val roomType = intent.getStringExtra("roomType") ?: "-"
        val tableNumber = intent.getIntExtra("selectedTable", -1)
        val tableQty = intent.getIntExtra("tableQty", 1)
        val hours = intent.getIntExtra("hours", 1)
        val pricePerHour = intent.getIntExtra("pricePerHour", 0)
        val totalPrice = intent.getIntExtra("totalPrice", 0)

        val tvTotalPrice = findViewById<TextView>(R.id.tvTotalPrice)
        tvTotalPrice.text = "Rp $totalPrice"

        // --- COUNTDOWN VIEWS ---
        val tvHours = findViewById<TextView>(R.id.tvHours)
        val tvMinutes = findViewById<TextView>(R.id.tvMinutes)
        val tvSeconds = findViewById<TextView>(R.id.tvSeconds)
        val tvDeadline = findViewById<TextView>(R.id.tvDeadline)

        tvDeadline.text = "(Before $date , $time WIB)"

        val totalMillis = 1 * 60 * 60 * 1000L

        object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(ms: Long) {
                val totalSec = ms / 1000
                val hoursLeft = totalSec / 3600
                val minutesLeft = (totalSec % 3600) / 60
                val secondsLeft = totalSec % 60

                tvHours.text = hoursLeft.toString()
                tvMinutes.text = String.format("%02d", minutesLeft)
                tvSeconds.text = String.format("%02d", secondsLeft)
            }

            override fun onFinish() {
                tvHours.text = "0"
                tvMinutes.text = "00"
                tvSeconds.text = "00"
            }
        }.start()

        val btn = findViewById<Button>(R.id.btnUpdateStatus)

        btn.setOnClickListener {
            val intent = Intent(this, ReservationTicketActivity::class.java)

            intent.putExtra("name", name)
            intent.putExtra("date", date)
            intent.putExtra("time", time)
            intent.putExtra("roomType", roomType)
            intent.putExtra("tableNumber", tableNumber)
            intent.putExtra("tableQty", tableQty)
            intent.putExtra("hours", hours)
            intent.putExtra("pricePerHour", pricePerHour)
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)
        }
    }
}
