package com.kazi.test.ui.employeesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazi.test.data.repository.UserRepository
import com.kazi.test.ui.employeesList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.kazi.test.utils.exception.ApiException
import com.kazi.test.utils.exception.NoInternetException
import com.vu.mobile.data.model.Image


class EmployeesListViewModel(val repository: UserRepository) : ViewModel() {

    private var isLoading: Boolean = false
    var currentPage = 1

    var listOfEmployees: MutableLiveData<ArrayList<Image>> = MutableLiveData()

    var view: IVIewEmployerList? = null


    fun getEmployeesList() {
        view?.showProgress()
        Coroutines.main {
            try {
                isLoading = true
                val employees = repository.getEmployeesAPI(currentPage)
                listOfEmployees.value = employees.images
                isLoading = false
                view?.hiddenProgress()

            } catch (e: ApiException) {
                view?.onFailure(e.message)
            } catch (e: NoInternetException) {
                view?.noInternetConnectionFound()
            }
        }


    }

    fun onItemClick(employee: Image) {
        view?.openEmpDetailsActivity(employee)

    }

    fun addOnScroll(totalItemCount: Int) {
        val size = listOfEmployees.value?.size
        size.let {
            if (it!! <= totalItemCount && isLoading == false) {
                isLoading = true
                currentPage++

                Coroutines.main {
                    try {
                        view?.hiddenProgress()
                        val employees = repository.getEmployeesAPI(currentPage)
                        val old = listOfEmployees.value
                        if (employees.images.size > 0) {
                            old?.let { it1 ->
                                employees.images.addAll(it1)
                                listOfEmployees.value = employees.images
                            }
                        } else {
                            currentPage--
                            view?.onFailure("${employees.page} page  no image found")
                        }
                        isLoading = false

                    } catch (e: ApiException) {
                        view?.onFailure(e.message)
                    } catch (e: NoInternetException) {
                        view?.noInternetConnectionFound()
                    }

                }

            }


        }
    }
}