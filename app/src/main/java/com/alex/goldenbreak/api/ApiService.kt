package com.alex.goldenbreak.api

import com.alex.goldenbreak.model.LoginRequest
import com.alex.goldenbreak.model.LoginResponse
import com.alex.goldenbreak.model.RegisterRequest
import com.alex.goldenbreak.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import com.alex.goldenbreak.model.ReservationRequest
import com.alex.goldenbreak.model.ReservationResponse

interface ApiService {

    @POST("register")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

    @POST("login")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @POST("reservation")
    fun createReservation(
        @Body request: ReservationRequest
    ): Call<ReservationResponse>


}