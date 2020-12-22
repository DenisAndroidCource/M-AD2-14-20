package com.example.contentprovicerexample

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class DataProvider: ContentProvider() {

    private lateinit var database: Database

    override fun onCreate(): Boolean {
        database = Database()
        return true
    }

    override fun query(
            uri: Uri,
            projection: Array<out String>?,
            selection: String?,
            selectionArgs: Array<out String>?,
            sortOrder: String?
    ): Cursor? {
        return when(uriMatcher.match(uri)) {
            DATA_ACTION -> database.data
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return when(uriMatcher.match(uri)) {
            DATA_ACTION_EXACT -> "object/uri"
            DATA_ACTION -> "object/data"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
//        return when(uriMatcher.match(uri)) {
//            DATA_ACTION_EXACT -> database.insert()
//            else -> null
//        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    //https://www.it-academy.by/course/java-developer/jd1-programmirovanie-na-java/
    companion object {
        private const val DATA_ACTION = 1
        private const val DATA_ACTION_EXACT = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI("com.example.contentprovicerexample", "data/data", DATA_ACTION)
            addURI("com.example.contentprovicerexample", "data/data/#", DATA_ACTION_EXACT)
        }
    }
}