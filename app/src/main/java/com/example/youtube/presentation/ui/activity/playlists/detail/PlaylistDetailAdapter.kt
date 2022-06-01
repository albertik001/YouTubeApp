package com.example.youtube.presentation.ui.activity.playlists.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.common.extensions.loadWithGlide
import com.example.youtube.data.remote.dto.Item
import youtube.databinding.ItemDetailPlaylistBinding

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
                tvThemes.text = playlist.snippet?.title.toString()
                playlist.snippet?.thumbnails?.medium?.url?.let { imBanner.loadWithGlide(it) }
                timeVideo.text = playlist.kind.toString()
            }
        }
    }
}