package com.example.youtube.ui.detail

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.network.result.Status
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.model.PlaylistItems
import com.example.youtube.databinding.ActivityDetailBinding
import com.example.youtube.ui.detail.adapter.DatailAdapter
import com.example.youtube.ui.playlists.PlaylistsActivity.Companion.ID
import com.example.youtube.ui.playlists.adapter.PlaylistsAdapter

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }
    private lateinit var adapter: DatailAdapter

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        adapter = DatailAdapter()
        binding.recyclerview.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        val getIntent =
            intent.getStringExtra(ID)
        viewModel.getPlaylistItem(getIntent!!).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerview.adapter = adapter
                    adapter.addList(it.data?.items!! as List<PlaylistItems.Item>)
                    binding.progressBar.isVisible = false

                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false

                }
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }


}