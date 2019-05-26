package com.rolly

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.rolly.UserControllerImpl
import com.rolly.UserModel
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Test
import kotlin.test.assertEquals

class UserControllerTests {
    @Test
    fun logValidUser(){
        val model = mock<UserModel>{
            on { logUser("admin", "password") } doReturn true
        }

        val controller = UserControllerImpl(model)

        val res = controller.logUser("admin", "password")
        assertEquals(true, res)

        verify(model).logUser("admin", "password")
        verifyNoMoreInteractions(model)
    }

    @Test
    fun logInvalidUser(){
        val model = mock<UserModel>{
            on { logUser("admin", "password") } doReturn false
        }

        val controller = UserControllerImpl(model)

        val res = controller.logUser("admin", "password")
        assertEquals(false, res)

        verify(model).logUser("admin", "password")
        verifyNoMoreInteractions(model)
    }
}