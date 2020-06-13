package com.onefootball.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onefootball.databinding.NewsItemBinding
import com.onefootball.model.News
import com.onefootball.utils.NewsDiffCallback
import com.onefootball.utils.OnNewsClickListener

/**The [ListAdapter] is a better implementation because it also computes diffs between lists
 * all in the background using the [NewsDiffCallback]
 * */
class NewsAdapter(private val clickListener: OnNewsClickListener) :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder.from(parent)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News, clickListener: OnNewsClickListener) {
            binding.news = news
            binding.clickListener = clickListener
        }

        // For inflating the layout in onCreateViewHolder()
        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val binding =
                    NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return NewsViewHolder(binding)
            }
        }
    }
}