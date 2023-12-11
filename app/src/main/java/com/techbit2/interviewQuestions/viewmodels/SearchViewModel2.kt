package com.techbit2.interviewQuestions.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techbit2.interviewQuestions.models.InterviewQuestions
import com.techbit2.interviewQuestions.repositories.InterviewQuestionsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel2 (
    private val interviewQuestionsRepo: InterviewQuestionsRepo
) : ViewModel() {
    val interviewQuestionsList = arrayListOf<InterviewQuestions>()
    val interviewQuestionsLoadedLiveData = MutableLiveData<Boolean>()


    fun searchInterviewQuestions() {

        CoroutineScope(Dispatchers.IO).launch {

            val response = interviewQuestionsRepo.getInterviewQuestions(pageSize = 20, pageNo = 1, maincatId = 2, subcatId = 1)
            withContext(Dispatchers.Main) {
                interviewQuestionsList.addAll(response!!.data)
                interviewQuestionsLoadedLiveData.postValue(true)

            }
        }
    }
}