package com.picpay.desafio.android.coredata

import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(userEntityList: List<com.picpay.desafio.android.coredata.UserEntity>)

    @Transaction
    @Query("SELECT * FROM contact")
    suspend fun selectContacts(): List<com.picpay.desafio.android.coredata.UserEntity>
}