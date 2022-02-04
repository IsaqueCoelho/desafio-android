package com.picpay.desafio.android.di

import com.picpay.desafio.android.contacts.MainViewModel
import com.picpay.desafio.android.contacts.repository.ContactRepository
import com.picpay.desafio.android.contacts.repository.PicPayService
import com.picpay.desafio.android.coredata.RETROFIT_BUILDER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val contactModule = module {

    viewModel {
        MainViewModel(
            repository = get()
        )
    }

    factory {
        ContactRepository(
            service = get(),
            contactDao = get()
        )
    }

    factory {
        get<Retrofit>(
            named(RETROFIT_BUILDER)
        ).create(PicPayService::class.java)
    }
}