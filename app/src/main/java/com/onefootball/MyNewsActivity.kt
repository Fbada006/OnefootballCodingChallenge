package com.onefootball

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onefootball.adapter.NewsAdapter

class MyNewsActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: NewsAdapter

    private val viewModel by viewModels<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.newsRecyclerView)
        myAdapter = NewsAdapter()
        with(recyclerView) {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MyNewsActivity)
        }

        viewModel.news.observe(this, Observer {
            myAdapter.submitList(it)
        })
    }
}