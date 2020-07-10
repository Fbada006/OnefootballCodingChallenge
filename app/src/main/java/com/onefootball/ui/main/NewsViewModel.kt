package com.onefootball.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onefootball.model.News
import com.onefootball.repo.NewsRepo
import com.onefootball.utils.Event
import javax.inject.Inject

/**This class will prepare and manage the data to present to the UI*/
class NewsViewModel @Inject constructor(newsRepo: NewsRepo) : ViewModel() {

    /*The internal MutableLiveData that stores the event of a click input */
    private val _navigateToSelectedNews = MutableLiveData<Event<News>>()

    /**The external immutable LiveData for the click event*/
    val navigateToSelectedNews: LiveData<Event<News>>
        get() = _navigateToSelectedNews

    /**The actual LiveData object containing the news resource*/
    val newsResource = newsRepo.getNewsData()

    /**Called when a user clicks on a News item*/
    fun displayNewsDetails(news: News) {
        _navigateToSelectedNews.value = Event(news)
    }
}