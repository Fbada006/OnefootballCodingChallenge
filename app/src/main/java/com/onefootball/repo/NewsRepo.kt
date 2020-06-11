package com.onefootball.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.onefootball.model.News
import com.onefootball.model.NewsResult
import java.nio.charset.Charset
import javax.inject.Inject

/**This class gets the JSON and parses it using JSON then presents it as
 * a LiveData to the ViewModel
 * */
class NewsRepo @Inject constructor(private val context: Context) {

    //This is the LiveData that will hold the news resource. Its mutable here
    //but the one presented to the UI must be immutable
    private val newsLiveData = MutableLiveData<List<News>>()

    /**Get the news from the json. Instead of manually parsing it, which is a lengthy process and
     * prone to errors, we just use GSON for easy parsing with minimum lines of code to keep things
     * clean
     * */
    fun getNewsData(): LiveData<List<News>> {
        val gson = Gson()
        val inputStream = context.assets.open("news.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val jsonString = buffer.toString(Charset.defaultCharset())
        val type = object : TypeToken<NewsResult>() {}.type

        try {
            val newsResult: NewsResult = gson.fromJson(jsonString, type)
            newsLiveData.value = newsResult.news
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newsLiveData
    }
}