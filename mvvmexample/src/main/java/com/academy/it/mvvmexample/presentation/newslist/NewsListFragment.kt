package com.academy.it.mvvmexample.presentation.newslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.academy.it.mvvmexample.OnNewsActionListener
import com.academy.it.mvvmexample.R

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsListViewModel

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
        initRecyclerView(view)
        initViewModel()
        viewModel.fetchNewsList()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NewsListViewModel::class.java)
        viewModel.newsDataViewModelLiveData.observe(viewLifecycleOwner, Observer { newsList -> newsListAdapter.updateItems(newsList) })
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.apply {
            adapter = newsListAdapter
            layoutManager = LinearLayoutManager(this@NewsListFragment.context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onNewsActionListener = null
    }

    companion object {
        fun newInstance() = NewsListFragment()
    }
}