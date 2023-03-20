package com.example.qrcodeapp.repository.data.user

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.qrcodeapp.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM users ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND role = :role")
    suspend fun getUser(email: String, role: String): User?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password AND role = :role")
    fun findByEmailAndPasswordAndRole(email: String, password: String, role: String): User?
}