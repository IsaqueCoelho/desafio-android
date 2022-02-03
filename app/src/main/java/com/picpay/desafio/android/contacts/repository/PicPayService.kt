package com.picpay.desafio.android.contacts.repository

import com.picpay.desafio.android.contacts.User
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}