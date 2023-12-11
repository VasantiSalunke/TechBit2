package com.techbit2.articles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techbit2.R
import com.techbit2.articles.models.Article
import com.techbit2.databinding.ArticleViewBinding
import com.techbit2.databinding.SeeMoreBinding

class ArticlesAdapter (
    private val articlesList: ArrayList<Article>
) : RecyclerView.Adapter<ArticlesAdapter.AricleViewHolder>() {


    interface OnArticleClicklistener{

        fun onArticleClick(articles: Article, position: Int)
    }

    var onArticleClicklistener : OnArticleClicklistener? = null



    inner class AricleViewHolder(
        view : View,
    ) : RecyclerView.ViewHolder(
        view
    ){


        var binding : ArticleViewBinding

        init {
            binding = ArticleViewBinding.bind(view)

            binding.root.setOnClickListener {
                Toast.makeText(it.context,"clicked $adapterPosition",Toast.LENGTH_SHORT).show()
                onArticleClicklistener?.onArticleClick(articlesList[adapterPosition],adapterPosition)
            }


        }
    }


       override fun getItemCount() = articlesList.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AricleViewHolder {
        return AricleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.article_view,null)
        )
    }



    override fun onBindViewHolder(holder: AricleViewHolder, position: Int) {

        var item = articlesList[position]

        holder.binding.txtArticleTitle.text = articlesList[position].title
        holder.binding.txtArticleAuthor.text = articlesList[position].author
        holder.binding.txtCreatedOn.text = articlesList[position].createdOn

        Glide.with(holder.itemView)
            .load(item.imgUrl)
            .error(R.drawable.img_1)
            .into(holder.binding.imgArticle)

    }


    }