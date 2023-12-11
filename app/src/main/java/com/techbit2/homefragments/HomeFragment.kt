package com.techbit2.homefragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techbit2.R
import com.techbit2.articles.adapters.ArticlesAdapter
import com.techbit2.articles.adapters.ArticlesCategoryAdapter
import com.techbit2.articles.factory.ViewModelFactory
import com.techbit2.articles.fragments.ArticleDetailsFragment
import com.techbit2.articles.fragments.ArticlesFragment
import com.techbit2.articles.models.Article
import com.techbit2.articles.models.ArticleCategory
import com.techbit2.articles.network.ArticlesService
import com.techbit2.articles.repositories.ArticlesRepo
import com.techbit2.articles.viewmodels.HomeFragmentViewModel
import com.techbit2.databinding.HomeFragmentBinding
import com.techbit2.databinding.SeeMoreBinding
import com.techbit2.interviewQuestions.adapters.InterviewQuestionsCategoryAdapter
import com.techbit2.interviewQuestions.factory.InterviewQuestionsViewModelFactory
import com.techbit2.interviewQuestions.fragments.InterviewQuestionsFragment
import com.techbit2.interviewQuestions.models.InterviewQuestionsCategory
import com.techbit2.interviewQuestions.network.InterviewQuestionsService
import com.techbit2.interviewQuestions.repositories.InterviewQuestionsRepo
import com.techbit2.interviewQuestions.viewmodels.HomeFragmentViewModel2

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    private lateinit var homeFragmentViewModel2: HomeFragmentViewModel2
    private lateinit var articlesCategoryAdapter: ArticlesCategoryAdapter
    private lateinit var trendingArticlesAdapter: ArticlesAdapter
    private lateinit var recyclerArticleCategory: RecyclerView
    private lateinit var recyclerInterviewQuestionsCategory: RecyclerView
    private lateinit var interviewQuestionsCategoryAdapter: InterviewQuestionsCategoryAdapter
    private lateinit var searchFragment: SearchFragment


    private lateinit var articlesRepo: ArticlesRepo
    private lateinit var interviewQuestionsRepo: InterviewQuestionsRepo

    private var isScrollingUp = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = HomeFragmentBinding.inflate(inflater)
        binding.recyclerTrendingArticle.setBackgroundColor(Color.WHITE)
        binding.recyclerArticleCategory.setBackgroundColor(Color.WHITE)
        binding.homeFragment.setBackgroundColor(Color.WHITE)
