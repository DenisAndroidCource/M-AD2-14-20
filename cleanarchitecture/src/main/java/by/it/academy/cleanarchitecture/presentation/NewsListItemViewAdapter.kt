package by.it.academy.cleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.cleanarchitecture.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class NewsListItemViewAdapter: RecyclerView.Adapter<NewsListItemViewAdapter.ItemViewHolder>() {

    private val itemList = mutableListOf<NewsItemView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemNewsBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun updateItems(listNewsData: List<NewsItemView>) {
        itemList.apply {
            clear()
            addAll(listNewsData)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(
            private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsData: NewsItemView) {
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