package com.example.centercropimage

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.centercropimage.databinding.FragmentCenterCropBinding

class ImageCenterCrop : Fragment() {

    private lateinit var binding: FragmentCenterCropBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCenterCropBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root)
            .load(R.drawable.center_crop_bg_2)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {

                    binding.clImageThirdCenterCrop.background = resource
                    Log.d("", "zzzd loaded")

                    return true
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }
            })
            .into(binding.imgCropCenterFour)


        val radiusInDp = 8f
        val radiusInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            radiusInDp,
            binding.imgCropCenterFour.context.resources.displayMetrics
        ).toInt()

        Glide.with(binding.root)
            .load(R.drawable.center_crop_bg_2)
            .centerCrop()
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(radiusInPixels)))
            .into(binding.imgCropCenterFour)


        Glide.with(binding.root)
            .load(R.drawable.center_crop_bg_2)
            .into(binding.imgCropCenterFive)
    }

//    Glide.with(binding.root)
//    .asBitmap()
//    .load(R.drawable.center_crop_bg_2)
//    .centerCrop()
//    .into(object: CustomTarget<Bitmap>() {
//        override fun onResourceReady(
//            resource: Bitmap,
//            transition: Transition<in Bitmap>?
//        ) {
//            binding.clImageThirdCenterCrop.background = BitmapDrawable(context?.resources, resource)
//        }
//
//        override fun onLoadCleared(placeholder: Drawable?) {
//            TODO("Not yet implemented")
//        }
//    })

}