package com.example.qrcodeapp.data.code

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.qrcodeapp.data.user.UserDao
import com.example.qrcodeapp.model.Code
import com.example.qrcodeapp.model.User

@Database(entities = [Code::class], version = 1, exportSchema = false)
abstract class CodeDatabase: RoomDatabase() {

    abstract fun codeDao(): CodeDao

    companion object {
        @Volatile
        private var INSTANCE: CodeDatabase? = null

        fun getDatabase(context: Context): CodeDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CodeDatabase::class.java,
                    "code_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}