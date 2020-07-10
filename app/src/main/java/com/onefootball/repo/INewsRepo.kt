package com.onefootball.repo

import androidx.lifecycle.LiveData
import com.onefootball.model.News
import com.onefootball.utils.Resource

interface INewsRepo {
    /**Get the news from the json. Instead of manually parsing it, which is a lengthy process and
     * prone to errors, we just use GSON for easy parsing with minimum lines of code to keep things
     * clean.
     * We will use a background thread (using coroutines) to do the work just to be on the safe side
     * */
    fun getNewsData(): LiveData<Resource<List<News>>>
}