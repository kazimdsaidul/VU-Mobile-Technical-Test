package com.kazi.test.ui.employeesList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudwell.paywell.consumer.utils.viewUtil.hide
import com.cloudwell.paywell.consumer.utils.viewUtil.show
import com.kazi.test.ui.employeesList.adapter.ImageItem
import com.kazi.test.ui.employeesList.employeesViewModelFactory.EmployeesViewModelFactory
import com.kazi.test.ui.employeesList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.vu.mobile.R
import com.vu.mobile.data.model.Image
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_employees_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class EmployeesListFragment : Fragment(), IVIewEmployerList, KodeinAware {

    private var directionDown: Boolean = false
    override val kodein by kodein()

    private val factory: EmployeesViewModelFactory by instance()
    private lateinit var viewModel: EmployeesListViewModel

    val gridLayoutManager = GridLayoutManager(context, 2);


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
            it.toImageItem().let { it1 -> initRecyclerView(it1) }
        })
    }


    private fun initRecyclerView(quoteItem: List<ImageItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recyclerview.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = mAdapter
        }

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("-----", "end")
                    val totalItemCount = recyclerView.layoutManager!!.itemCount
                    viewModel.addOnScroll(totalItemCount);
                }
            }
        })



    }





    override fun noInternetConnectionFound() {
    }

    override fun showProgress() {
    }

    override fun hiddenProgress() {
    }

    override fun onFailure(message: String?) {

        Toast.makeText(activity?.applicationContext , message, Toast.LENGTH_LONG).show()
    }

    override fun openEmpDetailsActivity(employee: Image) {

    }


    private fun List<Image>.toImageItem() : List<ImageItem>{
        return this.map {
            ImageItem(it, object : ImageItem.OnLikeClickedListener {
                override fun onLikeClicked(item: Image) {
                    
                }

            })
        }
    }





}