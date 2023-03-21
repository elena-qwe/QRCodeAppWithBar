package com.example.qrcodeapp.repository.data.scan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.qrcodeapp.model.Scan
import com.example.qrcodeapp.repository.data.user.UserDao
import com.example.qrcodeapp.repository.data.user.UserDatabase

@Database(entities = [Scan::class], version = 1)
abstract class ScanDatabase : RoomDatabase() {
    abstract fun scanDao(): ScanDao

    companion object {
        @Volatile
        private var INSTANCE: ScanDatabase? = null

        fun getInstance(context: Context): ScanDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScanDatabase::class.java,
                    "scan_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}