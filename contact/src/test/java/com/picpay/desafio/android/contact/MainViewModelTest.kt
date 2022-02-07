package com.picpay.desafio.android.contact

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.contact.repository.ContactRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MainViewModelTest {

    @Mock
    private val repository: ContactRepository = mock()

    private val viewModel = MainViewModel(
        repository = repository
    )

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `GIVEN intent WHEN sending intents THEN assert intent`(): Unit = runBlocking {

        launch(Dispatchers.Main) {
            // Given
            val intent = ContactIntent.GetContactList()

            // When
            viewModel.sendIntent(intent)

            // Then
            val state = viewModel.intentChannel.receive()
            assertEquals(intent, state)
        }

    }

    @Test
    fun `GIVEN fake contact list WHEN getting for user THEN assert contact state`(): Unit =
        runBlocking {
            launch(Dispatchers.Main) {
                // Given
                val expecteduserList = listOf(
                    User(
                        id = 0,
                        name = "test",
                        username = "usernametest",
                        img = "img"
                    )
                )
                val expectedContactState = ContactUiState(
                    loading = false,
                    userList = expecteduserList
                )

                whenever(repository.getContactList()).thenReturn(flow { emit(expecteduserList) })

                // When
                viewModel.getUsers()

                // Then
                assertEquals(
                    expectedContactState,
                    viewModel.contactState.value
                )
            }
        }
}
