package com.techbit2.articles.models

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.reflect.Type

@JsonAdapter(ArticleCategoryDeserializer ::class)
data class ArticleCategory(

    @SerializedName("id")
    val articleId: Int,

    val category : String,

    val imgurl :String

) : Serializable {

    companion object{
        val KEY_ARTICLE_ID = "article_id"
    }
}

class ArticleCategoryDeserializer : JsonDeserializer<ArticleCategory> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ArticleCategory {
        val jsonObject = json?.asJsonObject


        Log.e("tag", "****")
        Log.e("tag", jsonObject.toString())


        val id = jsonObject?.get("id")!!.asInt
        val category = jsonObject?.get("category")!!.asString
        val imgUrl = jsonObject?.getAsJsonArray("images")!![0].asJsonObject.get("path").asString


        return ArticleCategory(
            id,
            category,
            imgUrl
        )
    }

}
