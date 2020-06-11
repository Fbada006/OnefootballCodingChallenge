package com.onefootball

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.onefootball.repo.NewsRepo

/**This class will prepare and manage the data to present to the UI*/
class NewsViewModel(application: Application) : AndroidViewModel(application) {

    /**The actual LiveData object containing the news*/
    val news = NewsRepo(application).getNewsData()
}