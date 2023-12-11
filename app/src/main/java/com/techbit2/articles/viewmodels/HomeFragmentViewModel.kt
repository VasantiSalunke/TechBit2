package com.techbit2.articles.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techbit2.articles.models.Article
import com.techbit2.articles.models.ArticleCategory
import com.techbit2.articles.repositories.ArticlesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel(
    private val articlesRepo: ArticlesRepo
) : ViewModel() {


    val articleCategoryList = ArrayList<ArticleCategory>()
    val trendingArticlesList = ArrayList<Article>()
    val articleCategoryLoadedLiveData = MutableLiveData<Boolean>()
    val trendingArticleLoadedLiveData = MutableLiveData<Boolean>()

    var pageNo: Int = 0
    val pageSize: Int = 10

    fun getArticleCategory(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = articlesRepo.getArticlesCategory(pageSize,++pageNo)

            withContext(Dispatchers.Main){
                articleCategoryList.addAll(response!!.data)
                articleCategoryLoadedLiveData.postValue(true)
            }
        }
    }

    fun getTrendingArticles(){

        CoroutineScope(Dispatchers.IO).launch {
            val response = articlesRepo.getTrendingArticles(pageSize,pageNo)

            withContext(Dispatchers.Main){
                trendingArticlesList.addAll(response!!.data)
                trendingArticleLoadedLiveData.postValue(true)
            }
        }
    }
}