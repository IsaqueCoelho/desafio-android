package com.picpay.desafio.android.data

import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contactEntity: ContactEntity)

    @Transaction
    @Query("SELECT * FROM contact")
    suspend fun selectContacts(): List<ContactEntity>
}