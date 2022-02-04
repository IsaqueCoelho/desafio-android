package com.picpay.desafio.android.coredata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class UserEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val img: String,
    val username: String
)