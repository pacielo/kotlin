package com.rolly

import io.ktor.http.HttpStatusCode

class ArticleMysqlModel(val pool: ConnectionPool) : ArticleModel {
    override fun getListArticle(): List<Article> {
        var articles: List<Article> = emptyList()
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT * FROM article").use { stmt ->
                stmt.executeQuery().use { results ->
                    while (results.next()) {
                        articles += Article(results.getInt(1), results.getString(2), emptyList())
                    }
                }
            }
        }
        return articles
    }

    override fun getArticleText(articleId: Int): String?{
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT * FROM article WHERE id = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.execute()
                val rs = stmt.resultSet
                if (rs.next())
                    return rs.getString("text")
            }

        }
        return null
    }
    override fun getArticleComments(articleId: Int): List<Comment>{
        var commentaires: List<Comment> = emptyList()
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT * FROM comment WHERE id_article = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.executeQuery().use { results ->
                    while (results.next()) {
                        commentaires += Comment(results.getInt(1), results.getString(2))
                    }
                }
            }
        }
        return commentaires
    }
    override fun postComment(articleId: Int, text: String){
        pool.useConnection{ conn ->
            conn.prepareStatement("INSERT INTO comment (text, id_article) VALUES (?, ?)").use { stmt ->
                stmt.setString(1, text)
                stmt.setInt(2, articleId)
                stmt.execute()
            }
        }
    }
}