package com.academy.it.mvpexample.presentation.newslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.academy.it.mvpexample.OnNewsActionListener
import com.academy.it.mvpexample.R
import com.google.android.material.snackbar.Snackbar

class NewsListFragment : Fragment(), NewsListView {

    private val presenter = NewsListPresenterImpl(this)

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val newsListAdapter by lazy { NewsListAdapter { data -> onNewsActionListener?.openNews(data.url) } }
    private var onNewsActionListener: OnNewsActionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNewsActionListener) {
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
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.apply {
            adapter = newsListAdapter
            layoutManager = LinearLayoutManager(this@NewsListFragment.context, RecyclerView.VERTICAL, false)
        }
        presenter.fetchNewsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.close()
        onNewsActionListener = null
    }

    override fun onStartLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onStopLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showNewsList(newsList: List<NewsDataViewModel>) {
        newsListAdapter.updateItems(newsList)
    }

    override fun onError(message: String) {
        view?.run { Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show() }
    }

    companion object {
        fun newInstance() = NewsListFragment()
    }
}