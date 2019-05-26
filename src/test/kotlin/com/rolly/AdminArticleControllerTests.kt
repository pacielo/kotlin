package com.rolly

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Test

class AdminArticleControllerTests {
    @Test
    fun postValidArticle() {
        val model = mock<AdminArticleModel>()
        val controller = AdminArticleControllerImpl(model)

        controller.postArticle("Text")

        verify(model).postArticle("Text")
        verifyNoMoreInteractions(model)
    }

    @Test
    fun postInvalidArticle() {
        val model = mock<AdminArticleModel>()
        val controller = AdminArticleControllerImpl(model)

        controller.postArticle("")
        controller.postArticle("  ")

        verifyNoMoreInteractions(model)
    }

    @Test
    fun deleteArticleTest() {
        val model = mock<AdminArticleModel>()
        val controller = AdminArticleControllerImpl(model)

        controller.deleteArticle(42)
        verify(model).deleteArticle(42)
        verifyNoMoreInteractions(model)
    }
}