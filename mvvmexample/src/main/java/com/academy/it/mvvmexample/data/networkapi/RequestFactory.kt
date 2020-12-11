package com.academy.it.mvvmexample.data.networkapi

import okhttp3.Request

interface RequestFactory {
    fun getTopHeadLinesRequest(country: String) : Request
}