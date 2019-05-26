package com.rolly

import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ArticleControllerTests {

    @Test
    fun getValidArticleTextTest(){
        val model = mock<ArticleModel>{
            on { getArticleText(42) } doReturn "Text"
            on { getArticleComments(42) } doReturn listOf(Comment(1, "Comment"))
        }


        val controller = ArticleControllerImpl(model)

        val data = controller.displayArticle(42)
        assertEquals(Article(42, "Text", listOf(Comment(1, "Comment"))), data)

        verify(model).getArticleText(42)
        verify(model).getArticleComments(42)
        verifyNoMoreInteractions(model)
    }

    @Test
    fun getInvalidArticleTextTest(){
        val model = mock<ArticleModel>{
            on { getArticleText(42) } doReturn null
        }
        val controller = ArticleControllerImpl(model)

        val data = controller.displayArticle(42)
        assertNull(data)

        verify(model).getArticleText(42)
        verifyNoMoreInteractions(model)
    }

    @Test
    fun postValidCommentTest(){
        val model = mock<ArticleModel>()
        val controller = ArticleControllerImpl(model)

        controller.postComment(42, "Text")

        verify(model).postComment(42, "Text")
        verifyNoMoreInteractions(model)

    }
    @Test
    fun postInvalidCommentTest(){
        val model = mock<ArticleModel>()
        val controller = ArticleControllerImpl(model)

        controller.postComment(42, "")
        controller.postComment(42, " ")
        verifyNoMoreInteractions(model)

    }


}