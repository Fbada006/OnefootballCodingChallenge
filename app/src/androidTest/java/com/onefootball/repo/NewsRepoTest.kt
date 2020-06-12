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

    @Test
    fun getNewsData_returnsAllTheNewsInAssets() {
        val news =
            NewsRepo(ApplicationProvider.getApplicationContext()).getNewsData()
        SystemClock.sleep(2000)
        Truth.assertThat(news.getOrAwaitValue().data!!.size).isEqualTo(10)
    }
}