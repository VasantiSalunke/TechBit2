package com.techbit2.articles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techbit2.R
import com.techbit2.articles.models.Article
import com.techbit2.articles.models.ArticleCategory
import com.techbit2.databinding.ArticlesCategoryViewBinding

class ArticlesCategoryAdapter(
    private val articlesCategoryList: ArrayList<ArticleCategory>
) : RecyclerView.Adapter<ArticlesCategoryAdapter.ArticlesCategoryViewHolder>() {

    interface OnArticleClicklistener{

        fun onArticleClick(articles: ArticleCategory, position: Int)
    }

    var onArticleClicklistener : OnArticleClicklistener? = null

    inner class ArticlesCategoryViewHolder(
        view : View,
    ) : RecyclerView.ViewHolder(
        view
    ){

        var binding : ArticlesCategoryViewBinding

        init {
            binding = ArticlesCategoryViewBinding.bind(view)

            binding.root.setOnClickListener {
                Toast.makeText(it.context,"clicked $adapterPosition", Toast.LENGTH_SHORT).show()
                onArticleClicklistener?.onArticleClick(articlesCategoryList[adapterPosition],adapterPosition)
            }


        }
    }

    override fun getItemCount() = articlesCategoryList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesCategoryViewHolder {
        return ArticlesCategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.articles_category_view,null)
        )

    }

    override fun onBindViewHolder(holder: ArticlesCategoryViewHolder, position: Int) {

        var item = articlesCategoryList[position]

        //   holder.binding.txtArticleCategoriesTitle.text = articlesCategoryList[position].title
        holder.binding.txtArticleCategory.text = articlesCategoryList[position].category


        Glide.with(holder.itemView)
            .load(item.imgurl)
            .error(R.drawable.img_5)
            .into(holder.binding.imgArticlesCategoryView)


    }



}
