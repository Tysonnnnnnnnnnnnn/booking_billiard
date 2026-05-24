package com.alex.goldenbreak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alex.goldenbreak.api.RetrofitClient
import com.alex.goldenbreak.model.RegisterRequest
import com.alex.goldenbreak.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        val root = findViewById<ConstraintLayout>(R.id.main)

        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val etEmail = findViewById<EditText>(R.id.etEmail2)
        val etUsername = findViewById<EditText>(R.id.etUsername2)
        val etPassword = findViewById<EditText>(R.id.etPassword2)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        val txtSignIn = findViewById<TextView>(R.id.txtSignIn)

        btnSignUp.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty()) {
                etEmail.error = "Email tidak boleh kosong"
                return@setOnClickListener
            }

            if (!email.contains("@") || !email.contains(".")) {
                etEmail.error = "Email tidak valid"
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                etUsername.error = "Username tidak boleh kosong"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Password tidak boleh kosong"
                return@setOnClickListener
            }

            if (password.length < 6) {
                etPassword.error = "Password minimal 6 karakter"
                return@setOnClickListener
            }

            val request = RegisterRequest(
                name = username,
                email = email,
                password = password
            )

            RetrofitClient.instance.register(request)
                .enqueue(object : Callback<RegisterResponse> {

                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {

                        if (response.isSuccessful) {

                            Toast.makeText(
                                this@SignUpActivity,
                                "Register berhasil",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(
                                    this@SignUpActivity,
                                    SignInActivity::class.java
                                )
                            )

                            finish()

                        } else {

                            Toast.makeText(
                                this@SignUpActivity,
                                "Register gagal",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<RegisterResponse>,
                        t: Throwable
                    ) {

                        Toast.makeText(
                            this@SignUpActivity,
                            "Error: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }

        txtSignIn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    SignInActivity::class.java
                )
            )
        }
    }
}