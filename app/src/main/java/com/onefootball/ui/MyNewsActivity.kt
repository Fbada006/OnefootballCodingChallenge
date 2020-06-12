package com.onefootball.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onefootball.R
import com.onefootball.utils.OnNewsClickListener
import com.onefootball.utils.Resource.Status.*
import dagger.android.AndroidInjection
import javax.inject.Inject

class MyNewsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: NewsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var noNewsTextView: TextView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NewsViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.loading_spinner)
        noNewsTextView = findViewById(R.id.no_news_found)
        recyclerView = findViewById(R.id.newsRecyclerView)
        myAdapter = NewsAdapter(onNewsClickListener())

        with(recyclerView) {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MyNewsActivity)
        }

        viewModel.news.observe(this, Observer {
            when (it.status) {
                LOADING -> showLoading()
                ERROR -> showError()
                SUCCESS -> if (!it.data.isNullOrEmpty()) myAdapter.submitList(it.data) else showError()
            }
        })
    }

    private fun onNewsClickListener() = OnNewsClickListener { news ->
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news?.newsLink)))
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        noNewsTextView.visibility = View.GONE
    }

    private fun showError() {
        noNewsTextView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}