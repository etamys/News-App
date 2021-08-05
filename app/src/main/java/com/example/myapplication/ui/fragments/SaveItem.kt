package com.example.myapplication.ui.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.movies.ParallelNetworkCallsViewModel
import kotlinx.android.synthetic.main.activity_save_item.*
import kotlinx.android.synthetic.main.fragment_saved_fragmnent.*
import net.simplifiedcoding.ui.MainActivity

class SaveItem : BaseFragment() {

    lateinit var recycler:RecyclerView
    lateinit var adapter :SavedAdapter
    private lateinit var viewModel: ParallelNetworkCallsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_save_item, container, false)
        viewModel = (activity as MainActivity).viewModel
        recycler = view.findViewById(R.id.savedRecyclerView)
        renderList()
        return view
    }


    private fun renderList() {
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.setHasFixedSize(true)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter = SavedAdapter(it,requireContext(),viewModel)
            if (it.isNotEmpty()) {
                recycler.visibility = View.VISIBLE
                recycler.adapter = adapter
                noSavedData.visibility = View.INVISIBLE
            } else {
                noSavedData.visibility = View.VISIBLE
                recycler.visibility = View.INVISIBLE
            }
        })
    }
}