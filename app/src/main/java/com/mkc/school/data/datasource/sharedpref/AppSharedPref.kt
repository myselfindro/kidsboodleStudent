package com.mkc.school.data.datasource.sharedpref

import android.content.Context
import android.content.SharedPreferences

class AppSharedPref(context: Context, prefFileName: String) {

    companion object {

        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        private val IS_EMAIL_PASSWORD_SAVE = "IS_EMAIL_PASSWORD_SAVE"
        private val USER_LOGIN_ID = "USER_LOGIN_ID"
        private val USER_LOGIN_PASSWORD = "USER_LOGIN_PASSWORD"
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)


    var accessToken: String?
        get() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
        set(accessToken) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()

    var isEmailPasswordSave: Int?
        get() = mPrefs.getInt(IS_EMAIL_PASSWORD_SAVE, 0)
        set(data) = mPrefs.edit().putInt(IS_EMAIL_PASSWORD_SAVE, data!!).apply()

    var loginId: String?
        get() = mPrefs.getString(USER_LOGIN_ID, null)
        set(data) = mPrefs.edit().putString(USER_LOGIN_ID, data).apply()

    var loginPassword: String?
        get() = mPrefs.getString(USER_LOGIN_PASSWORD, null)
        set(data) = mPrefs.edit().putString(USER_LOGIN_PASSWORD, data).apply()


    fun logout(){
        mPrefs.edit().remove(PREF_KEY_ACCESS_TOKEN).apply()
        mPrefs.edit().remove(IS_EMAIL_PASSWORD_SAVE).apply()
        mPrefs.edit().remove(USER_LOGIN_ID).apply()
        mPrefs.edit().remove(USER_LOGIN_PASSWORD).apply()
        mPrefs.edit().clear().apply()
    }

}
