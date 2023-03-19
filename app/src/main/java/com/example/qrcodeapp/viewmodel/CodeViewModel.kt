package com.example.qrcodeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.qrcodeapp.repository.data.code.CodeDatabase
import com.example.qrcodeapp.model.Code
import com.example.qrcodeapp.repository.CodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Code>>
    private val repository: CodeRepository

    init {
        val codeDao = CodeDatabase.getDatabase(application).codeDao()
        repository = CodeRepository(codeDao)
        readAllData = repository.readAllData
    }

    fun addCode(code: Code) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCode(code)
        }
    }

    fun updateCode(code: Code){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCode(code)
        }
    }

    fun deleteCode(code: Code) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCode(code)
        }
    }

    fun deleteAllCode() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCode()
        }
    }

}