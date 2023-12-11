package com.techbit2.articles.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techbit2.articles.repositories.ArticleDetailsRepo
import com.techbit2.articles.repositories.ArticlesRepo
import com.techbit2.articles.repositories.Repo
import com.techbit2.articles.viewmodels.ArticleDetailsViewModel
import com.techbit2.articles.viewmodels.ArticlesViewModel
import com.techbit2.articles.viewmodels.HomeFragmentViewModel
import com.techbit2.articles.viewmodels.SearchViewModel

class ViewModelFactory(
    val repo: Repo
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java) && repo is ArticlesRepo) {
            return HomeFragmentViewModel(repo) as T
        }

        if (modelClass.isAssignableFrom(ArticleDetailsViewModel::class.java) && repo is ArticleDetailsRepo){
            return ArticleDetailsViewModel(repo) as T
        }
        if (modelClass.isAssignableFrom(ArticlesViewModel::class.java) && repo is ArticlesRepo){
            return ArticlesViewModel(repo) as T
        }

        if (modelClass.isAssignableFrom(SearchViewModel::class.java) && repo is ArticlesRepo){
            return SearchViewModel(repo) as T
        }

        throw java.lang.Error(" Unable to create view model ")
    }

}