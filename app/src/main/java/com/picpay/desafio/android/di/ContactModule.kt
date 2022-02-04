package com.picpay.desafio.android.di

import com.picpay.desafio.android.contacts.injectContacts
import org.koin.dsl.module

val contactModule = module {

    // inject contacts
    injectContacts()
}