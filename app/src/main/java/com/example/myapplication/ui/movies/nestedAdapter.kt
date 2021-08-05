package com.example.myapplication.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gotrypper_paging.model.News
import com.example.myapplication.R
import kotlinx.android.synthetic.main.nestedrecycler.view.*
import net.simplifiedcoding.ui.movies.MoviesAdapter

class nestedAdapter(private val data:List<Int>,
    private val news:News,private val owner:LifecycleOwner,
                    private val viewModel: ParallelNetworkCallsViewModel) : RecyclerView.Adapter<nestedAdapter.ViewHolder>(){


    lateinit var adapterN:MoviesAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.nestedrecycler,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = data[position]
        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            adapterN = MoviesAdapter(news.news,context,owner,viewModel)
            holder.recyclerView.adapter = adapterN
        }
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val recyclerView : RecyclerView = itemView.findViewById(R.id.nestedRecycler)
    }


}