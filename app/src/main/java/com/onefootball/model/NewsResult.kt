package com.onefootball.model

/**This class represents the entire JSON we have in the assets folder containing
 *  the list of [News]
 * */
data class NewsResult(
    val news: List<News>
)