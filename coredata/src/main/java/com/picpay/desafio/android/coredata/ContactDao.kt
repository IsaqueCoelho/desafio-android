package com.picpay.desafio.android.coredata

import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(userEntityList: List<UserEntity>)

    @Transaction
    @Query("SELECT * FROM contact")
    suspend fun selectContacts(): List<UserEntity>
}