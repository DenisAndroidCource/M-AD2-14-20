package by.it.academy.cleanarchitecture.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.cleanarchitecture.R
import by.it.academy.cleanarchitecture.databinding.FragmentNewslistBinding

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsListViewModel
    private lateinit var binding: FragmentNewslistBinding

    private val newsListAdapter by lazy { NewsListItemViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_newslist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewslistBinding.bind(view)
        initRecyclerView()
        initViewModel()
        viewModel.fetchNewsList()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NewsListViewModel::class.java)
        viewModel.newsItemListLiveData
            .observe(viewLifecycleOwner, Observer { newsList ->
                newsListAdapter.updateItems(newsList)
                binding.progressBar.visibility = View.GONE
            })
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = newsListAdapter
            layoutManager = LinearLayoutManager(this@NewsListFragment.context, RecyclerView.VERTICAL, false)
        }
    }

    companion object {
        fun newInstance() = NewsListFragment()
    }
}