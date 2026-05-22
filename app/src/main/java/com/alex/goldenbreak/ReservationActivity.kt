package com.alex.goldenbreak

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val roomType = intent.getStringExtra("roomType") ?: "Unknown"
        val selectedTable = intent.getIntExtra("selectedTable", -1)

        val etName = findViewById<EditText>(R.id.etResName)
        // Menggunakan kolom tanggal tunggal yang baru
        val etCheckInDate = findViewById<EditText>(R.id.etCheckInDate)
        val etTime = findViewById<EditText>(R.id.etTime)

        val rbAM = findViewById<RadioButton>(R.id.rbAM)
        val rbPM = findViewById<RadioButton>(R.id.rbPM)

        val tvTableType = findViewById<TextView>(R.id.tvTableType)
        val etTableQty = findViewById<EditText>(R.id.etTableQty)
        val etPlayingHour = findViewById<EditText>(R.id.etPlayingHour)
        val btnContinue = findViewById<Button>(R.id.btnContinue)

        tvTableType.text = roomType

        if (selectedTable != -1) {
            etTableQty.setText(selectedTable.toString())
        }

        // TAMBAHAN: Logika memunculkan Kalender saat kolom tanggal diklik
        etCheckInDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Set teks dengan format standar DD/MM/YYYY (Contoh: 21/05/2026)
                    val formattedDate = String.format(
                        Locale.getDefault(),
                        "%02d/%02d/%d",
                        selectedDay,
                        selectedMonth + 1, // Bulan dimulai dari 0
                        selectedYear
                    )
                    etCheckInDate.setText(formattedDate)
                },
                year,
                month,
                day
            )

            // Batasi pendaftaran: Minimal hari ini (tidak bisa pilih tanggal kemarin)
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }

        btnContinue.setOnClickListener {

            // Validasi diubah untuk mengecek etCheckInDate
            if (
                etName.text.isEmpty() ||
                etCheckInDate.text.isEmpty() ||
                etTime.text.isEmpty() ||
                etTableQty.text.isEmpty() ||
                etPlayingHour.text.isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val name = etName.text.toString()

            // LOGIKA BARU: Memecah string DD/MM/YYYY untuk mencocokkan dengan fungsi getMonthName kamu
            val dateRaw = etCheckInDate.text.toString() // Hasilnya: "21/05/2026"
            val dateParts = dateRaw.split("/")
            val day = dateParts[0]
            val monthNum = dateParts[1].toInt()
            val year = dateParts[2]

            val monthName = getMonthName(monthNum)
            val fullDate = "$day $monthName $year" // Hasilnya tetap: "21 May 2026"

            val timeText = etTime.text.toString()
            val ampm = if (rbAM.isChecked) "AM" else "PM"
            val fullTime = "$timeText $ampm"

            val tableQty = etTableQty.text.toString().toInt()
            val playHours = etPlayingHour.text.toString().toInt()
            val pricePerHour = if (roomType == "Reguler") 40000 else 90000
            val totalPrice = pricePerHour * playHours

            val intent = Intent(this, ProgressActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("date", fullDate)
            intent.putExtra("time", fullTime)
            intent.putExtra("roomType", roomType)
            intent.putExtra("selectedTable", tableQty)
            intent.putExtra("tableQty", 1)
            intent.putExtra("hours", playHours)
            intent.putExtra("pricePerHour", pricePerHour)
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)
        }
    }

    private fun getMonthName(num: Int): String {
        return when (num) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> "-"
        }
    }
}