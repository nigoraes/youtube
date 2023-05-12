package com.example.youtube.ui.playlists

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.network.result.Status
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.databinding.ActivityPlaylistsBinding
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.ui.detail.DetailActivity
import com.example.youtube.ui.playlists.adapter.PlaylistsAdapter

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    private lateinit var adapter: PlaylistsAdapter

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun initViews() {
        super.initViews()
        adapter = PlaylistsAdapter(this::onClick)
        binding.recyclerview.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getplaylist().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerview.adapter = adapter
                    adapter.addList(it.data?.items!! as List<Playlist.Item>)
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
    override fun isConnection() {
        super.isConnection()
        if (!isInternetAvailable()) {
            binding.internetCheck.titleTextView.isVisible = true
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun onClick(item: Playlist.Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(ID, item.id)
        startActivity(intent)
    }


    companion object {
        const val ID = "ID"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}
