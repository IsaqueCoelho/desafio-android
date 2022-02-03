package com.picpay.desafio.android.contacts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel(
    private val retrofit: Retrofit
) : ViewModel() {

    private val intentChannel = Channel<ContactIntent>(Channel.UNLIMITED)

    private val _contactState = MutableStateFlow(ContactUiState(loading = false))
    val contactState: StateFlow<ContactUiState> = _contactState.asStateFlow()

    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    init {
        handleIntents()
    }

    fun sendIntent(intent: ContactIntent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    private fun handleIntents() {
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


    fun getUsers() {
        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    val message = getString(R.string.error)
//
//                    progressBar.visibility = View.GONE
//                    recyclerView.visibility = View.GONE
//
//                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
//                        .show()

                    Log.e("MainViewModel", "on Failure")
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    progressBar.visibility = View.GONE
//
//                    adapter.users = response.body()!!
                    Log.e("MainViewModel", "on Success")
                }
            })
    }

}