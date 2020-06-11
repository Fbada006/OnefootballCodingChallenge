package com.onefootball.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.onefootball.R
import com.onefootball.model.News
import com.onefootball.utils.NewsDiffCallback
import kotlinx.android.synthetic.main.news_item.view.*

/**The [ListAdapter] is a better implementation because it also computes diffs between lists
 * all in the background using the [NewsDiffCallback]
 * */
class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder.from(parent)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //Bind views to data
        fun bind(news: News) {
            itemView.news_title.text = news.title
            itemView.news_view.load(uri = news.imageURL)
            itemView.resource_icon.load(uri = news.resourceURL)
            itemView.resource_name.text = news.resourceName
            itemView.setOnClickListener {
                it.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(news.newsLink))
                )
            }
        }

        //For inflating the layout in onCreateViewHolder()
        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
                return NewsViewHolder(view)
            }
        }
    }
}