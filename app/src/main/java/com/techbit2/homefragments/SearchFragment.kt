package com.techbit2.homefragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techbit2.R
import com.techbit2.articles.adapters.ArticlesAdapter
import com.techbit2.articles.factory.ViewModelFactory
import com.techbit2.articles.fragments.ArticleDetailsFragment
import com.techbit2.articles.models.Article
import com.techbit2.articles.network.ArticlesService
import com.techbit2.articles.repositories.ArticlesRepo
import com.techbit2.articles.viewmodels.SearchViewModel
import com.techbit2.databinding.SearchViewBinding
import com.techbit2.interviewQuestions.adapters.InterviewQuestionsAdapter
import com.techbit2.interviewQuestions.viewmodels.SearchViewModel2

class SearchFragment : Fragment() {

    private lateinit var binding: SearchViewBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchViewModel2: SearchViewModel2
    private lateinit var InterviewQuestionsAdapter: InterviewQuestionsAdapter
    private lateinit var ArticlesAdapter: ArticlesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SearchViewBinding.inflate(inflater)
        binding.recyclerSearchArticle.setBackgroundColor(Color.WHITE)

        initViewModels()
        initViews()
        initObservers()
        initListener()

        searchViewModel.searchArticles()
        searchViewModel2.searchInterviewQuestions()

        return binding.root

    }

    private fun initViews() {
        binding.recyclerSearchArticle.layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        ArticlesAdapter = ArticlesAdapter(searchViewModel.articlesList)
        binding.recyclerSearchArticle.adapter = ArticlesAdapter


    }

    private fun initViewModels() {

        searchViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ArticlesRepo(ArticlesService.getInstance())
            )
        )[SearchViewModel::class.java]
    }

    private fun initObservers() {
        searchViewModel.articlesLoadedLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                ArticlesAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initListener() {
        ArticlesAdapter.onArticleClicklistener =
            object : ArticlesAdapter.OnArticleClicklistener {
                override fun onArticleClick(article: Article, position: Int) {
                    addArticleDetailsFragment(article)
                    Toast.makeText(context, "$article.id", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addArticleDetailsFragment(article: Article) {

        val articleDetailsFragment = ArticleDetailsFragment()

        val input = Bundle()

        input.putSerializable(Article.KEY_ARTICLE_ID, article.articleId)

        articleDetailsFragment.arguments = input

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, articleDetailsFragment, null)
            .addToBackStack(null)
            .commit()

    }

}