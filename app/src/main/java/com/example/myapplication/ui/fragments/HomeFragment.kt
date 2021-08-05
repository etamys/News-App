package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.*
import com.example.gotrypper_paging.model.News
import com.example.myapplication.R
import com.example.myapplication.ui.movies.ParallelNetworkCallsViewModel
import com.example.myapplication.ui.movies.nestedAdapter
import com.example.myapplication.util.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home1.*
import net.simplifiedcoding.ui.MainActivity
import net.simplifiedcoding.ui.movies.MoviesAdapter
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : BaseFragment() {

    private lateinit var viewModel: ParallelNetworkCallsViewModel
    lateinit var recycler: RecyclerView
    lateinit var adpter: nestedAdapter
     var list  = listOf<Int>(1,2,3)
//    var verticalAdapter = VerticalRecycler()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).viewModel
        val view = inflater.inflate(R.layout.fragment_home1, container, false)

        recycler = view.findViewById(R.id.recycler_view_movies)

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager
        recycler.setHasFixedSize(true)
        setupObserver()
        return view
    }



    private fun setupObserver() {
        viewModel.news.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar?.visibility = View.GONE
                    button.visibility = View.GONE
                     adpter = nestedAdapter(list,it.data!!,viewLifecycleOwner,viewModel)
                        recycler.adapter = adpter
                        recycler_view_movies.visibility = View.VISIBLE
                    adpter.notifyDataSetChanged()

                }
                Status.LOADING -> {
                    progressBar?.visibility = View.VISIBLE
                    button.visibility = View.INVISIBLE
                    recycler_view_movies.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar?.visibility = View.GONE
                    button.visibility = View.VISIBLE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }




}