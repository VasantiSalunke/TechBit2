package com.techbit2.interviewQuestions.models

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.reflect.Type

@JsonAdapter(InterviewQuestionCategoryDeserializer ::class)
data class InterviewQuestionsCategory(

    @SerializedName("id")
    val interviewQuestionsId: Int,

    val category : String,

    val imgurl :String

) : Serializable {

    companion object{
        val KEY_INTERVIEW_QUESTIONS_ID = "interview_questions_id"
    }
}

class InterviewQuestionCategoryDeserializer : JsonDeserializer<InterviewQuestionsCategory> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): InterviewQuestionsCategory {
        val jsonObject = json?.asJsonObject


        Log.e("tag", "****")
        Log.e("tag", jsonObject.toString())


        val id = jsonObject?.get("id")!!.asInt
        val category = jsonObject?.get("category")!!.asString
        val imgUrl = jsonObject?.getAsJsonArray("images")!![0].asJsonObject.get("path").asString


        return InterviewQuestionsCategory(
            id,
            category,
            imgUrl
        )
    }

}
