package by.it.academy.cleanarchitecture.api

import okhttp3.Request

interface RequestFactory {
    fun getTopHeadLinesRequest(country: String) : Request
}