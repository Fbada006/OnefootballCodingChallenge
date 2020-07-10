package com.onefootball.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.onefootball.model.News
import com.onefootball.model.NewsResult
import com.onefootball.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.nio.charset.Charset
import javax.inject.Inject

/**This class gets the JSON and parses it using JSON then presents it as
 * a LiveData to the ViewModel
 * */
class NewsRepo @Inject constructor(private val context: Context, private val gson: Gson) :
    INewsRepo {

    // This is the LiveData that will hold the news resource and its loading status. Its mutable here
    // but the one presented to the UI must be immutable
    private val newsLiveData = MutableLiveData<Resource<List<News>>>()

    /**Get the news from the json. Instead of manually parsing it, which is a lengthy process and
     * prone to errors, we just use GSON for easy parsing with minimum lines of code to keep things
     * clean.
     * We will use a background thread (using coroutines) to do the work just to be on the safe side
     * TODO: Access the JSON on a different thread
     * */
    override fun getNewsData(): LiveData<Resource<List<News>>> {
        newsLiveData.postValue(Resource.loading(null))
        Timber.d("Value is ${newsLiveData.value?.status}")
        val inputStream = context.assets.open("news.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        CoroutineScope(IO).launch {
            try {
                val jsonString = buffer.toString(Charset.defaultCharset())
                val type = object : TypeToken<NewsResult>() {}.type

                val newsResult = withContext(Dispatchers.Default) {
                    val result: NewsResult = gson.fromJson(jsonString, type)
                    result
                }
                newsLiveData.postValue(Resource.success(newsResult.news))
            } catch (e: Exception) {
                Timber.e("Problem getting data from local JSON: $e")
                newsLiveData.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
        return newsLiveData
    }
}