package com.onefootball.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.R
import com.onefootball.databinding.ActivityMainBinding
import com.onefootball.ui.settings.SettingsActivity
import com.onefootball.utils.OnNewsClickListener
import com.onefootball.utils.Resource.Status.ERROR
import com.onefootball.utils.Resource.Status.LOADING
import com.onefootball.utils.Resource.Status.SUCCESS
import dagger.android.AndroidInjection
import javax.inject.Inject

class MyNewsActivity : AppCompatActivity() {

    // We will use viewBinding here because the need is just to remove findViewById as recommended
    // by the Google team
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeViewModelNews() {
        viewModel.newsResource.observe(this, Observer {
            when (it.status) {
                LOADING -> showLoading()
                ERROR -> showError()
                SUCCESS -> if (!it.data.isNullOrEmpty()) {
                    showNews()
                    myAdapter.submitList(it.data)
                } else showError()
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

    private fun showNews() {
        binding.noNewsFound.visibility = View.GONE
        binding.loadingSpinner.visibility = View.GONE
    }
}