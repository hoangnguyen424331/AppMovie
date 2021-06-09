package com.example.appmovie.ui.genres.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.data.model.Genres
import kotlinx.android.synthetic.main.item_selected.view.*

class GenresSelectedAdapter(
    private var onClickListener: (Int?, Int) -> Unit
) : RecyclerView.Adapter<GenresSelectedAdapter.GenresSelectedViewHolder>() {

    private var genresSelected = mutableListOf<Genres?>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenresSelectedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_selected, parent, false)
        return GenresSelectedViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(
        holder: GenresSelectedViewHolder,
        position: Int
    ) {
        holder.bindData(genresSelected)
    }

    override fun getItemCount() = genresSelected.size

    class GenresSelectedViewHolder(itemView: View, val onClickListener: (Int?, Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bindData(
            listGenre: MutableList<Genres?>
        ) {
            itemView.apply {
                textViewSelected.text = listGenre[adapterPosition]?.name
                setOnClickListener {
                    if (listGenre.size != 1 && adapterPosition in 0..listGenre.size)
                        onClickListener(
                            listGenre[adapterPosition]?.positionSelected,
                            adapterPosition
                        )
                }
            }
        }
    }

    fun setData(newListGenresSelected: MutableList<Genres?>) {
        genresSelected = newListGenresSelected
        notifyDataSetChanged()
    }

    fun removeData(position: Int) {
        genresSelected.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, genresSelected.size)
    }
}
