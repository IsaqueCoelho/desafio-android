package com.picpay.desafio.android.coredata

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [com.picpay.desafio.android.coredata.UserEntity::class],
    version = 1
)
abstract class ContactDb: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}