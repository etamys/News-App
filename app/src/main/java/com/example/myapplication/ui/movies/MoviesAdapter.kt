package net.simplifiedcoding.ui.movies

import android.app.Activity
import android.content.Context
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gotrypper_paging.model.New
import com.example.myapplication.R
import com.example.myapplication.data.repositories.NewsDatabase
import com.example.myapplication.ui.fragments.BaseFragment
import com.example.myapplication.ui.movies.ParallelNetworkCallsViewModel
import kotlinx.android.synthetic.main.recyclerview_movie.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.simplifiedcoding.ui.MainActivity


class MoviesAdapter(
    private val room: List<New>,
    private val context: Context,
    private val owner: LifecycleOwner,
    private val viewModel: ParallelNetworkCallsViewModel
) : RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    private val TAG = this::class.java.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_movie, parent, false)
        return ItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item: New = room.get(position)
        holder.title.text = item.news_title
        holder.content.text = item.news_content
        holder.comment.text = item.total_comment
        holder.view.text = item.total_views
        Glide.with(holder.itemView)
            .load(item.news_image)
            .into(holder.news_image)

        viewModel.data.observe(owner, Observer {
            CoroutineScope(Dispatchers.Default).launch {
                val isSaved = NewsDatabase(context).getNewsDao().getResult(item.news_id)
                if (isSaved) {
                    holder.heart_in.setImageResource(R.drawable.heart_out)
                } else {
                    holder.heart_in.setImageResource(R.drawable.heart_in)
                }

            }
        })


        holder.heart_in.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                val isSave = NewsDatabase(context).getNewsDao().getResult(item.news_id)
                    if(isSave){
                        holder.heart_in.setImageResource(R.drawable.heart_in)
                        viewModel.deleteArticle(item.news_id)
                    }else{
                        holder.heart_in.setImageResource(R.drawable.heart_out)
                        viewModel.SaveNews(item)
                    }

            }
        }

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val content: TextView = itemView.findViewById(R.id.content)
        val view: TextView = itemView.findViewById(R.id.viewsEye)
        val comment: TextView = itemView.findViewById(R.id.comment)
        val news_image: ImageView = itemView.findViewById(R.id.news_image)
        val heart_in: ImageView = itemView.findViewById(R.id.heart_in)

    }

    override fun getItemCount(): Int {
        return room.size
    }


}