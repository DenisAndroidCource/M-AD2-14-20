package by.it.academy.mvvmnewsexample.presentation.newslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.mvvmnewsexample.NewsListAdapter
import by.it.academy.mvvmnewsexample.R
import by.it.academy.mvvmnewsexample.onNewsActionListener

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsListViewModel
    private lateinit var recyclerView: RecyclerView

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
        viewModel = ViewModelProvider(viewModelStore, ViewModelProvider.NewInstanceFactory())
            .get(NewsListViewModel::class.java)

        viewModel.liveData
            .observe(viewLifecycleOwner, Observer { newsList -> newsListAdapter.updateItems(newsList) })

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.apply {
            adapter = newsListAdapter
            layoutManager = LinearLayoutManager(this@NewsListFragment.context, RecyclerView.VERTICAL, false)
        }

        viewModel.fetchNewsList()
    }
}

