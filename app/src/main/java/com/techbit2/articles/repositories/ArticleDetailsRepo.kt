package com.techbit2.articles.repositories

import com.techbit2.ApiResponse
import com.techbit2.articles.models.ArticleDetails
import com.techbit2.articles.network.ArticlesService.Companion.articlesService

class ArticleDetailsRepo (): Repo() {

    suspend fun getArticleDetails(

        articleId : Int

    ) : ApiResponse.Success<ArticleDetails>? {

        return articlesService?.getArticleDetails(articleId)

    }

}