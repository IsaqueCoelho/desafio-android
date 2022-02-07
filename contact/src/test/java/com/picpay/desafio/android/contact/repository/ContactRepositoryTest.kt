package com.picpay.desafio.android.contact.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.contact.User
import com.picpay.desafio.android.coredata.ContactDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ContactRepositoryTest {

    private val remoteCache = mock<PicPayService>()
    private val localCache = mock<ContactDao>()

    private val repository = ContactRepository(
        service = remoteCache,
        contactDao = localCache
    )

    @Test
    fun `GIVEN fake list WHEN sync data THEN assert result`(): Unit = runBlocking {
        launch {
            // Given
            val expectedUsers = listOf(
                User(
                    id = 0,
                    name = "test",
                    username = "usernametest",
                    img = "img"
                )
            )

            runBlocking {
                whenever(remoteCache.getUsers()).thenReturn(expectedUsers)
            }

            // When
            remoteCache.getUsers()

            // Then
            assertEquals(
                expectedUsers,
                repository.syncData()
            )
        }
    }
}