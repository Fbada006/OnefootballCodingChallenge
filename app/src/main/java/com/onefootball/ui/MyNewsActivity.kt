package com.onefootball.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onefootball.R
import com.onefootball.utils.OnNewsClickListener
import dagger.android.AndroidInjection
import javax.inject.Inject

class MyNewsActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: NewsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NewsViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.newsRecyclerView)
        myAdapter = NewsAdapter(onNewsClickListener())

        with(recyclerView) {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MyNewsActivity)
        }

        viewModel.news.observe(this, Observer {
            myAdapter.submitList(it)
        })
    }

    private fun onNewsClickListener() = OnNewsClickListener { news ->
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news?.newsLink)))
    }
}