package com.techbit2.interviewQuestions.models

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.reflect.Type

@JsonAdapter(InterviewQuestionDeserializer::class)

data class InterviewQuestions (
    @SerializedName("id")
    val id: Int,
    val title: String,
    val author: String,
    val category: String,
    val subcategory: String,
    val tags: String,
    val status: Int,
    val createdOn: String,
    val imgUrl: String

) : Serializable {

    companion object {

        val KEY_INTERVIEWQUESTION_ID = "interviewquestion_Id"

    }
}

class InterviewQuestionDeserializer : JsonDeserializer<InterviewQuestions> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): InterviewQuestions {

        val jsonObject = json?.asJsonObject

        Log.e("tag",jsonObject.toString())
        val id = jsonObject?.get("id")!!.asInt
        val title = jsonObject?.get("title")!!.asString
        val author = jsonObject?.get("author")!!.asString
        val category = jsonObject?.get("category")!!.asString
        val subcategory = jsonObject?.get("subcategory")!!.asString
        val tags = jsonObject?.get("tags")!!.asString
        val status = jsonObject?.get("status")!!.asInt
        val createdOn = jsonObject?.get("created_on")!!.asString
        val imgUrl = jsonObject?.getAsJsonArray("images")!![0].asJsonObject.get("path").asString


        return InterviewQuestions(
            id,
            title,
            author,
            category,
            subcategory,
            tags,
            status,
            createdOn,
            imgUrl
        )
    }


}

