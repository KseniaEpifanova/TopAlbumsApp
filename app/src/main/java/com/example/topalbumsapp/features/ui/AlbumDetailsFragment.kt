package com.example.topalbumsapp.features.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.topalbumsapp.R
import com.example.topalbumsapp.databinding.FragmentAlbumDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {

    private var _binding: FragmentAlbumDetailsBinding? = null
    private val binding: FragmentAlbumDetailsBinding
        get() {
            val fragmentMainBinding = _binding
                ?: throw IllegalStateException(getString(R.string.viewbinding_access_error))
            return fragmentMainBinding
        }
    private val args: AlbumDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.album = args.album
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
