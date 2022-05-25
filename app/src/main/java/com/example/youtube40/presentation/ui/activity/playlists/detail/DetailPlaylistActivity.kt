package com.example.youtube40.presentation.ui.activity.playlists.detail

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
import com.example.youtube40.databinding.ActivityDetailPlaylistBinding
import com.example.youtube40.presentation.ui.base.BaseActivity

class DetailPlaylistActivity :
    BaseActivity<DetailPlaylistViewModel, ActivityDetailPlaylistBinding>() {

    override val viewModel: DetailPlaylistViewModel by lazy {
        ViewModelProvider(this)[DetailPlaylistViewModel::class.java]
    }
    private val adapter = PlaylistDetailAdapter(this::onItemClick)

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
                        adapter.setList(it.data?.items as ArrayList<Item>)
                    }
                    Status.ERROR -> {}
                    Status.LOADING -> {
                        Toast.makeText(this, "HELLO", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun checkInternet() {
        val checkNet = ConnectivityStatus(this)
        checkNet.observe(this) {
            if (it) {
                binding.noInternetCheck.root.isInvisible = true
                binding.recyclerPlaylistDetail.isVisible = true
                initViewModel()
            } else {
                Toast.makeText(this, "FUCK", Toast.LENGTH_SHORT).show()
                binding.recyclerPlaylistDetail.isInvisible = true
                binding.noInternetCheck.root.isVisible = true
            }
        }
    }

}