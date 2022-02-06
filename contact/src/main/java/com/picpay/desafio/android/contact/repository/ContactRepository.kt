package com.picpay.desafio.android.contact.repository

import android.util.Log
import com.picpay.desafio.android.contact.User
import com.picpay.desafio.android.coredata.ContactDao
import kotlinx.coroutines.flow.flow

class ContactRepository(
    private val service: PicPayService,
    private val contactDao: ContactDao
) {

    fun getContactList() = flow {
        val localContactList = contactDao.selectContacts()
        when {
            localContactList.isNullOrEmpty() -> {
                Log.e("ContactRepository", "sem cache")
                val newContactList = syncData()
                emit(newContactList)
            }
            else -> {
                Log.e("ContactRepository", "com cache")
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

    private suspend fun syncData(): List<User> {
        val newContactList = service.getUsers()
        val entityContactList = newContactList.map {
            com.picpay.desafio.android.coredata.UserEntity(
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