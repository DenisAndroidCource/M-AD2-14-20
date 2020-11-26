package com.example.networkexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkexample.data.NewsData
import com.example.networkexample.databinding.ItemNewsBinding

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ItemViewHolder>() {

    private val itemList = mutableListOf<NewsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemNewsBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun updateItems(listNewsData: List<NewsData>) {
        itemList.apply {
            clear()
            addAll(listNewsData)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsData: NewsData) {
            with(binding) {
                textViewTitle.text = newsData.title
                textViewDescription.text = newsData.description
                Glide.with(root.context)
                        .load(newsData.urlToImage)
                        .into(imagePreview)
            }

        }
    }
}