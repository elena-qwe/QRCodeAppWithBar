package com.example.qrcodeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.qrcodeapp.databinding.ActivityLoginBinding
import com.example.qrcodeapp.viewmodel.UserViewModel



class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {

        }

    }
   /* private fun getSelectedRole(): String {
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
            else -> throw IllegalArgumentException("Invalid role")
        }
    }*/
}



