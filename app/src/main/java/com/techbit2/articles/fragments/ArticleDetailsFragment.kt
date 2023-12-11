package com.techbit2.articles.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.techbit2.R
import com.techbit2.articles.factory.ViewModelFactory
import com.techbit2.articles.models.Article
import com.techbit2.articles.repositories.ArticleDetailsRepo
import com.techbit2.articles.viewmodels.ArticleDetailsViewModel
import com.techbit2.databinding.ArticleDetailsFragmentBinding

class ArticleDetailsFragment : Fragment() {

    private lateinit var binding: ArticleDetailsFragmentBinding
    private lateinit var articleDetailsViewModel: ArticleDetailsViewModel
    private var articleId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = ArticleDetailsFragmentBinding.inflate(inflater)
        binding.scrollView.setBackgroundColor(Color.WHITE)



        initViewModels()
        initObservers()
        extractInput()

        articleDetailsViewModel.getArticleDetails(articleId)

        return binding.root
    }

    private fun extractInput() {
        articleId = requireArguments().getInt(Article.KEY_ARTICLE_ID)
    }

    private fun initViewModels() {
        articleDetailsViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ArticleDetailsRepo()
            )
        )[ArticleDetailsViewModel::class.java]
    }

    private fun initObservers() {
        articleDetailsViewModel.articleDetailsLiveData.observe(viewLifecycleOwner) {
            binding.txtTitle.text = it.title
            binding.txtAuthor.text = it.author
            binding.txtContent.loadData(it.content, "text/html", "UTF-8")
            binding.txtCategory.text = it.category
            binding.txtSubCategory.text = it.subcategory
            binding.txtTags.text = it.tags
            binding.txtStatus.text = it.status.toString()
            binding.txtCreatedOn.text = it.createdOn
            Glide.with(binding.imgArticleDetails)
                .load(it.imgUrl)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgArticleDetails)
        }
    }
}