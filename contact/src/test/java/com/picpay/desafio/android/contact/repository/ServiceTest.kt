package com.picpay.desafio.android.contact.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.contact.User
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun `GIVEN emptlist WHEN calling for users THEN assert result`() {
        // given
        val expectedUsers = emptyList<User>()

        runBlocking {
            whenever(api.getUsers()).thenReturn(expectedUsers)
        }

        // when
        val users = runBlocking {
            service.example()
        }

        // then
        assertEquals(users, expectedUsers)
    }

    @Test
    fun `GIVEN expected result WHEN calc 2 + 2 THEN assert 4`() {
        // Given
        val expectedResult = 4

        // When
        val calc = runBlocking {
            2 + 2
        }

        // Then
        assertEquals(calc, expectedResult)
    }
}