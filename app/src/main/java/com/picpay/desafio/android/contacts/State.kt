package com.picpay.desafio.android.contacts

interface ViewState

data class ContactUiState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val userList: List<User> = emptyList()
)


sealed class ContactIntent {
    class GetContactList : ContactIntent()
}
