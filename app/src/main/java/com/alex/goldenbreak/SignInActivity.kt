package com.alex.goldenbreak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView // Ditambahkan untuk TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alex.goldenbreak.api.RetrofitClient
import com.alex.goldenbreak.model.LoginRequest
import com.alex.goldenbreak.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val etUsername = findViewById<EditText>(R.id.etUsername3)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        // =========================================================================
        // PERBAIKAN TAMBAHAN: Menghubungkan TextView dan memberi aksi Klik
        // =========================================================================
        val tvToSignUp = findViewById<TextView>(R.id.tvGoToSignUp) // Pastikan ID di XML activity_sign_in adalah tvToSignUp

        tvToSignUp.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
            // finish() // Boleh diaktifkan jika tidak ingin user bisa back lagi ke halaman login setelah di halaman daftar
        }
        // =========================================================================

        btnSignIn.setOnClickListener {

            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty()) {
                etUsername.error = "Masukkan username"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Masukkan password"
                return@setOnClickListener
            }

            val request = LoginRequest(
                username = username,
                password = password
            )

            RetrofitClient.instance.login(request)
                .enqueue(object : Callback<LoginResponse> {

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {

                        if (response.isSuccessful) {

                            Toast.makeText(
                                this@SignInActivity,
                                "Login berhasil",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(
                                this@SignInActivity,
                                HomeActivity::class.java
                            )

                            intent.putExtra("username", username)

                            startActivity(intent)

                            finish()

                        } else {

                            Toast.makeText(
                                this@SignInActivity,
                                "Username atau Password salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<LoginResponse>,
                        t: Throwable
                    ) {

                        Toast.makeText(
                            this@SignInActivity,
                            "Error: ${t.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }
}