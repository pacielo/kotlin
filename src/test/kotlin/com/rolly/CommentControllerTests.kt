package com.rolly

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Test

class CommentControllerTests {
    @Test
    fun deleteCommentTest(){

        val model = mock<CommentModel>()
        val controller = CommentControllerImpl(model)

        controller.deleteComment(42)
        verify(model).deleteComment(42)
        verifyNoMoreInteractions(model)

    }
}