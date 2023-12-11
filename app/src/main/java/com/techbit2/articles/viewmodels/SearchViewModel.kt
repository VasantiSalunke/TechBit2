package com.techbit2.articles.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techbit2.articles.models.Article
import com.techbit2.articles.repositories.ArticlesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel (
    private val articlesRepo: ArticlesRepo
) : ViewModel() {
    val articlesList = arrayListOf<Article>()
    val articlesLoadedLiveData = MutableLiveData<Boolean>()


    fun searchArticles() {

        CoroutineScope(Dispatchers.IO).launch {

            val response = articlesRepo.getArticles(pageSize = 20, pageNo = 1)
            withContext(Dispatchers.Main) {
                articlesList.addAll(response!!.data)
                articlesLoadedLiveData.postValue(true)

            }
        }
    }
}