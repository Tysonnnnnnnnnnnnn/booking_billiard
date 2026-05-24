package com.alex.goldenbreak.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)