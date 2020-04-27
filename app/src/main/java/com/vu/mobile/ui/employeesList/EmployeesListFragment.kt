package com.kazi.test.ui.employeesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloudwell.paywell.consumer.utils.viewUtil.hide
import com.cloudwell.paywell.consumer.utils.viewUtil.show
import com.kazi.test.ui.employeesList.employeesViewModelFactory.EmployeesViewModelFactory
import com.kazi.test.ui.employeesList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.vu.mobile.R
import com.vu.mobile.data.model.Image
import kotlinx.android.synthetic.main.fragment_employees_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class EmployeesListFragment : Fragment(), IVIewEmployerList, KodeinAware {

    override val kodein by kodein()

    private val factory: EmployeesViewModelFactory by instance()
    private lateinit var viewModel: EmployeesListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this, factory).get(EmployeesListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_employees_list, container, false)


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.view = this
        viewModel.getEmployeesList()
        bindUI()
    }


    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.listOfEmployees.observe(this, Observer {
            progress_bar.hide()
           // initRecyclerView(it.toQuoteItem())
        })
    }

//    private fun initRecyclerView(quoteItem: List<Datum>) {
//
//        val mAdapter = GroupAdapter<ViewHolder>().apply {
//            addAll(quoteItem)
//        }
//
//        recyclerview.apply {
//            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
//            adapter = mAdapter
//        }
//
//
//
//        mAdapter.setOnItemClickListener(object : OnItemClickListener {
//            override fun onItemClick(item: Item<*>, view: View) {
////                val employeeemployee = viewModel.listOfEmployees.value?.get(item.getPosition(item))
////                employeeemployee?.let { viewModel.onItemClick(it) }
//
//            }
//        })
//    }


//    private fun List<Datum>.toQuoteItem(): List<Datum> {
//        return this.map {
//            Datum(it)
//        }
//    }


    override fun noInternetConnectionFound() {
    }

    override fun showProgress() {
    }

    override fun hiddenProgress() {
    }

    override fun onFailure(message: String?) {
    }

    override fun openEmpDetailsActivity(employee: Image) {


    }




}