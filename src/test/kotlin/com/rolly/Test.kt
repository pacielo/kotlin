package com.rolly
import org.junit.Test
import kotlin.test.assertEquals


internal class FirstJUnit5Tests {

    @Test
    fun myFirstTest() {
        assertEquals(2, 1 + 1, "Faux")
    }

}
