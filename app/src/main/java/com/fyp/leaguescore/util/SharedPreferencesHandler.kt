package com.fyp.leaguescore.util
import android.content.Context
import android.content.SharedPreferences
import com.fyp.leaguescore.app.Constants.Companion.TOKEN
import com.fyp.leaguescore.app.LeagueScoreApplication
import javax.inject.Inject


class SharedPreferencesHandler {
    @set:Inject
    var spEditor: SharedPreferences.Editor? = null


    //save SharedPreferences
    fun saveIntoSharedPreferences(context: Context, token: String?) {
        (context.applicationContext as LeagueScoreApplication).getAppComponent()?.inject(this)
        spEditor!!.putString(TOKEN, token)
        spEditor!!.apply()
    }

    //clear SharedPreferences
    fun clearSharedPreferences(context: Context) {
        (context.applicationContext as LeagueScoreApplication).getAppComponent()?.inject(this)
        spEditor!!.clear()
        spEditor!!.commit()
    }

    fun logoutClearSharedPref(context: Context) {
        (context.applicationContext as LeagueScoreApplication).getAppComponent()?.inject(this)
        spEditor!!.remove(TOKEN)
        spEditor!!.commit()
    }


}