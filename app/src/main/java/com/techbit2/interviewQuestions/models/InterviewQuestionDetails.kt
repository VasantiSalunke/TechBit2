package com.techbit2.interviewQuestions.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.io.Serializable
import java.lang.reflect.Type

@JsonAdapter(InterviewQuestionsDetailsDeserializer::class)

data class InterviewQuestionDetails(
    val id : Int,
    val title : String,
    val author : String,
    val content : String,
    val category : String,
    val subCategory : String,
    val tag : String,
    val status : Int,
    val createdOn : String,
    val imgUrl : String
) : Serializable

class InterviewQuestionsDetailsDeserializer: JsonDeserializer<InterviewQuestionDetails> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): InterviewQuestionDetails {

        val jsonObject = json?.asJsonObject!!

        return InterviewQuestionDetails(
            jsonObject.get("id").asInt,
            jsonObject.get("title").asString,
            jsonObject.get("author").asString,
            jsonObject.get("content").asString,
            jsonObject.get("category").asString,
            jsonObject.get("subcategory").asString,
            jsonObject.get("tags").asString,
            jsonObject.get("status").asInt,
            jsonObject.get("created_on").asString,
            jsonObject.get("images").asJsonArray[0].asJsonObject.get("path").asString
        )
    }
}