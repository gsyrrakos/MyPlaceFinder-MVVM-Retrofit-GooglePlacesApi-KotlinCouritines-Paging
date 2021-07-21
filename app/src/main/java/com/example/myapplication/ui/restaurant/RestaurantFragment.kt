package com.example.myapplication.ui.restaurant

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.myapplication.R


import com.example.myapplication.data.PlacesStoreClasses
import com.example.myapplication.databinding.FragmentRestaurantsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment:Fragment(R.layout.fragment_restaurants) ,Adapter.OnItemClickListener{

    private val viewModel by viewModels<RestaurantViewModel>()
    private var _binding: FragmentRestaurantsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRestaurantsBinding.bind(view)
        val adapter = Adapter(this)
        binding.apply {
            recycleView.setHasFixedSize(true)
            recycleView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter{adapter.retry()} ,
                footer = PhotoLoadStateAdapter{adapter.retry()}
            )
            buttonRetry.setOnClickListener {
                adapter.retry()
            }
        }
        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)

        }
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recycleView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1) {
                    recycleView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }

        setHasOptionsMenu(true)



    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(photo: PlacesStoreClasses.PlacesResult) {
      val action=RestaurantFragmentDirections.actionRestaurantFragment2ToDetailsFragment(photo)
        findNavController().navigate(action)
    }


}