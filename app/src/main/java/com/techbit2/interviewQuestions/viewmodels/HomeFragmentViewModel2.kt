package com.techbit2.interviewQuestions.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techbit2.interviewQuestions.models.InterviewQuestionsCategory
import com.techbit2.interviewQuestions.repositories.InterviewQuestionsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel2(
    private val interviewQuestionsRepo: InterviewQuestionsRepo
): ViewModel() {
    val interviewQuestionsCategoryList = ArrayList<InterviewQuestionsCategory>()
    val interviewQuestionsCategoryLoadedLiveData = MutableLiveData<Boolean>()

    var pageNo: Int = 0
    val pageSize: Int = 10
    val maincatId: Int = 2
    val subcatId : Int = 1



    fun getInterviewQuestionsCategory(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = interviewQuestionsRepo.getInterviewQuestionsCategory(pageSize,++pageNo,maincatId,subcatId)

            withContext(Dispatchers.Main){
                interviewQuestionsCategoryList.addAll(response!!.data)
                interviewQuestionsCategoryLoadedLiveData.postValue(true)
            }
        }
    }


}