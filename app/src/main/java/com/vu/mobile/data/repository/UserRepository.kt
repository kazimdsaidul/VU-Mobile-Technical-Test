package com.kazi.test.data.repository


import com.kazi.test.data.network.APIService
import com.kazi.test.data.network.SafeApiRequest
import com.vu.mobile.data.model.ResposeData

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
class UserRepository(
    private val apiService: APIService

) : SafeApiRequest() {

    suspend fun getEmployeesAPI(page: Int): ResposeData {
        return apiRequest { apiService.getEmployees(page) }
    }


}