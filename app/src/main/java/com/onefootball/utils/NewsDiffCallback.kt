package com.onefootball.utils

import androidx.recyclerview.widget.DiffUtil
import com.onefootball.model.News

/** DiffUtil is a utility class that calculates the difference between two lists and outputs a
 * list of update operations that converts the first list into the second one.*/
class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}