package com.picpay.desafio.android.di

import com.picpay.desafio.android.contacts.injectContacts
import com.picpay.desafio.android.data.injectDataLocalDependencies
import com.picpay.desafio.android.data.injectDataNetworkDependencies
import org.koin.dsl.module

val koinModule = module {

    // inject network
    injectDataNetworkDependencies()

    // inject local database
    injectDataLocalDependencies()

    // inject contacts
    injectContacts()
}