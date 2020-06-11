package com.onefootball.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.onefootball.R
import com.onefootball.model.News
import com.onefootball.utils.NewsDiffCallback

/**The [ListAdapter] is a better implementation because it also computes diffs between lists
 * all in the background using the [NewsDiffCallback]
 * */
class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)

        holder.titleView.text = news.title
        holder.newsView.load(url = news.imageURL)
        holder.resourceImage.load(url = news.resourceURL)
        holder.resourceName.text = news.resourceName
        holder.itemView.setOnClickListener {
            it.context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(news.newsLink))
            )
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleView: TextView = itemView.findViewById(R.id.news_title)
        var newsView: ImageView = itemView.findViewById(R.id.news_view)
        var resourceImage: ImageView = itemView.findViewById(R.id.resource_icon)
        var resourceName: TextView = itemView.findViewById(R.id.resource_name)
    }
}