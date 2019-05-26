package com.rolly

interface AdminArticleController {
    fun postArticle(text: String)

    fun deleteArticle(articleId: Int)
}