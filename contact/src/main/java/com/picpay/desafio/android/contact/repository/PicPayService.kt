package com.picpay.desafio.android.contact.repository

import com.picpay.desafio.android.contact.User
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<com.picpay.desafio.android.contact.User>
}