package com.techbit2.interviewQuestions.network

import com.techbit2.ApiResponse
import com.techbit2.interviewQuestions.models.InterviewQuestionDetails
import com.techbit2.interviewQuestions.models.InterviewQuestions
import com.techbit2.interviewQuestions.models.InterviewQuestionsCategory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface InterviewQuestionsService {
    @GET("interviewquestions/{pageSize}/{pageNo}/{maincatId}/{subcatId}")
    suspend fun getInterviewQuestions(
        @Path("pageSize") pageSize: Int,
        @Path("pageNo") pageNo: Int,
        @Path("maincatId") maincatId: Int,
       @Path("subcatId") subcatId: Int
    ) : ApiResponse.Success<ArrayList<InterviewQuestions>>


    @GET("interviewquestions/{pageSize}/{pageNo}/{maincatId}/{subcatId}")
    suspend fun getInterviewQuestionsCategory(
        @Path("pageSize") pageSize : Int,
        @Path("pageNo") pageNo : Int,
        @Path("maincatId") maincatId : Int,
        @Path("subcatId") subcatId : Int

    ) : ApiResponse.Success<ArrayList<InterviewQuestionsCategory>>

//    @GET("articles/{pageSize}/{pageNo}")
//    suspend fun getTrendingArticles(
//        @Path("pageSize") pageSize : Int,
//        @Path("pageNo") pageNo : Int
//    ) : ApiResponse.Success<ArrayList<Article>>

    @GET("interviewquestions/{interviewQuestionsId}")
    suspend fun getInterviewQuestionDetails(
        @Path("interviewQuestionsId") interviewQuestionsId : Int
    ) : ApiResponse.Success<InterviewQuestionDetails>


    companion object {

        var interviewQuestionsService: InterviewQuestionsService? = null

        fun getInstance(): InterviewQuestionsService {

            if (interviewQuestionsService == null) {
                interviewQuestionsService =
                    Retrofit.Builder()
                        .baseUrl("http://3.7.73.200:4002/apis/")
                        .addConverterFactory(
                            GsonConverterFactory.create()
                        ).build().create(InterviewQuestionsService::class.java)
            }
            return interviewQuestionsService!!
        }
    }

}



