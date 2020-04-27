package com.kazi.test.ui.employeesList.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vu.mobile.R
import com.vu.mobile.data.model.Image
import com.vu.mobile.databinding.ItemImageBinding
import com.xwray.groupie.databinding.BindableItem

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-07.
 */
class ImageItem(private val im: Image, val onLikeClickedListener :OnLikeClickedListener ) : BindableItem<ItemImageBinding>() {

    override fun getLayout() = R.layout.item_image

    override fun bind(viewBinding: ItemImageBinding, position: Int) {
        viewBinding.employee = im

        val options = RequestOptions()
            .placeholder(R.drawable.free_avatars_cons)
            .error(R.drawable.free_avatars_cons)

        val url = im.avatar
        Glide.with(viewBinding.ivEmpImage.context).load(url)
            .apply(options)
            .into(viewBinding.ivEmpImage);


        viewBinding.ivEmpImage.setOnClickListener {
            onLikeClickedListener.onLikeClicked(im)
        }
    }

    interface OnLikeClickedListener {
        fun onLikeClicked(item: Image)
    }
}