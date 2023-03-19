package com.example.qrcodeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.qrcodeapp.databinding.ActivityLoginBinding
import com.example.qrcodeapp.databinding.ActivityRegistrationBinding
import com.example.qrcodeapp.model.User
import com.example.qrcodeapp.viewmodel.UserViewModel

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val role = getSelectedRole()

            if (email.isNotEmpty() && password.isNotEmpty() && role.isNotEmpty()) {
                val existingUser = viewModel.getUser(email, role)

                if (existingUser == null) {
                    val user = User(email = email, password = password, role = role)
                    viewModel.insertUser(user)

                    val intent = Intent(this, getWelcomeActivityByRole(role)::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Такой пользователь уже зарегистрирован", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Пожалуйста заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getSelectedRole(): String {
        return when (binding.roleRadioGroup.checkedRadioButtonId) {
            R.id.adminRadioButton -> "admin"
            R.id.employeeRadioButton -> "user"
            else -> ""
        }
    }

    private fun getWelcomeActivityByRole(role: String): Class<out AppCompatActivity> {
        return when (role) {
            "admin" -> AdminActivity::class.java
            "user" -> HomeActivity::class.java
            else -> throw IllegalArgumentException("Ошибка роли")
        }
    }
}