package com.picpay.desafio.android.contacts.repository

import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit

class ContactRepository(
    private val retrofit: Retrofit
) {

    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    fun getContactList() = flow {
        val contactList = service.getUsers()
        emit(contactList)
    }
}