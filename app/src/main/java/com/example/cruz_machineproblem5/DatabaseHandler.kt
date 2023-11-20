package com.example.cruz_machineproblem5

// DatabaseHandler.kt

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "userDatabase"
        private const val TABLE_USERS = "users"

        private const val KEY_EMAIL = "email"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUserTable = ("CREATE TABLE $TABLE_USERS($KEY_EMAIL TEXT PRIMARY KEY, $KEY_FIRST_NAME TEXT, $KEY_LAST_NAME TEXT, $KEY_USERNAME TEXT, $KEY_PASSWORD TEXT)")
        db.execSQL(createUserTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // Function to add a new user
    fun addUser(email: String, firstName: String, lastName: String, username: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_EMAIL, email)
        values.put(KEY_FIRST_NAME, firstName)
        values.put(KEY_LAST_NAME, lastName)
        values.put(KEY_USERNAME, username)
        values.put(KEY_PASSWORD, password)
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    // Function to check if a user with a given username and password exists
    fun checkUser(username: String, password: String): Boolean {
        val columns = arrayOf(KEY_USERNAME)
        val db = this.readableDatabase
        val selection = "$KEY_USERNAME = ? AND $KEY_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()
        db.close()

        return count > 0
    }
}
