package com.rolly

interface ArticleModel {
    fun getArticleText(articleId: Int): String?

    fun getArticleComments(articleId: Int): List<Comment>

    fun getListArticle(): List<Article>

    fun postComment(articleId: Int, text: String)
}