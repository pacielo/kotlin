package com.rolly

import com.rolly.AdminArticleModel

class AdminArticleMysqlModel(val pool: ConnectionPool): AdminArticleModel {
    override fun postArticle(text: String){
        pool.useConnection{ conn ->
            conn.prepareStatement("INSERT INTO article (text) VALUES (?)").use { stmt ->
                stmt.setString(1, text)
                stmt.execute()
            }
        }
    }

    override fun deleteArticle(articleId: Int) {
        pool.useConnection{ conn ->
            conn.prepareStatement("DELETE FROM comment WHERE id_article = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.execute()
            }
            conn.prepareStatement("DELETE FROM article WHERE article.id = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.execute()
            }
        }
    }
}