package com.techbit2.interviewQuestions.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techbit2.R
import com.techbit2.databinding.InterviewQuestionsFragmentBinding
import com.techbit2.interviewQuestions.adapters.InterviewQuestionsAdapter
import com.techbit2.interviewQuestions.factory.InterviewQuestionsViewModelFactory
import com.techbit2.interviewQuestions.models.InterviewQuestions
import com.techbit2.interviewQuestions.network.InterviewQuestionsService
import com.techbit2.interviewQuestions.repositories.InterviewQuestionsRepo
import com.techbit2.interviewQuestions.viewmodels.InterviewQuestionsViewModel

class InterviewQuestionsFragment : Fragment() {

    private lateinit var binding: InterviewQuestionsFragmentBinding
    private lateinit var interviewQuestionsViewModel: InterviewQuestionsViewModel
    private lateinit var interviewQuestionAdapter: InterviewQuestionsAdapter
    private lateinit var interviewQuestionDetailsFragment: InterviewQuestionDetailsFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = InterviewQuestionsFragmentBinding.inflate(inflater)
        binding.recyclerInterviewQuestions.setBackgroundColor(Color.WHITE)


        initViewModels()
        initViews()
        initObservers()
        initListener()


        interviewQuestionsViewModel.getInterviewQuestion()

        return binding.root
    }

    private fun initViews() {
        binding.recyclerInterviewQuestions.layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

        interviewQuestionAdapter =
            InterviewQuestionsAdapter(interviewQuestionsViewModel.interviewQuestionList)
        binding.recyclerInterviewQuestions.adapter = interviewQuestionAdapter

    }

    private fun initViewModels() {

        interviewQuestionsViewModel = ViewModelProvider(
            this,
            InterviewQuestionsViewModelFactory(
                InterviewQuestionsRepo(InterviewQuestionsService.getInstance())
            )
        )[InterviewQuestionsViewModel::class.java]
    }

    private fun initObservers() {
        interviewQuestionsViewModel.interviewQuestionLoadedLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                interviewQuestionAdapter.notifyDataSetChanged()
            }
        }

    }

    private fun initListener() {
        interviewQuestionAdapter.onQuestionClickListener =
            object : InterviewQuestionsAdapter.OnQuestionClickListener {
                override fun onQuestionClick(interviewQuestion: InterviewQuestions, position: Int) {

                    addInterviewQuestionDetailsFragment(interviewQuestion)

                }
            }
    }

    private fun addInterviewQuestionDetailsFragment(interviewQuestion: InterviewQuestions) {

        val interviewQuestionDetailsFragment = InterviewQuestionDetailsFragment()

        val input = Bundle()
        input.putSerializable(InterviewQuestions.KEY_INTERVIEWQUESTION_ID,interviewQuestion .id)

        interviewQuestionDetailsFragment.arguments = input

        parentFragmentManager.beginTransaction()
            .add(R.id.interviewMainContainer, interviewQuestionDetailsFragment, null)
            .addToBackStack(null)
            .commit()


    }
}

