package com.example.qrcodeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "code_table")
data class Code(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val responsible_firstName: String,
    val responsible_lastName: String,
    val department: String,
    val bit_pic: String,
    val information: String
) : Parcelable