package com.techbit2.interviewQuestions.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techbit2.interviewQuestions.models.InterviewQuestions
import com.techbit2.interviewQuestions.repositories.InterviewQuestionsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InterviewQuestionsViewModel (

    private val interviewQuestionRepo: InterviewQuestionsRepo

) : ViewModel() {

    val interviewQuestionList = ArrayList<InterviewQuestions>()
    val interviewQuestionLoadedLiveData = MutableLiveData<Boolean>()
    var pageNo: Int = 0
    val pageSize: Int = 10
    val maincatId : Int = 2
    val subcatId : Int = 1

    fun getInterviewQuestion(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = interviewQuestionRepo.getInterviewQuestions(pageSize, pageNo, maincatId,subcatId)
            withContext(Dispatchers.Main){
                interviewQuestionList.addAll(response!!.data)
                interviewQuestionLoadedLiveData.postValue(true)

                Log.e("tag", response.data.size.toString())

            }
        }
    }


}
