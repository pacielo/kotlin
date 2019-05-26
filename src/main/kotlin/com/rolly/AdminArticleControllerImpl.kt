package com.rolly

class AdminArticleControllerImpl(val model: AdminArticleModel) : AdminArticleController {
    override fun postArticle(text: String) {
        if (text.isNotBlank())
            model.postArticle(text)
    }

    override fun deleteArticle(articleId: Int) {
        model.deleteArticle(articleId)
    }
}