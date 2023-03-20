package com.example.qrcodeapp.repository

import androidx.lifecycle.LiveData
import com.example.qrcodeapp.repository.data.user.UserDao
import com.example.qrcodeapp.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUser(email: String, role: String): User? {
        return userDao.getUser(email, role)
    }

    suspend fun findByEmailAndPasswordAndRole(email: String, password: String, role: String): User? {
        return userDao.findByEmailAndPasswordAndRole(email, password, role)
    }




}