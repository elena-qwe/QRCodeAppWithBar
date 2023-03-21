package com.example.qrcodeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.qrcodeapp.model.Scan
import com.example.qrcodeapp.repository.ScanHistoryRepository
import com.example.qrcodeapp.repository.data.scan.ScanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScanHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ScanHistoryRepository
    val readAllData: LiveData<List<Scan>>

    init {
        val scanDao = ScanDatabase.getInstance(application).scanDao()
        repository = ScanHistoryRepository(scanDao)
        readAllData = repository.readAllData
    }

    fun updateScan(scan: Scan) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateScan(scan)
        }
    }

    fun deleteScan(scan: Scan) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteScan(scan)
        }
    }

    fun deleteAllScans() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllScans()
        }
    }
}