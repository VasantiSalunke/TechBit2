package com.techbit2.interviewQuestions.repositories

import com.techbit2.ApiResponse
import com.techbit2.interviewQuestions.models.InterviewQuestionDetails
import com.techbit2.interviewQuestions.network.InterviewQuestionsService.Companion.interviewQuestionsService

class InterviewQuestionDetailsRepository () :Repository{
    suspend fun getInterviewQuestionDetails(
        interviewQuestionId : Int

    ): ApiResponse.Success<InterviewQuestionDetails>? {

        return interviewQuestionsService?.getInterviewQuestionDetails(interviewQuestionId)

    }
}