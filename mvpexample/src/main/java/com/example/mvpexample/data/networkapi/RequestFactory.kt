package com.example.networkexample.data.networkapi

import okhttp3.Request

interface RequestFactory {
    fun getTopHeadLinesRequest(country: String) : Request
}