package com.picpay.desafio.android.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ContactEntity::class],
    version = 1
)
abstract class ContactDb: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}