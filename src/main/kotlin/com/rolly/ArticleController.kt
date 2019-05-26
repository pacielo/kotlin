package com.rolly

import io.ktor.freemarker.FreeMarkerContent

interface ArticleController {
    fun displayArticle(id: Int): Article?

    fun getListArticle(): List<Article>

    fun postComment(articleId: Int, text: String)
}