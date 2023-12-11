package com.techbit2.articles.models

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.reflect.Type

@JsonAdapter(ArticleDeserializer:: class)

data class Article (
    @SerializedName("id")
    val articleId: Int,

    val title: String,

    val author: String,

    val category: String,

    val subcategory: String,

    val tags : String,

    val status : String,

    val createdOn : String,

    val imgUrl : String

): Serializable {

    companion object{
        val KEY_ARTICLE_ID = "article_id"
    }
}

class ArticleDeserializer : JsonDeserializer<Article> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Article {
        val jsonObject = json?.asJsonObject

        Log.e("tag", "****")
        Log.e("tag", jsonObject.toString())

        val id = jsonObject?.get("id")!!.asInt
        val title = jsonObject?.get("title")!!.asString
        val author = jsonObject?.get("author")!!.asString
        val category = jsonObject?.get("category")!!.asString
        val subcategory = jsonObject?.get("subcategory")!!.asString
        val tags = jsonObject?.get("tags")!!.asString
        val status = jsonObject?.get("status")!!.asString
        val createdOn = jsonObject?.get("created_on")!!.asString
        val imgUrl = jsonObject?.getAsJsonArray("images")!![0].asJsonObject.get("path").asString

        return Article(
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