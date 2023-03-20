package com.example.qrcodeapp.viewmodel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.qrcodeapp.repository.data.user.UserDatabase
import com.example.qrcodeapp.repository.UserRepository
import com.example.qrcodeapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository
    val readAllData: LiveData<List<User>>

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

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

    fun getUser(email: String, role: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser(email, role)
        }
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun findByEmailAndPasswordAndRole(email: String, password: String, role: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.findByEmailAndPasswordAndRole(email, password, role)
        }
    }



}





