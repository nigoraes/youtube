package com.example.youtube.data.local

import android.content.Context

class Preference(context: Context) {

    private val preference = context.getSharedPreferences("youtube_api", Context.MODE_PRIVATE)

    var onBoard: Boolean
    get()  = preference.getBoolean("onBoard", false)
    set(value){
        preference.edit().putBoolean("onBoard", value).apply()
    }
}