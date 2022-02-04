package com.picpay.desafio.android.contact

interface ViewState

data class ContactUiState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val userList: List<com.picpay.desafio.android.contact.User> = emptyList()
)


sealed class ContactIntent {
    class GetContactList : ContactIntent()
}
