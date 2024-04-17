package com.abhay.dynamicappicon

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    // Function to save a String value
    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // Function to retrieve a String value with a default value
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    // Similar functions for other data types (Int, Boolean, etc.)
    fun saveInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    // Function to remove a specific key-value pair
    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    // Function to clear all stored data
    fun clearAll() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
