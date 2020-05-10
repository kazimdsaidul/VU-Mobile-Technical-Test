package com.vu.mobile.base

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2020-05-10.
 */
 open class BaseFragment():Fragment(),  IView{
    override fun noInternetConnectionFound() {
        Log.e("", "noInternetConnectionFound")
    }

    override fun showProgress() {
        Log.e("", "showProgress")

    }

    override fun hiddenProgress() {
        Log.e("", "hiddenProgress")
    }

    override fun onFailure(message: String?) {
        Toast.makeText(activity?.applicationContext , message, Toast.LENGTH_LONG).show()
    }



}