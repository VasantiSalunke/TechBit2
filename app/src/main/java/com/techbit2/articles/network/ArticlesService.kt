package com.techbit2.articles.network

import com.techbit2.ApiResponse
import com.techbit2.articles.models.Article
import com.techbit2.articles.models.ArticleCategory
import com.techbit2.articles.models.ArticleDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesService {

    @GET("articles/{pageSize}/{pageNo}")
    suspend fun getArticles(
        @Path("pageSize") pageSize : Int,
        @Path("pageNo") pageNo : Int
    ) : ApiResponse.Success<ArrayList<Article>>

    @GET("articles/{pageSize}/{pageNo}")
    suspend fun getArticlesCategory(
        @Path("pageSize") pageSize : Int,
        @Path("pageNo") pageNo : Int
    ) : ApiResponse.Success<ArrayList<ArticleCategory>>

    @GET("articles/{pageSize}/{pageNo}")
    suspend fun getTrendingArticles(
        @Path("pageSize") pageSize : Int,
        @Path("pageNo") pageNo : Int
    ) : ApiResponse.Success<ArrayList<Article>>

    @GET("articles/{articleId}")
    suspend fun getArticleDetails(
        @Path("articleId") articleId : Int
    ) : ApiResponse.Success<ArticleDetails>

    companion object {
        var articlesService : ArticlesService? = null

        fun getInstance() : ArticlesService {

            if (articlesService == null) {
                articlesService =
                    Retrofit.Builder()
                        .baseUrl("http://3.7.73.200:4002/apis/")
                        .addConverterFactory(
                            GsonConverterFactory.create()
                        ).build().create(ArticlesService::class.java)

            }

            return articlesService!!

        }
    }
}