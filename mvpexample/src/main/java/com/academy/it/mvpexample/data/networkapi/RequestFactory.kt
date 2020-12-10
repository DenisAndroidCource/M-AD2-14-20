package com.academy.it.mvpexample.data.networkapi

import okhttp3.Request

interface RequestFactory {
    fun getTopHeadLinesRequest(country: String) : Request
}