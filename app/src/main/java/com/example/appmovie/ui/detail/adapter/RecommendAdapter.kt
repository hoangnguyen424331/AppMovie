package com.example.appmovie.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.extensions.loadFromUrl
import com.example.appmovie.utils.Constant
import kotlinx.android.synthetic.main.item_recommend.view.*

class RecommendAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {

    private var movies = listOf<HotMovie>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recommend, parent, false)
        return RecommendViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bindData(movies[position])
    }

    override fun getItemCount() = movies.size

    class RecommendViewHolder(itemView: View, private val onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(hotMovie: HotMovie) {
            itemView.apply {
                textViewRecommend.text = hotMovie.title
                imageRecommend.loadFromUrl(Constant.BASE_URL_IMAGE + hotMovie.posterPath)
                setOnClickListener {
                    id.let(onItemClick)
                }
            }
        }
    }

    fun setData(newList: List<HotMovie>) {
        movies = newList
        notifyDataSetChanged()
    }
}
