package com.picpay.desafio.android.contacts

import com.picpay.desafio.android.contacts.repository.ContactRepository
import com.picpay.desafio.android.contacts.repository.PicPayService
import com.picpay.desafio.android.data.RETROFIT_BUILDER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import retrofit2.Retrofit

fun Module.injectContacts() {
    viewModel {
        MainViewModel(
            get()
        )
    }

    factory {
        ContactRepository(
            get()
        )
    }

    factory {
        get<Retrofit>(
            named(RETROFIT_BUILDER)
        ).create(PicPayService::class.java)
    }
}