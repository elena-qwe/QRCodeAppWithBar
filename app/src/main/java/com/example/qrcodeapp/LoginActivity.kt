package com.example.qrcodeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.qrcodeapp.databinding.ActivityLoginBinding
import com.example.qrcodeapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.loginBtn.setOnClickListener {
            onLoginClicked()

        }

    }

    private fun onLoginClicked() {
        val email = emailTextField.text.toString()
        val password = passwordTextField.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Введите пароль и логин", Toast.LENGTH_SHORT).show()
            return
        }

        val loggedIn = viewModel.login(email, password)

        if (loggedIn != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {

            Toast.makeText(this, "Неправильно введен логин или пароль", Toast.LENGTH_SHORT).show()

        }
    }


}