//        binding.txtSeeMore.setBackgroundColor(Color.WHITE)

        recyclerArticleCategory = binding.recyclerArticleCategory


        initViewModels()
        initViews()
        initObservers()
        initListener()

        homeFragmentViewModel.getArticleCategory()
        homeFragmentViewModel.getTrendingArticles()
        homeFragmentViewModel2.getInterviewQuestionsCategory()


        return binding.root
    }

    private fun initViews() {

        binding.recyclerTrendingArticle.layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        trendingArticlesAdapter =
            ArticlesAdapter(homeFragmentViewModel.trendingArticlesList)
        binding.recyclerTrendingArticle.adapter = trendingArticlesAdapter




        articlesCategoryAdapter =
            ArticlesCategoryAdapter(homeFragmentViewModel.articleCategoryList)
        binding.recyclerArticleCategory.adapter = articlesCategoryAdapter

        recyclerArticleCategory = binding.recyclerArticleCategory

        binding.recyclerArticleCategory.layoutManager = GridLayoutManager(requireContext(),3)

        articlesCategoryAdapter.notifyDataSetChanged()




        interviewQuestionsCategoryAdapter =
            InterviewQuestionsCategoryAdapter(homeFragmentViewModel2.interviewQuestionsCategoryList)
        binding.recyclerInterviewQuestionsCategory.adapter = interviewQuestionsCategoryAdapter

        recyclerInterviewQuestionsCategory = binding.recyclerInterviewQuestionsCategory

        binding.recyclerInterviewQuestionsCategory.layoutManager = GridLayoutManager(requireContext(),3)

        interviewQuestionsCategoryAdapter.notifyDataSetChanged()




        val textView = binding.txtSeeMore

    }

    private fun initViewModels() {
        homeFragmentViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ArticlesRepo(ArticlesService.getInstance())
            )
        )[HomeFragmentViewModel::class.java]



        homeFragmentViewModel2 = ViewModelProvider(
            this,
            InterviewQuestionsViewModelFactory(
                InterviewQuestionsRepo(InterviewQuestionsService.getInstance())
            )
        )[HomeFragmentViewModel2::class.java]

    }



    private fun initObservers() {
        homeFragmentViewModel.articleCategoryLoadedLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                articlesCategoryAdapter.notifyDataSetChanged()
            }
        }

        homeFragmentViewModel.trendingArticleLoadedLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                trendingArticlesAdapter.notifyDataSetChanged()
            }
        }

        homeFragmentViewModel2.interviewQuestionsCategoryLoadedLiveData.observe(
            viewLifecycleOwner
        ){
            if (it){
                interviewQuestionsCategoryAdapter.notifyDataSetChanged()
            }
        }

    }

    private fun initListener() {

        interviewQuestionsCategoryAdapter.onInterviewQuestionsClicklistener =
            object : InterviewQuestionsCategoryAdapter.OnInterviewQuestionsClicklistener{
                override fun onInterviewQuestionsClick(interviewQuestionsCategory: InterviewQuestionsCategory, position: Int) {
                    addInterviewQuestionsFragment(interviewQuestionsCategory  )
                }
            }

        articlesCategoryAdapter.onArticleClicklistener =
            object : ArticlesCategoryAdapter.OnArticleClicklistener {
                override fun onArticleClick(articleCategory: ArticleCategory, position: Int) {
                    addArticlesFragment(articleCategory)
                }
            }


        trendingArticlesAdapter.onArticleClicklistener =
            object : ArticlesAdapter.OnArticleClicklistener {
                override fun onArticleClick(article: Article, position: Int) {
                    addArticleDetailsFragment(article)
                }
            }

//        binding.txtSeeMore.setOnClickListener {
//            // Handle "See More" click here
//            val articlesFragment = ArticlesFragment()
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.mainContainer, articlesFragment)
//                .addToBackStack(null)
//                .commit()
//        }


        binding.txtSeeMore.setOnClickListener {
            val articlesFragment = ArticlesFragment()
            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, articlesFragment)
                .addToBackStack(null)
                .commit()
        }


        binding.recyclerTrendingArticle.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                isScrollingUp = dy > 0

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount


                Log.d(
                    "Home Fragment",
                    "Last Visible Item Position : $lastVisibleItemPosition, Total Item Count : $totalItemCount"
                )


                if (isScrollingUp && lastVisibleItemPosition == totalItemCount - 1) {
                    Log.d("tag", "You are at the end")
                    Toast.makeText(context, "You have reached at the end", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })
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

    private fun addArticlesFragment(articleCategory: ArticleCategory){
        val articlesFragment = ArticlesFragment()

        val input = Bundle()

        input.putSerializable(ArticleCategory.KEY_ARTICLE_ID, articleCategory.articleId)
        articlesFragment.arguments = input

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, articlesFragment,null)
            .addToBackStack(null)
            .commit()
    }

    private fun addInterviewQuestionsFragment(interviewQuestionsCategory: InterviewQuestionsCategory){
        val interviewQuestionsFragment = InterviewQuestionsFragment()

        val input = Bundle()

        input.putSerializable(InterviewQuestionsCategory.KEY_INTERVIEW_QUESTIONS_ID, interviewQuestionsCategory.interviewQuestionsId)
        interviewQuestionsFragment.arguments = input

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, interviewQuestionsFragment,null)
            .addToBackStack(null)
            .commit()
    }
}
