package com.academy.it.mvvmexample.presentation.newsreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.academy.it.mvvmexample.R
import com.academy.it.mvvmexample.databinding.FragmentNewsreaderBinding

class FragmentNewsReader : Fragment() {

    private lateinit var binding: FragmentNewsreaderBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_newsreader, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNewsreaderBinding.bind(view)
        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                loadsImagesAutomatically = true
            }
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            loadUrl(arguments?.getString(urlKey) ?: "")
        }
    }

    fun loadUrl(url: String) {
        binding.webView.loadUrl(url)
    }

    companion object {
        private const val urlKey = "url"
        fun newInstance(url: String) = FragmentNewsReader().apply {
            arguments = Bundle().apply { putString(urlKey, url) }
        }
    }
}