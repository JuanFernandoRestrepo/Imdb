package com.example.data.local

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences(USER_SESSION_PREFS, Context.MODE_PRIVATE)

    fun saveUser(email: String, name: String) {
        prefs.edit()
            .putString("email", email)
            .putString("name", name)
            .apply()
    }

    fun getUserEmail(): String? = prefs.getString("email", null)
    fun getUserName(): String? = prefs.getString("name", null)
    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
