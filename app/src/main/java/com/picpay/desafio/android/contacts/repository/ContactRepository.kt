package com.picpay.desafio.android.contacts.repository

import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit

class ContactRepository(
    private val service: PicPayService
) {

    fun getContactList() = flow {
        val contactList = service.getUsers()
        emit(contactList)
    }
}