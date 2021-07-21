package com.example.myapplication.ui.restaurant

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myapplication.R


import com.example.myapplication.data.PlacesStoreClasses
import com.example.myapplication.databinding.RestaurantsBinding

class Adapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<PlacesStoreClasses.PlacesResult, Adapter.PhotoViewHolder>(Photo_Comperator) {


    inner class PhotoViewHolder(private val binding: RestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {

                    val item = getItem(position)
                    if (item != null) {

                        listener.onItemClick(item)
                    }
                }

            }


        }

        lateinit var photoref: String

        fun bind(photo: PlacesStoreClasses.PlacesResult) {
//check if ii have parse images
            if (photo.photos.isNotEmpty()) {
                photoref = photo.photos[0].photo_reference

            } else {
                photoref = "no image"
            }
            binding.apply {
                Glide.with(itemView)
                    .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=4000&photoreference=${photoref}&key=AIzaSyDczO1dBGYCmxDonWrK-WcQe4cAm2jrMx0")
                    .centerCrop().transition(
                        DrawableTransitionOptions.withCrossFade()
                    ) .error(R.drawable.ic_launcher_background).listener(object :
                        RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.isVisible = false

                            return false
                        }
                    }).into(
                        imageView

                    )
                textviewuser.text = photo.name
                Log.i("MainActivity", "onresponse1  $photoref")


            }

        }

    }

    interface OnItemClickListener {

        fun onItemClick(photo: PlacesStoreClasses.PlacesResult)

    }

    companion object {


        private val Photo_Comperator = object : DiffUtil.ItemCallback<PlacesStoreClasses.PlacesResult>() {
            override fun areItemsTheSame(oldItem: PlacesStoreClasses.PlacesResult, newItem: PlacesStoreClasses.PlacesResult) =
                oldItem.name == newItem.name


            override fun areContentsTheSame(
                oldItem: PlacesStoreClasses.PlacesResult,
                newItem: PlacesStoreClasses.PlacesResult
            ) = oldItem == newItem
        }


    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = RestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }
}