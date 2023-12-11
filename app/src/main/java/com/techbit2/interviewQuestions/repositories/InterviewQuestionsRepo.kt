package com.techbit2.interviewQuestions.repositories

import com.techbit2.ApiResponse
import com.techbit2.interviewQuestions.models.InterviewQuestions
import com.techbit2.interviewQuestions.models.InterviewQuestionsCategory
import com.techbit2.interviewQuestions.network.InterviewQuestionsService

class InterviewQuestionsRepo (
    private val interviewQuestionsService: InterviewQuestionsService
) : Repository {

    suspend fun getInterviewQuestionsCategory(
        pageSize: Int,
        pageNo: Int,
        maincatId : Int,
        subcatId : Int
    ): ApiResponse.Success<ArrayList<InterviewQuestionsCategory>> {
        return interviewQuestionsService.getInterviewQuestionsCategory(pageSize, pageNo,maincatId,subcatId )
    }

//    suspend fun getTrendingArticles(
//        pageSize: Int,
//        pageNo: Int
//    ): ApiResponse.Success<ArrayList<Article>>? {
//        return articlesService.getTrendingArticles(pageSize, pageNo)
//
//    }

    suspend fun getInterviewQuestions(
        pageSize: Int,
        pageNo: Int,
        maincatId: Int,
        subcatId: Int
    ): ApiResponse.Success<ArrayList<InterviewQuestions>>? {
        return interviewQuestionsService.getInterviewQuestions(pageSize, pageNo,maincatId,subcatId)

    }
}