package com.techbit2.articles.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techbit2.articles.models.ArticleDetails
import com.techbit2.articles.repositories.ArticleDetailsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleDetailsViewModel (
    private val articleDetailsRepo : ArticleDetailsRepo
) : ViewModel() {

    val articleDetailsLiveData = MutableLiveData<ArticleDetails>()


    fun getArticleDetails(articleId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = articleDetailsRepo.getArticleDetails(articleId)

            withContext(Dispatchers.Main) {
                articleDetailsLiveData.postValue(response!!.data!!)
            }
        }
    }
}