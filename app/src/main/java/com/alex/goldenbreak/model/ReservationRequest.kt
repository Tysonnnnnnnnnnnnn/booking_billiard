package com.alex.goldenbreak.model

data class ReservationRequest(

    val name: String,
    val date: String,
    val time: String,
    val room_type: String,
    val table_qty: Int,
    val playing_hour: Int,
    val total_price: Int
)