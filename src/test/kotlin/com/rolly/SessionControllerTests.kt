package com.rolly


import org.junit.Test
import kotlin.test.assertEquals
import com.rolly.SessionControllerImpl
import com.rolly.UserSession

class SessionControllerTests {
    @Test
    fun sessionExistsTest(){

        val controller = SessionControllerImpl

        val res = controller.sessionExists(UserSession("admin"))
        assertEquals("admin", res)

    }
    @Test
    fun sessionNoexistsTest(){

        val controller = SessionControllerImpl

        val res = controller.sessionExists(null)
        assertEquals(null, res)

    }
}