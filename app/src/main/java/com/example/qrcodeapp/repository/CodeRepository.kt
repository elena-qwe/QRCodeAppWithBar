package com.example.qrcodeapp.repository

import androidx.lifecycle.LiveData
import com.example.qrcodeapp.repository.data.code.CodeDao
import com.example.qrcodeapp.model.Code

class CodeRepository (private val codeDao: CodeDao) {
    val readAllData: LiveData<List<Code>> = codeDao.readAllData()

    suspend fun addCode(code: Code) {
        codeDao.addCode(code)
    }

    suspend fun updateCode(code: Code){
        codeDao.updateCode(code)
    }

    suspend fun deleteCode(code: Code) {
        codeDao.deleteCode(code)
    }

    suspend fun deleteAllCode() {
        codeDao.deleteAllCode()
    }


}