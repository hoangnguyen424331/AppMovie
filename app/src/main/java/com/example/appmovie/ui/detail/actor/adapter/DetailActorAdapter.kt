package com.example.appmovie.ui.detail.actor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.data.model.Actor
import com.example.appmovie.extensions.loadFromUrl
import com.example.appmovie.utils.Constant
import kotlinx.android.synthetic.main.item_actor.view.*

class DetailActorAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<DetailActorAdapter.DetailMovieViewHolder>() {

    private var actors = listOf<Actor>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return DetailMovieViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: DetailMovieViewHolder, position: Int) {
        holder.bindData(actors[position])
    }

    override fun getItemCount() = actors.size

    class DetailMovieViewHolder(itemView: View, private val onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(actor: Actor) {
            itemView.apply {
                imageActor.loadFromUrl(Constant.BASE_URL_IMAGE + actor.imageUrl)
                setOnClickListener {
                    id.let(onItemClick)
                }
            }
        }

    }
}
