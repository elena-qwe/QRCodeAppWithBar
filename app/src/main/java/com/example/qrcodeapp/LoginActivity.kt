package com.example.qrcodeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.qrcodeapp.databinding.ActivityLoginBinding
import com.example.qrcodeapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        /*db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "user_database"
        ).build()*/

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {

            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val role = when (binding.roleRadioGroup.checkedRadioButtonId) {
                R.id.adminRadioButton -> "admin"
                R.id.employeeRadioButton -> "user"
                else -> null
            }

            if (role != null) {
                val user = mUserViewModel.findByEmailAndPasswordAndRole(email, password, role)
                if (user != null) {
                    when (role) {
                        "admin" -> startActivity(Intent(this, AdminActivity::class.java))
                        "user" -> startActivity(Intent(this, HomeActivity::class.java))
                    }
                } else {
                    Toast.makeText(this, "Неправильно введена почта, пароль или роль", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Выберите пожалуйста роль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}




