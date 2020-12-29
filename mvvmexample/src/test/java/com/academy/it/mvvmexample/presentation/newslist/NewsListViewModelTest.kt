package com.academy.it.mvvmexample.presentation.newslist

import com.academy.it.mvvmexample.data.NewsData
import com.academy.it.mvvmexample.data.networkapi.NewsApi
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

class NewsListViewModelTest {

    private val newsApiMock = mock(NewsApi::class.java).apply {
        val single = Single.just(listOf(NewsData("title", "desc", "url", "urlImg")))
        Mockito.`when`(getTopHeadlines(anyString())).thenReturn(single)
    }
    private val newsDataViewModelMapper = NewsDataViewModelMapper()

    private val viewModel = NewsListViewModel(newsApiMock, newsDataViewModelMapper)

    @Test
    fun `when fetch data success then all data received`() {
        viewModel.fetchNewsList()

        val result = viewModel.newsDataViewModelLiveData.value
        assertEquals(1, result.size)
        Mockito.verify(newsApiMock.getTopHeadlines(anyString()), times(5))
    }


}