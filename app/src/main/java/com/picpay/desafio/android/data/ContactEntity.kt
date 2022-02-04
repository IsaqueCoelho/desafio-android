package com.picpay.desafio.android.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val img: String,
    val username: String
)