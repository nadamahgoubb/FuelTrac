package com.fueltrac.model.db.storage

import android.content.Context
import android.content.SharedPreferences
import com.fueltrac.utilities.*


class SessionManager(private val context: Context) {

    private val sharedPreferences : SharedPreferences =
        context.getSharedPreferences(app_Preference, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor

    var userToken: String?
        get() = sharedPreferences.getString(Shared_user_Token, "")
        set(userToken) {
            if (userToken == null || userToken == "") {
                editor.putString(Shared_user_Token, userToken)
                editor.commit()
            } else {
                editor.putString(Shared_user_Token, "Bearer $userToken")
                editor.commit()
            }
        }

    fun clearSessionManager() {
        userToken = ""
        appVersion = -1
    }

    var appVersion: Int
        get() = sharedPreferences.getInt(app_Version, -1)
        set(appVersion) {
            editor.putInt(app_Version, appVersion)
            editor.commit()
        }

    var applang: String?
        get() = sharedPreferences.getString(app_lang, "en")
        set(applang) {
            editor.putString(app_lang, applang)
            editor.commit()
        }
    var userEmail: String?
        get() = sharedPreferences.getString(Shared_user_Email, "")
        set(userEmail) {
            editor.putString(Shared_user_Email, userEmail)
            editor.commit()
        }

    var nameOfUser: String?
        get() = sharedPreferences.getString(Shared_user_name, "")
        set(name) {
            editor.putString(Shared_user_name, name)
            editor.commit()
        }

    var userType : Long
        get() = sharedPreferences.getLong(Shared_user_Type, -1)
        set(userId) {
            editor.putLong(Shared_user_Type, userId)
            editor.commit()
        }

    init {
        editor = sharedPreferences.edit()
        editor.apply()
    }
}