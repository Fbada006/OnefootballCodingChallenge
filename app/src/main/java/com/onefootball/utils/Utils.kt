package com.onefootball.utils

import org.json.JSONArray
import org.json.JSONObject

/**An extension function to iterate over a JSONArray*/
fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
    for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
}