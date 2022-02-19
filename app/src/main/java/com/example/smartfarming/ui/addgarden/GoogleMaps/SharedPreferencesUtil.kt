package com.example.smartfarming.ui.addgarden.GoogleMaps

import android.content.Context
import android.location.Location
import com.example.smartfarming.R

fun Location?.toText() : String {
    return if (this != null){
        "($latitude, $longitude)"
    } else {
        "Unknown location"
    }
}

/*internal object SharedPreferencesUtil {
    const val KEY_FOREGROUND_ENABLED = "tracking_foreground_location"
    fun getLocationTrackingPref(context: Context) : Boolean =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE).getBoolean(KEY_FOREGROUND_ENABLED, false)
        )
}*/