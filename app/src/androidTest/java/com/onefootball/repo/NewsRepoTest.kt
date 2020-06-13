package com.onefootball.repo

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.onefootball.getOrAwaitValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsRepoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**Make sure that the app gets the correct data from the assets folder.
     * If the sleep() code is removed, the test will fail
     * Note: There is no need for test doubles here because the data is all local
     * */
    @Test
    fun getNewsData_returnsAllTheNewsInAssets() {
        val news =
            NewsRepo(ApplicationProvider.getApplicationContext()).getNewsData()
        SystemClock.sleep(2000)
        Truth.assertThat(news.getOrAwaitValue().data!!.size).isEqualTo(10)
    }
}