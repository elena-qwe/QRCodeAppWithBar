package com.example.qrcodeapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qrcodeapp.R
import com.example.qrcodeapp.model.User
import com.example.qrcodeapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstname = firstname_et.editText?.text.toString()
        val lastname = lastname_et.editText?.text.toString()
        val department = department_et.editText?.text.toString()
        val email = login_et.editText?.text.toString()
        val password = password_et.editText?.text.toString()


        if (inputCheck(firstname, lastname, department, email, password)) {
            //create user object
            val user = User(0, firstname, lastname, department, email, password)
            //add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(
                requireContext(),
                "Пользователь успешно зарегестрирован",
                Toast.LENGTH_LONG
            ).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(
                requireContext(), "Пожалуйста заполните все поля", Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun inputCheck(
        firstname: String,
        lastname: String,
        department: String,
        email: String,
        password: String
    ): Boolean {
        return !(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(
            department
        ) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
    }


}