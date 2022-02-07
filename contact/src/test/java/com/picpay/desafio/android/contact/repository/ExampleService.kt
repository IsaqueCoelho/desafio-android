package com.picpay.desafio.android.contact.repository

import com.picpay.desafio.android.contact.User

class ExampleService(
    private val service: PicPayService
) {
    suspend fun example(): List<User> {
        return service.getUsers()
    }
}