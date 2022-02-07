package com.picpay.desafio.android.contact.repository

import androidx.annotation.VisibleForTesting
import com.picpay.desafio.android.contact.User
import com.picpay.desafio.android.coredata.ContactDao
import com.picpay.desafio.android.coredata.UserEntity
import kotlinx.coroutines.flow.flow

class ContactRepository(
    private val service: PicPayService,
    private val contactDao: ContactDao
) {

    fun getContactList() = flow {
        val localContactList = contactDao.selectContacts()
        when {
            localContactList.isNullOrEmpty() -> {
                val newContactList = syncData()
                emit(newContactList)
            }
            else -> {
                val contactList = localContactList.map {
                    User(
                        id = it.id,
                        name = it.name,
                        username = it.username,
                        img = it.img,
                    )
                }

                emit(contactList)
                syncData()
            }
        }
    }

    @VisibleForTesting
    suspend fun syncData(): List<User> {
        val newContactList = service.getUsers()
        val entityContactList = newContactList.map {
            UserEntity(
                id = it.id,
                name = it.name,
                username = it.username,
                img = it.img,
            )
        }

        contactDao.insertContacts(entityContactList)
        return newContactList
    }
}