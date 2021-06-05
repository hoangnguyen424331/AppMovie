package com.example.appmovie.ui.hotmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.extensions.loadFromUrl
import com.example.appmovie.utils.Constant
import kotlinx.android.synthetic.main.item_hot_movie.view.*
import kotlinx.android.synthetic.main.item_load_more.view.*

class HotMovieAdapter(private val onItemClick: (HotMovie) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listHotMovie = mutableListOf<HotMovie?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LOADING) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false)
            LoadMoreItemViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_hot_movie, parent, false)
            HotMovieHolder(view, onItemClick)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HotMovieHolder) {
            listHotMovie[position]?.let {
                holder.popularItemRow(it)
            }
        } else if (holder is LoadMoreItemViewHolder) {
            holder.showLoadingView()
        }
    }

    override fun getItemCount() = listHotMovie.size

    override fun getItemViewType(position: Int): Int {
        return if (listHotMovie[position] == null) VIEW_TYPE_LOADING
        else VIEW_TYPE_ITEM
    }

    class HotMovieHolder(itemView: View, private val onItemClick: (HotMovie) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun popularItemRow(hotMovie: HotMovie) {
            itemView.apply {
                textViewRateMovie.text = (hotMovie.voteAverage).toString()
                imageHotMovie.loadFromUrl(Constant.BASE_URL_IMAGE + hotMovie.posterPath)
                rateHotMovie.rating = hotMovie.voteAverage?.div(2)?.toFloat() ?: -1f
                textViewHotMovie.text = hotMovie.title
                setOnClickListener {
                    onItemClick(hotMovie)
                }
            }
        }
    }

    class LoadMoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun showLoadingView() {
            itemView.progressBarLoadMore.isEnabled = true
        }
    }

    fun setData(newListHotMovie: MutableList<HotMovie?>) {
        listHotMovie.clear()
        listHotMovie = newListHotMovie
        notifyDataSetChanged()
    }

    fun addMovies(movies: MutableList<HotMovie?>) {
        listHotMovie.addAll(movies)
        notifyDataSetChanged()
    }

    fun addMoviesNull() {
        listHotMovie.add(null)
        notifyItemInserted(listHotMovie.size - 1)
    }

    fun removeMoviesLastItem() {
        listHotMovie.removeAt(listHotMovie.size - 1)
        notifyItemRemoved(listHotMovie.size)
    }

    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_ITEM = 1
    }
}
