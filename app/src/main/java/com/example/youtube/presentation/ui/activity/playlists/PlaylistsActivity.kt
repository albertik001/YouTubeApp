package com.example.youtube.presentation.ui.activity.playlists

import android.content.Intent
import android.view.LayoutInflater
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.common.constants.Constant
import com.example.youtube.core.network.status.Status
import com.example.youtube.core.utils.ConnectivityStatus
import com.example.youtube.data.remote.dto.Item
import com.example.youtube.presentation.ui.activity.playlists.detail.DetailPlaylistActivity
import com.example.youtube.presentation.ui.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import youtube.databinding.ActivityPlaylistsBinding

class PlaylistsActivity :
    BaseActivity<PlaylistViewModel, ActivityPlaylistsBinding>() {

    private val adapter = PlaylistAdapter(this::onItemClick)
    override val viewModel: PlaylistViewModel by viewModel()
    private val checkNet: ConnectivityStatus by inject()

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
                    binding.progressBar.isVisible = false
                }
                Status.ERROR -> {
                    Snackbar.make(binding.root, "Error${it.message}", Snackbar.LENGTH_LONG)
                        .setAction("RETRY") {
                            checkInternet()
                        }
                        .show()
                }
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(inflater)
    }

    override fun checkInternet() {
        checkNet.observe(this) {
            binding.progressBar.isVisible = true
            if (it) {
                binding.progressBar.isVisible = false
                binding.includedNoInternet.root.isInvisible = true
                binding.recyclerPlaylist.isVisible = true
                initViewModel()
            } else {
                binding.progressBar.isVisible = true
                binding.includedNoInternet.root.isInvisible = false
                binding.recyclerPlaylist.isVisible = false
            }
        }

    }
}