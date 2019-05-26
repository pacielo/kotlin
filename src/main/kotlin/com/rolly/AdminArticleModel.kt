package com.rolly

interface AdminArticleModel {
    fun postArticle(text: String)

    fun deleteArticle(articleId: Int)
}