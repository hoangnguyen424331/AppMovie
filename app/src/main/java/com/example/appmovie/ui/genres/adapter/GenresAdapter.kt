package com.example.appmovie.ui.genres.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.data.model.Genres
import kotlinx.android.synthetic.main.item_genres.view.*

class GenresAdapter(
    private val context: Context,
    private var onClickListener: (Genres, Int) -> Unit
) : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private var genres = mutableListOf<Genres?>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        return GenresViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        genres[position]?.let {
            holder.bindData(context, it)
        }
    }

    override fun getItemCount() = genres.size

    class GenresViewHolder(itemView: View, val onClickListener: (Genres, Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(context: Context, genre: Genres) {
            itemView.let {
                it.textViewGenres.text = genre.name
                it.setOnClickListener {
                    if (!genre.selected) onClickListener(genre, adapterPosition)
                }
                if (genre.selected) {
                    it.lineaLayoutGenres.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.red)
                    )
                    it.textViewGenres.setTextColor(ContextCompat.getColor(context, R.color.white))
                } else {
                    it.lineaLayoutGenres.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.yellow)
                    )
                    it.textViewGenres.setTextColor(ContextCompat.getColor(context, R.color.black))
                }
            }
        }
    }

    fun setData(newListGenres: MutableList<Genres?>) {
        genres = newListGenres
        notifyDataSetChanged()
    }

    fun selectedGenres(position: Int) {
        genres[position]?.selected = true
        notifyItemChanged(position)
    }

    fun removeGenres(position: Int?) {
        position?.let {
            genres[it]?.selected = false
            notifyItemChanged(it)
        }
    }
}
