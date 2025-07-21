package com.example.topalbumsapp.features.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.topalbumsapp.R
import com.example.topalbumsapp.databinding.ItemAlbumBinding
import com.example.topalbumsapp.models.Album

class AlbumsAdapter(
    private val onItemClick: (Album) -> Unit
) : ListAdapter<Album, AlbumsAdapter.AlbumViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAlbumBinding.inflate(inflater, parent, false)
        return AlbumViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AlbumViewHolder(
        private val binding: ItemAlbumBinding,
        private val onItemClick: (Album) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album) = with(binding) {
            album = item

            Glide.with(root.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(binding.imageAlbum)
            root.setOnClickListener {
                onItemClick(item)
            }
            executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }
}
