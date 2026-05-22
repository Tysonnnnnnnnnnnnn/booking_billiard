package com.alex.goldenbreak.model

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)