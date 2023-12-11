package com.techbit2.interviewQuestions.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.techbit2.databinding.InterviewQuestionDetailsFragmentBinding
import com.techbit2.interviewQuestions.factory.InterviewQuestionsViewModelFactory
import com.techbit2.interviewQuestions.models.InterviewQuestions
import com.techbit2.interviewQuestions.repositories.InterviewQuestionDetailsRepository
import com.techbit2.interviewQuestions.viewmodels.InterviewQuestionDetailsViewModel

class InterviewQuestionDetailsFragment : Fragment() {

    private lateinit var binding: InterviewQuestionDetailsFragmentBinding
    private lateinit var interviewQuestionDetailsViewModel: InterviewQuestionDetailsViewModel
    private var interviewQuestionId : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        binding = InterviewQuestionDetailsFragmentBinding.inflate(inflater)
        binding.scrollViewInterviewQuestionDetails.setBackgroundColor(Color.WHITE)

        initViewModels()
        initObservers()
        extractInput()

        interviewQuestionDetailsViewModel.getInterviewQuestionDetails(interviewQuestionId)

        return binding.root
    }

    private fun extractInput() {
        interviewQuestionId = requireArguments().getInt(InterviewQuestions.KEY_INTERVIEWQUESTION_ID)
    }

    private fun initViewModels() {
        interviewQuestionDetailsViewModel = ViewModelProvider(
            this,
            InterviewQuestionsViewModelFactory(
                InterviewQuestionDetailsRepository()
            )
        )[InterviewQuestionDetailsViewModel::class.java]
    }

    private fun initObservers() {

        interviewQuestionDetailsViewModel.interviewQuestionDetailsLiveData.observe(viewLifecycleOwner) {

            binding.txtTitle.text = it.title
            binding.txtAuthor.text = it.author
            binding.txtContent.loadData(it.content, "text/html", "UTF-8")
            binding.txtCategory.text = it.category
            binding.txtSubcategory.text = it.subCategory
            binding.txtTag.text = it.tag
            binding.txtStatus.text = it.status.toString()
            binding.txtCreatedOn.text = it.createdOn



        }
    }

}