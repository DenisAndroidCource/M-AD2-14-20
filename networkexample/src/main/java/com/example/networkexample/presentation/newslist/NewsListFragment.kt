package com.example.networkexample.presentation.newslist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkexample.NewsListAdapter
import com.example.networkexample.R
import com.example.networkexample.data.networkapi.NewsApiImpl
import com.example.networkexample.onNewsActionListener
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class NewsListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val newsApi = NewsApiImpl()
    private val newsListAdapter by lazy { NewsListAdapter { data -> onNewsActionListener?.openNews(data.url) } }
    private var onNewsActionListener: onNewsActionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is onNewsActionListener) {
            onNewsActionListener = context
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_newslist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.apply {
            adapter = newsListAdapter
            layoutManager = LinearLayoutManager(this@NewsListFragment.context, RecyclerView.VERTICAL, false)
        }
        newsApi.getTopHeadlines("US")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { dataList -> newsListAdapter.updateItems(dataList) },
                        { Snackbar.make(view, R.string.error_message, Snackbar.LENGTH_SHORT).show() }
                )
    }


}

