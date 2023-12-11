package com.techbit2.interviewQuestions.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techbit2.interviewQuestions.repositories.InterviewQuestionDetailsRepository
import com.techbit2.interviewQuestions.repositories.InterviewQuestionsRepo
import com.techbit2.interviewQuestions.repositories.Repository
import com.techbit2.interviewQuestions.viewmodels.HomeFragmentViewModel2
import com.techbit2.interviewQuestions.viewmodels.InterviewQuestionDetailsViewModel
import com.techbit2.interviewQuestions.viewmodels.InterviewQuestionsViewModel

class InterviewQuestionsViewModelFactory(
    val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeFragmentViewModel2::class.java) && repository is InterviewQuestionsRepo){
            return HomeFragmentViewModel2(repository) as  T
        }


        if (modelClass.isAssignableFrom(InterviewQuestionsViewModel::class.java) && repository is InterviewQuestionsRepo) {
            return InterviewQuestionsViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(InterviewQuestionDetailsViewModel::class.java) && repository is InterviewQuestionDetailsRepository) {
            return InterviewQuestionDetailsViewModel(repository) as T
        }

        throw java.lang.Error(" Unable to create view model $modelClass")

    }

}
