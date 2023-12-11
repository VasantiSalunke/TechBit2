package com.techbit2.articles.repositories

import com.techbit2.ApiResponse
import com.techbit2.articles.models.Article
import com.techbit2.articles.models.ArticleCategory
import com.techbit2.articles.network.ArticlesService

class ArticlesRepo(
    private val articlesService : ArticlesService
)
    : Repo() {

    suspend fun getArticlesCategory(
        pageSize: Int,
        pageNo: Int
    ): ApiResponse.Success<ArrayList<ArticleCategory>> {
        return articlesService.getArticlesCategory(pageSize, pageNo)
    }

    suspend fun getTrendingArticles(
        pageSize: Int,
        pageNo: Int
    ): ApiResponse.Success<ArrayList<Article>>? {
        return articlesService.getTrendingArticles(pageSize, pageNo)

    }

    suspend fun getArticles(
        pageSize: Int,
        pageNo: Int
    ): ApiResponse.Success<ArrayList<Article>> {
        return articlesService.getArticles(pageSize, pageNo)

    }
}