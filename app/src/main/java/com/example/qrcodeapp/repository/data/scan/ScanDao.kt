package com.example.qrcodeapp.repository.data.scan

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.qrcodeapp.model.Scan
import com.example.qrcodeapp.model.User

@Dao
interface ScanDao {
    @Query("SELECT * FROM scans")
    fun getAll(): List<Scan>

    @Query("SELECT * FROM scans ORDER BY id ASC")
    fun readAllData(): LiveData<List<Scan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(scan: Scan)

    @Update
    suspend fun updateScan(scan: Scan)

    @Delete
    suspend fun deleteScan(scan: Scan)

    @Query("DELETE FROM scans")
    suspend fun deleteAllScans()
}