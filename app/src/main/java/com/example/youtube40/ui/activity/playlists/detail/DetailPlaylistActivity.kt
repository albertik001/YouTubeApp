package com.example.youtube40.ui.activity.playlists.detail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtube40.base.BaseActivity
import com.example.youtube40.common.`object`.Constant
import com.example.youtube40.databinding.ActivityDetailPlaylistBinding

class DetailPlaylistActivity :
    BaseActivity<DetailPlaylistViewModel, ActivityDetailPlaylistBinding>() {

    override val viewModel: DetailPlaylistViewModel by lazy {
        ViewModelProvider(this)[DetailPlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDetailPlaylistBinding {
        return ActivityDetailPlaylistBinding.inflate(inflater)
    }

    override fun initView() {
        Toast.makeText(this, intent.getStringExtra(Constant.putId), Toast.LENGTH_SHORT).show()
    }

}