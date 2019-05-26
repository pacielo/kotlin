package com.rolly

class CommentControllerImpl(val model: CommentModel) : CommentController {
    override fun deleteComment(commentId: Int) {
        model.deleteComment(commentId)
    }
}