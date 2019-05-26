package com.rolly

@Suppress("unused")
data class Article(val id: Int, var text: String, var comments: List<Comment>)