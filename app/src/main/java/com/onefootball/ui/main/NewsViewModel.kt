package com.onefootball.ui.main

import androidx.lifecycle.ViewModel
import com.onefootball.repo.NewsRepo
import javax.inject.Inject

/**This class will prepare and manage the data to present to the UI*/
class NewsViewModel @Inject constructor(newsRepo: NewsRepo) : ViewModel() {

    /**The actual LiveData object containing the news resource*/
    val newsResource = newsRepo.getNewsData()
}