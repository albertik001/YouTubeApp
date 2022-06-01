package com.example.youtube.presentation.ui.activity.playlists.detail

import android.view.LayoutInflater
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.common.constants.Constant
import com.example.youtube.core.network.status.Status
import com.example.youtube.core.utils.ConnectivityStatus
import com.example.youtube.data.remote.dto.Item
import com.example.youtube.presentation.ui.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import youtube.databinding.ActivityDetailPlaylistBinding

class DetailPlaylistActivity :
    BaseActivity<DetailPlaylistViewModel, ActivityDetailPlaylistBinding>() {

    override val viewModel: DetailPlaylistViewModel by viewModel()
    private val adapter = PlaylistDetailAdapter(this::onItemClick)
    private val checkNet: ConnectivityStatus by inject()

    private fun onItemClick(id: String) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDetailPlaylistBinding {
        return ActivityDetailPlaylistBinding.inflate(inflater)
    }

    override fun initView() {
        binding.recyclerPlaylistDetail.adapter = adapter
        binding.recyclerPlaylistDetail.layoutManager = LinearLayoutManager(this)
    }

    override fun initViewModel() {
        intent.getStringExtra(Constant.putId)?.let { it ->
            viewModel.getPlaylists(it).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        adapter.setList(it.data?.items as ArrayList<Item>)
                        binding.tvTitleDetail.text = it.data.items[0].snippet?.title
                        binding.tvDescriptionDetail.text =
                            it.data.items[0].snippet?.description
                        println(it.data.items[0].snippet?.localized?.title)
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
    }

    override fun checkInternet() {
        checkNet.observe(this) {
            binding.progressBar.isVisible = true
            if (it) {
                binding.progressBar.isVisible = false
                binding.noInternetCheck.root.isInvisible = true
                binding.recyclerPlaylistDetail.isVisible = true
                initViewModel()
            } else {
                binding.progressBar.isVisible = true
                binding.noInternetCheck.root.isInvisible = false
                binding.recyclerPlaylistDetail.isVisible = false
            }
        }
    }

}