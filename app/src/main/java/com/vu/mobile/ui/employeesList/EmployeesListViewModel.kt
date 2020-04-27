package com.kazi.test.ui.employeesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazi.test.data.repository.UserRepository
import com.kazi.test.ui.employeesList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.kazi.test.utils.exception.ApiException
import com.kazi.test.utils.exception.NoInternetException
import com.vu.mobile.data.model.Image

class EmployeesListViewModel(val repository: UserRepository) : ViewModel() {


    var listOfEmployees: MutableLiveData<List<Image>> = MutableLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    var view: IVIewEmployerList? = null



    fun getEmployeesList() {
        view?.showProgress()
        Coroutines.main {
            try {

//                val employeesLocal = repository.getEmployeesLocal()
//                if (employeesLocal.size != 0) {
//                    //listOfEmployees.value = employeesLocal
//                } else {
                    val employees = repository.getEmployeesAPI()
//                    repository.saveAllEmployee(employees)
                    listOfEmployees.value = employees.images
//                }

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
}