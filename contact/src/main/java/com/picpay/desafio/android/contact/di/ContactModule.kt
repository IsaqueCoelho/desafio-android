package com.picpay.desafio.android.contact.di

import com.picpay.desafio.android.contact.MainViewModel
import com.picpay.desafio.android.contact.repository.ContactRepository
import com.picpay.desafio.android.contact.repository.PicPayService
import com.picpay.desafio.android.coredata.RETROFIT_BUILDER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val contactModule = module {

    viewModel {
        com.picpay.desafio.android.contact.MainViewModel(
            repository = get()
        )
    }

    factory {
        com.picpay.desafio.android.contact.repository.ContactRepository(
            service = get(),
            contactDao = get()
        )
    }

    factory {
        get<Retrofit>(
            named(RETROFIT_BUILDER)
        ).create(com.picpay.desafio.android.contact.repository.PicPayService::class.java)
    }
}