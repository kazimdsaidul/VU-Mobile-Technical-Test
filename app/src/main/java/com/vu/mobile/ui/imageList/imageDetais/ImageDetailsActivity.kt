package com.vu.mobile.ui.imageList.imageDetais

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vu.mobile.R
import kotlinx.android.synthetic.main.activity_image_details.*
import kotlinx.android.synthetic.main.content_image_details.*


class ImageDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        setSupportActionBar(toolbar)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        val url = intent.extras?.getString("image_url", "")


        val options = RequestOptions()
            .placeholder(R.drawable.free_avatars_cons)
            .error(R.drawable.free_avatars_cons)

        Glide.with(applicationContext).load(url)
            .apply(options)
            .into(imageView);
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean { // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

}
