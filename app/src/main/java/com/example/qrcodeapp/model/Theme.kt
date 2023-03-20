package com.example.qrcodeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "themes")
data class Theme(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val darkTheme: Int,
    val lightTheme: Int
): Parcelable
