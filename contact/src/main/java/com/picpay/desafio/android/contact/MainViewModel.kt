package com.picpay.desafio.android.contact

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.contact.repository.ContactRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ContactRepository
) : ViewModel() {

    @VisibleForTesting
    val intentChannel = Channel<ContactIntent>(Channel.UNLIMITED)

    private val _contactState = MutableStateFlow(ContactUiState(loading = false))
    val contactState: StateFlow<ContactUiState> = _contactState.asStateFlow()

    fun sendIntent(intent: ContactIntent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    fun handleIntents() {
        intentChannel
            .consumeAsFlow()
            .onEach { intent ->
                when (intent) {
                    is ContactIntent.GetContactList -> {
                        getUsers()
                    }
                }
            }.launchIn(viewModelScope)
    }


    @VisibleForTesting
    suspend fun getUsers() {
        _contactState.emit(
            ContactUiState(
                loading = true
            )
        )
        repository.getContactList().collect {
            _contactState.emit(
                ContactUiState(
                    loading = false,
                    userList = it
                )
            )
        }
    }

}