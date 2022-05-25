package com.example.youtube40.presentation.ui.activity.playlists.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube40.common.extensions.loadWithGlide
import com.example.youtube40.data.remote.model.Item
import com.example.youtube40.databinding.ItemDetailPlaylistBinding

class PlaylistDetailAdapter(
    private val onItemClick: (id: String) -> Unit
) : RecyclerView.Adapter<PlaylistDetailAdapter.ListViewHolder>() {

    private var list = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemDetailPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: ArrayList<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemDetailPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(playlist: Item) {
            binding.apply {
                tvTitleDetail.text = playlist.snippet?.title.toString()
                playlist.snippet?.thumbnails?.medium?.url?.let { imgDetail.loadWithGlide(it) }
                tvDate.text = playlist.kind.toString()
            }
        }
    }
}