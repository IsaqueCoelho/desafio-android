package com.picpay.desafio.android.contact

import com.picpay.desafio.android.contact.repository.PicPayService

class ExampleService(
    private val service: PicPayService
) {
    suspend fun example(): List<User> {
        return service.getUsers()
    }
}