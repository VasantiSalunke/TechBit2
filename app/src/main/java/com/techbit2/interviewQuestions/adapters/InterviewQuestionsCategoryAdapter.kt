package com.techbit2.interviewQuestions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techbit2.R
import com.techbit2.databinding.InterviewQuestionCategoryViewBinding
import com.techbit2.interviewQuestions.models.InterviewQuestionsCategory

class InterviewQuestionsCategoryAdapter (private val interviewQuestionsCategoryList: ArrayList<InterviewQuestionsCategory>
) : RecyclerView.Adapter<InterviewQuestionsCategoryAdapter.InterviewQuestionsCategoryViewHolder>() {

    interface OnInterviewQuestionsClicklistener {

        fun onInterviewQuestionsClick(interviewQuestions: InterviewQuestionsCategory, position: Int)
    }

    var onInterviewQuestionsClicklistener: OnInterviewQuestionsClicklistener? = null

    inner class InterviewQuestionsCategoryViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(
        view
    ) {

        var binding: InterviewQuestionCategoryViewBinding

        init {
            binding = InterviewQuestionCategoryViewBinding.bind(view)

            binding.root.setOnClickListener {
                Toast.makeText(it.context, "clicked $adapterPosition", Toast.LENGTH_SHORT).show()
                onInterviewQuestionsClicklistener?.onInterviewQuestionsClick(
                    interviewQuestionsCategoryList[adapterPosition],
                    adapterPosition
                )
            }


        }
    }

    override fun getItemCount() = interviewQuestionsCategoryList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InterviewQuestionsCategoryViewHolder {
        return InterviewQuestionsCategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.interview_question_category_view, null)
        )

    }

    override fun onBindViewHolder(holder: InterviewQuestionsCategoryViewHolder, position: Int) {

        var item = interviewQuestionsCategoryList[position]

        //   holder.binding.txtArticleCategoriesTitle.text = articlesCategoryList[position].title
        holder.binding.txtInterviewQuestionsCategoryView.text =
            interviewQuestionsCategoryList[position].category


        Glide.with(holder.itemView)
            .load(item.imgurl)
            .error(R.drawable.img_2)
            .into(holder.binding.imgInterviewQuestionsCategoryView)


    }

}