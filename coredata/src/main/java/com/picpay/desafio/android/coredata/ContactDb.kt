package com.picpay.desafio.android.coredata

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class ContactDb : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}