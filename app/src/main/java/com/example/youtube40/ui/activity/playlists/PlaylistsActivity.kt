package com.example.youtube40.ui.activity.playlists

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube40.base.BaseActivity
import com.example.youtube40.common.`object`.Constant
import com.example.youtube40.databinding.ActivityPlaylistsBinding
import com.example.youtube40.model.Item
import com.example.youtube40.ui.checkinternet.ConnectionLiveData
import com.example.youtube40.ui.activity.playlists.detail.DetailPlaylistActivity

class PlaylistsActivity :
    BaseActivity<PlaylistViewModel, ActivityPlaylistsBinding>() {

    private val adapter = PlaylistAdapter(this::onItemClick)
    private lateinit var checkNet: ConnectionLiveData

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun initView() {
        binding.recyclerPlaylist.adapter = adapter
        binding.recyclerPlaylist.layoutManager = LinearLayoutManager(this)
    }

    private fun onItemClick(id: String) {
        val intent = Intent(this, DetailPlaylistActivity::class.java)
        intent.putExtra(Constant.putId, id)
        startActivity(intent)
    }

    override fun initListener() {

    }

    override fun initViewModel() {
        viewModel.getPlaylists().observe(this) {
            Toast.makeText(this, it.kind.toString(), Toast.LENGTH_SHORT).show()
            adapter.setList(it.items as ArrayList<Item>)
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(inflater)
    }

    override fun checkInternet() {
        checkNet = ConnectionLiveData(application)
        checkNet.observe(this) {
            if (it) {
                initViewModel()
                binding.recyclerPlaylist.isVisible = true
                binding.includedNoInternet.root.isInvisible = true
            } else {
                binding.recyclerPlaylist.isInvisible = true
                binding.includedNoInternet.root.isVisible = true
            }
        }
    }
}