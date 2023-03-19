package com.example.qrcodeapp.repository.data.code

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.qrcodeapp.model.Code

@Dao
interface CodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCode(code: Code)

    @Update
    suspend fun updateCode(code: Code)

    @Delete
    suspend fun deleteCode(code: Code)

    @Query("DELETE FROM code_table")
    suspend fun deleteAllCode()

    @Query("SELECT * FROM code_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Code>>




}