package com.example.appmovie.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.data.model.SearchMovie
import com.example.appmovie.extensions.loadFromUrl
import com.example.appmovie.utils.Constant
import kotlinx.android.synthetic.main.item_load_more.view.*
import kotlinx.android.synthetic.main.item_search_movie.view.*

class SearchAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listSearchMovie = mutableListOf<SearchMovie?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SEARCH_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_movie, parent, false)
            SearchMovieViewHolder(view, onItemClick)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false)
            LoadItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchMovieViewHolder) {
            listSearchMovie[position]?.let {
                holder.bindData(it)
            }
        } else if (holder is LoadItemViewHolder) {
            holder.showLoadingView()
        }
    }

    override fun getItemCount() = listSearchMovie.size

    override fun getItemViewType(position: Int): Int {
        listSearchMovie[position]?.let {
            return VIEW_TYPE_SEARCH_ITEM
        }
        return VIEW_TYPE_SEARCH_LOADING
    }

    class SearchMovieViewHolder(itemView: View, val onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(searchMovie: SearchMovie) {
            itemView.apply {
                imageSearchMovie.loadFromUrl(Constant.BASE_URL_IMAGE + searchMovie.posterPath)
                textSearchMovieTitle.text = searchMovie.title
                setOnClickListener {
                    searchMovie.id?.let { idMovie ->
                        onItemClick(idMovie)
                    }
                }
            }
        }
    }

    class LoadItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun showLoadingView() {
            itemView.progressBarLoadMore.isEnabled = true
        }
    }

    fun setData(newList: MutableList<SearchMovie?>) {
        listSearchMovie.clear()
        listSearchMovie = newList
        notifyDataSetChanged()
    }

    fun addMovies(movies: MutableList<SearchMovie?>) {
        listSearchMovie.addAll(movies)
        notifyDataSetChanged()
    }

    fun addMoviesNull() {
        listSearchMovie.add(null)
        notifyItemInserted(listSearchMovie.size - 1)
    }

    fun removeMoviesLastItem() {
        listSearchMovie.removeAt(listSearchMovie.size - 1)
        notifyItemRemoved(listSearchMovie.size)
    }

    companion object {
        const val VIEW_TYPE_SEARCH_LOADING = 0
        const val VIEW_TYPE_SEARCH_ITEM = 1
    }
}