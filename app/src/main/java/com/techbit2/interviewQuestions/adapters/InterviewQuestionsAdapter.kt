package com.techbit2.interviewQuestions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techbit2.R
import com.techbit2.databinding.InterviewQuestionsViewBinding
import com.techbit2.interviewQuestions.models.InterviewQuestions

class InterviewQuestionsAdapter(

    private val interviewQuestionList: ArrayList<InterviewQuestions>

) : RecyclerView.Adapter<InterviewQuestionsAdapter.InterviewQuestionViewHolder>() {

    interface OnQuestionClickListener {
        fun onQuestionClick(interviewQuestion: InterviewQuestions, position: Int)
    }

    var onQuestionClickListener: OnQuestionClickListener? = null

    inner class InterviewQuestionViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(
        view
    ) {
        var binding: InterviewQuestionsViewBinding

        init {
            binding = InterviewQuestionsViewBinding.bind(view)

            binding.root.setOnClickListener {
                Toast.makeText(it.context, "clicked $adapterPosition", Toast.LENGTH_SHORT).show()
                onQuestionClickListener?.onQuestionClick(interviewQuestionList[adapterPosition], adapterPosition)
            }
        }
    }

    override fun getItemCount() = interviewQuestionList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterviewQuestionViewHolder {

        return InterviewQuestionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.interview_questions_view, null)
        )
    }

    override fun onBindViewHolder(holder:InterviewQuestionViewHolder, position: Int) {

        val item = interviewQuestionList[position]


        holder.binding.txtQuestionTitle.text = item.title
        holder.binding.txtQuestionAuthor.text = item.author
        holder.binding.txtQuestionCreatedOn.text = item.createdOn

        Glide.with(holder.itemView)
            .load(item.imgUrl)
            .error(R.mipmap.ic_launcher)
            .into(holder.binding.imgQuestion)


    }
}