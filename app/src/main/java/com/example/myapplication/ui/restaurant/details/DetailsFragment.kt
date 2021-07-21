package com.example.myapplication.ui.restaurant.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.myapplication.R

import com.bumptech.glide.request.target.Target
import com.example.myapplication.databinding.FragmentDetailsBinding

class DetailsFragment:Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)
        lateinit var photoref: String

        binding.apply {
            val photo = args.photo
            if (!photo.photos.isEmpty()) {
                photoref = photo.photos[0].photo_reference

            } else {
                photoref = "no image"
            }

            Glide.with(this@DetailsFragment)
                .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=4000&photoreference=${photoref}&key=AIzaSyDczO1dBGYCmxDonWrK-WcQe4cAm2jrMx0")
                .error(R.drawable.ic_baseline_restaurant_menu_24)
                .listener(object : RequestListener<Drawable> {
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
                        textViewAdresss.isVisible = true
                        textViewDescript.isVisible = photo.name != null
                        return false
                    }
                })
                .into(imageView)

            textViewDescript.text = photo.name
            textViewAdresss.text=photo.formatted_address





        }
    }
}