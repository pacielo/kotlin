package com.rolly


class ArticleControllerImpl(val model: ArticleModel) : ArticleController {
    override fun getListArticle(): List<Article> {
        return model.getListArticle()
    }

    override fun displayArticle(id: Int): Article?{
        val text = model.getArticleText(id) ?: return null
        val comments = model.getArticleComments(id)
        return Article(id, text, comments)
    }
    override fun postComment(articleId: Int, text: String){
        if (text.isNotBlank())
            model.postComment(articleId, text)
    }

}