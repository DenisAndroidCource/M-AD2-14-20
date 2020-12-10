package com.example.mvpexample.presentation.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpexample.databinding.ItemNewsBinding

class NewsListAdapter(
        private val itemClickListener: (NewsDataViewModel) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.ItemViewHolder>() {

    private val itemList = mutableListOf<NewsDataViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemNewsBinding.inflate(inflater), itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun updateItems(listNewsData: List<NewsDataViewModel>) {
        itemList.apply {
            clear()
            addAll(listNewsData)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(
            private val binding: ItemNewsBinding,
            private val itemClickListener: (NewsDataViewModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsData: NewsDataViewModel) {
            with(binding) {
                textViewTitle.text = newsData.title
                textViewDescription.text = newsData.description
                Glide.with(root.context)
                        .load(newsData.urlToImage)
                        .into(imagePreview)
                root.setOnClickListener { itemClickListener(newsData) }
            }

        }
    }
}