package com.onefootball.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.databinding.ActivityMainBinding
import com.onefootball.utils.OnNewsClickListener
import com.onefootball.utils.Resource.Status.*
import dagger.android.AndroidInjection
import javax.inject.Inject

class MyNewsActivity : AppCompatActivity() {

    //We will use viewBinding here because the need is just to remove findViewById as recommended
    //by the Google team
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: NewsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NewsViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myAdapter = NewsAdapter(onNewsClickListener())

        with(binding.newsRecyclerView) {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MyNewsActivity)
        }

        observeViewModelNews()
    }

    private fun observeViewModelNews() {
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
        binding.loadingSpinner.visibility = View.VISIBLE
        binding.noNewsFound.visibility = View.GONE
    }

    private fun showError() {
        binding.noNewsFound.visibility = View.VISIBLE
        binding.loadingSpinner.visibility = View.GONE
    }
}