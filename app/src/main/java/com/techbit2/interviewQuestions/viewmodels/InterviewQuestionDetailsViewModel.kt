package com.techbit2.interviewQuestions.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techbit2.interviewQuestions.models.InterviewQuestionDetails
import com.techbit2.interviewQuestions.repositories.InterviewQuestionDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InterviewQuestionDetailsViewModel (

    private val interviewQuestionDetailsRepo : InterviewQuestionDetailsRepository

) : ViewModel() {


    val interviewQuestionDetailsLiveData = MutableLiveData<InterviewQuestionDetails>()
    fun getInterviewQuestionDetails(interviewQuestionId : Int) {

        CoroutineScope(Dispatchers.IO).launch {
            val response =
                interviewQuestionDetailsRepo.getInterviewQuestionDetails(interviewQuestionId)
            withContext(Dispatchers.Main) {
                interviewQuestionDetailsLiveData.postValue(response!!.data!!)
            }

        }
    }
}

