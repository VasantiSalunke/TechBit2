package com.techbit2.articles.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.lang.UCharacter.LineBreak
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techbit2.R
import com.techbit2.articles.adapters.ArticlesAdapter
import com.techbit2.articles.factory.ViewModelFactory
import com.techbit2.articles.models.Article
import com.techbit2.articles.network.ArticlesService
import com.techbit2.articles.repositories.ArticlesRepo
import com.techbit2.articles.viewmodels.ArticlesViewModel
import com.techbit2.databinding.ArticlesFragmentBinding

class ArticlesFragment : Fragment() {

    private lateinit var binding: ArticlesFragmentBinding
    private lateinit var articlesViewModel : ArticlesViewModel
    private lateinit var ArticlesAdapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = ArticlesFragmentBinding.inflate(inflater)
        binding.recyclerArticle.setBackgroundColor(Color.WHITE)


//        val txtArticleTitle : TextView = requireView().findViewById(R.id.txtArticleTitle)
//        txtArticleTitle.text = "First Line\\nSecond Line"


        initViewModels()
        initViews()
        initObservers()
        initListener()

        articlesViewModel.getArticles()

        return binding.root

    }


    private fun initViews() {
        binding.recyclerArticle.layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        ArticlesAdapter = ArticlesAdapter(articlesViewModel.articlesList)
        binding.recyclerArticle.adapter = ArticlesAdapter

    }

    private fun initViewModels() {

        articlesViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ArticlesRepo(ArticlesService.getInstance())
            )
        )[ArticlesViewModel::class.java]
    }

    private fun initObservers() {
        articlesViewModel.articlesLoadedLiveData.observe(
            viewLifecycleOwner
        ){
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
                   // Toast.makeText(context, "$article.id", Toast.LENGTH_SHORT).show()
                }
            }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun addArticleDetailsFragment(article: Article) {

        val articleDetailsFragment = ArticleDetailsFragment()

        val input = Bundle()

        input.putSerializable(Article.KEY_ARTICLE_ID, article.articleId)

//        input.putSerializable("articleId", article)

        articleDetailsFragment.arguments = input

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, articleDetailsFragment, null)
            .addToBackStack(null)
            .commit()

    }




}