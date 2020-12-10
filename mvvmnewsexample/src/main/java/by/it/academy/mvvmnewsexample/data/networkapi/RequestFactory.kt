package by.it.academy.mvvmnewsexample.data.networkapi

import okhttp3.Request

interface RequestFactory {
    fun getTopHeadLinesRequest(country: String) : Request
}