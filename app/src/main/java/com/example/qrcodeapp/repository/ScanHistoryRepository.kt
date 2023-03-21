package com.example.qrcodeapp.repository

import androidx.lifecycle.LiveData
import com.example.qrcodeapp.model.Scan
import com.example.qrcodeapp.repository.data.scan.ScanDao

class ScanHistoryRepository(private val scanDao: ScanDao) {

    val readAllData: LiveData<List<Scan>> = scanDao.readAllData()

    suspend fun insert(scan: Scan) {
        scanDao.insert(scan)
    }

    suspend fun updateScan(scan: Scan){
        scanDao.updateScan(scan)
    }

    suspend fun deleteScan(scan: Scan) {
        scanDao.deleteScan(scan)
    }

    suspend fun deleteAllScans() {
        scanDao.deleteAllScans()
    }


}
