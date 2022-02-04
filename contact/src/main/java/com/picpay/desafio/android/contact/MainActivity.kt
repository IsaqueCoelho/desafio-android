package com.picpay.desafio.android.contact

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contact.adapter.UserListAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private val viewModel: MainViewModel by inject()

    override fun onResume() {
        super.onResume()

        prepareComponents()
        showUiState()
    }

    private fun prepareComponents() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE

        viewModel.sendIntent(ContactIntent.GetContactList())
    }

    private fun showUiState() {
        lifecycleScope.launch {
            viewModel.contactState.collect { uiState ->

                progressBar.visibility = when {
                    uiState.loading -> View.VISIBLE
                    else -> View.GONE
                }

                adapter.users = uiState.userList

                uiState.error?.let {
                    val message = getString(R.string.error)
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
