package com.mkc.school.data.datasource.sharedpref

import android.content.Context
import android.content.SharedPreferences

class AppSharedPref(context: Context, prefFileName: String) {

    companion object {

        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)


    var accessToken: String?
        get() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
        set(accessToken) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()


    fun logout(){
        mPrefs.edit().remove(PREF_KEY_ACCESS_TOKEN).apply()
        mPrefs.edit().clear().apply()
    }

}
