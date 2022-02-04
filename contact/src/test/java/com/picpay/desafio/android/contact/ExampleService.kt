package com.picpay.desafio.android.contact

import com.picpay.desafio.android.contact.repository.PicPayService
import com.picpay.desafio.android.contact.User

class ExampleService(
    private val service: com.picpay.desafio.android.contact.repository.PicPayService
) {

    fun example(): List<com.picpay.desafio.android.contact.User> {
//        val users = service.getUsers().execute()

        return /*users.body() ?:*/ emptyList()
    }
}