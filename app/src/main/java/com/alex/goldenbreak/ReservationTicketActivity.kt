    package com.alex.goldenbreak

    import android.content.Intent
    import android.os.Bundle
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity

    class ReservationTicketActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_reservation_ticket)


            val tvName = findViewById<TextView>(R.id.tvName)
            val tvDate = findViewById<TextView>(R.id.tvDate)
            val tvTime = findViewById<TextView>(R.id.tvTime)
            val tvRoomType = findViewById<TextView>(R.id.tvRoomType)
            val tvQuantity = findViewById<TextView>(R.id.tvQuantity)
            val tvHours = findViewById<TextView>(R.id.tvHours)
            val tvTotal = findViewById<TextView>(R.id.tvTotal)
            val tvBreakdown = findViewById<TextView>(R.id.tvBreakdown)


            val name = intent.getStringExtra("name") ?: "-"
            val date = intent.getStringExtra("date") ?: "-"
            val time = intent.getStringExtra("time") ?: "-"
            val roomType = intent.getStringExtra("roomType") ?: "-"
            val quantity = intent.getIntExtra("quantity", 0)
            val hours = intent.getIntExtra("hours", 0)
            val totalPrice = intent.getIntExtra("totalPrice", 0)
            val pricePerHour = intent.getIntExtra("pricePerHour", 0)

            val txtInfo = findViewById<TextView>(R.id.txtInfo)
            txtInfo.setOnClickListener {
                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }


            tvName.text = name
            tvDate.text = date
            tvTime.text = time
            tvRoomType.text = roomType
            tvQuantity.text = "$quantity Table"
            tvHours.text = "$hours Hours"

            tvTotal.text = "Rp %,d".format(totalPrice).replace(",", ".")
            tvBreakdown.text = "Rp %,d × $hours hours".format(pricePerHour).replace(",", ".")
        }
    }
