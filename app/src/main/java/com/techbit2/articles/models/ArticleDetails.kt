package com.techbit2.articles.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.io.Serializable
import java.lang.reflect.Type

@JsonAdapter(ArticleDetailsDeserializer:: class)

data class ArticleDetails(
    val id : Int,
    val title : String,
    val author : String,
    val content : String,
    val category : String,
    val subcategory : String,
    val  tags : String,
    val status : Int,
    val createdOn : String,
    val imgUrl : String

): Serializable

class ArticleDetailsDeserializer : JsonDeserializer<ArticleDetails> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ArticleDetails {

        val jsonObject = json?.asJsonObject!!

        return ArticleDetails(
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