package com.example.topalbumsapp.features.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topalbumsapp.R
import com.example.topalbumsapp.databinding.FragmentAlbumListBinding
import com.example.topalbumsapp.features.ui.adapters.AlbumsAdapter
import com.example.topalbumsapp.features.viewmodels.AlbumListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment : Fragment() {

    private var _binding: FragmentAlbumListBinding? = null
    private val binding: FragmentAlbumListBinding
        get() {
            val fragmentMainBinding = _binding
                ?: throw IllegalStateException(getString(R.string.viewbinding_access_error))
            return fragmentMainBinding
        }

    private val viewModel: AlbumListViewModel by viewModels()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumsAdapter = AlbumsAdapter { album ->
            val action =
                AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailsFragment(album)
            findNavController().navigate(action)
        }

        binding.recyclerViewAlbums.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = albumsAdapter
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshAlbums()
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.albums.observe(viewLifecycleOwner) { albums ->
            albumsAdapter.submitList(albums)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            viewModel.clearError()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.swipeRefresh.isRefreshing = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
