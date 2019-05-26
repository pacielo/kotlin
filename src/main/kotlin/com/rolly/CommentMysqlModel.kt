package com.rolly

class CommentMysqlModel(val pool: ConnectionPool) : CommentModel {
    override fun deleteComment(commentId: Int) {
        pool.useConnection{ conn ->
            conn.prepareStatement("DELETE FROM comment WHERE comment.id = ?").use { stmt ->
                stmt.setInt(1, commentId)
                stmt.execute()
            }
        }
    }
}