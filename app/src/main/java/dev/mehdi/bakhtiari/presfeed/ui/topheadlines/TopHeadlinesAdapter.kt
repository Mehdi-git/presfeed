package dev.mehdi.bakhtiari.presfeed.ui.topheadlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.mehdi.bakhtiari.presfeed.data.model.Article
import dev.mehdi.bakhtiari.presfeed.databinding.ItemHeadlineBinding

class TopHeadlinesAdapter(private val onItemClick: (Article) -> Unit ): ListAdapter<Article, TopHeadlinesAdapter.ArticleViewHolder> (diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHeadlineBinding.inflate(layoutInflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class ArticleViewHolder(
        private var binding: ItemHeadlineBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Article, onItemClick: (Article) -> Unit) {
            binding.apply {
                article = item
                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}