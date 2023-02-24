package com.example.qrcodeapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.qrcodeapp.MainActivity
import com.example.qrcodeapp.data.user.UserDao
import com.example.qrcodeapp.data.user.UserDatabase
import com.example.qrcodeapp.repository.UserRepository
import com.example.qrcodeapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val userDao = UserDatabase.getDatabase(application).userDao()

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user:User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
         repository.deleteAllUsers()
        }
    }

    fun login(email: String, password: String)  {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.getUser(email, password)
             if (user != null) {
                true
            } else {
                false
            }
        }

    }

}