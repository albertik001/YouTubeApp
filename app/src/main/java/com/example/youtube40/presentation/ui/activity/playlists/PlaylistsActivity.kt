package com.example.youtube40.presentation.ui.activity.playlists

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube40.common.constants.Constant
import com.example.youtube40.core.network.status.Status
import com.example.youtube40.core.utils.ConnectivityStatus
import com.example.youtube40.data.remote.model.Item
import com.example.youtube40.databinding.ActivityPlaylistsBinding
import com.example.youtube40.presentation.ui.activity.playlists.detail.DetailPlaylistActivity
import com.example.youtube40.presentation.ui.base.BaseActivity

class PlaylistsActivity :
    BaseActivity<PlaylistViewModel, ActivityPlaylistsBinding>() {

    private val adapter = PlaylistAdapter(this::onItemClick)
    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.includedNoInternet.btnTryAgain.setOnClickListener {
            checkInternet()
        }
    }

    override fun initViewModel() {
        viewModel.getPlaylists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.setList(it.data?.items as ArrayList<Item>)
                }
                Status.ERROR -> {}
                Status.LOADING -> {
                    Toast.makeText(this, "HELLO", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(inflater)
    }

    override fun checkInternet() {
        val checkNet = ConnectivityStatus(this)
        checkNet.observe(this) {
            if (it) {
                binding.includedNoInternet.root.isInvisible = true
                binding.recyclerPlaylist.isVisible = true
                initViewModel()
            } else {
                Toast.makeText(this, "FUCK", Toast.LENGTH_SHORT).show()
                binding.recyclerPlaylist.isInvisible = true
                binding.includedNoInternet.root.isVisible = true
            }
        }

    }
}