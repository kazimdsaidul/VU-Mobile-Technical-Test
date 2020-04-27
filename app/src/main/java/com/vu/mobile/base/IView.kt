package com.vu.mobile.base

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
interface IView {
    fun noInternetConnectionFound()
    fun showProgress()
    fun hiddenProgress()
    fun onFailure(message: String?)
}