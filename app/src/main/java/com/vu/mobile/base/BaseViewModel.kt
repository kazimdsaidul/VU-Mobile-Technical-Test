package com.vu.mobile.base

import androidx.lifecycle.ViewModel
import com.kazi.test.utils.exception.ApiException
import com.kazi.test.utils.exception.NoInternetException

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2020-05-10.
 */
open class BaseViewModel: ViewModel() {

    public fun handleException(ex: Exception, view: IView?) {
        if (ex is ApiException){
            view?.onFailure(ex.message)
        }else if (ex is NoInternetException){
            view?.onFailure(ex.message)
        }
    }
}