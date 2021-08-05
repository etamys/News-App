package com.example.myapplication.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gotrypper_paging.model.New
import com.example.gotrypper_paging.model.News
import com.example.myapplication.R
import com.example.myapplication.data.repositories.NewsDatabase
import com.example.myapplication.ui.movies.ParallelNetworkCallsViewModel
import kotlinx.android.synthetic.main.fragment_home1.*
import kotlinx.android.synthetic.main.fragment_saved_fragmnent.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.simplifiedcoding.ui.MainActivity
import net.simplifiedcoding.ui.movies.MoviesAdapter


class SavedFragmnent : BaseFragment() {


    lateinit var  arrow:ImageView
    lateinit var  count:TextView
    private lateinit var viewModel: ParallelNetworkCallsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).viewModel
        val view = inflater.inflate(R.layout.fragment_saved_fragmnent, container, false)
        arrow = view.findViewById(R.id.arrow)
        count = view.findViewById(R.id.count)
        arrow.setOnClickListener(View.OnClickListener {
            fragmentManager!!.beginTransaction().hide(SavedFragmnent()).show(SaveItem())
            findNavController().navigate(R.id.action_Saved_to_saveItem)
        })
        setCount()
        return view
    }

    private fun setCount() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            count.text = it.size.toString()
        })
    }


}