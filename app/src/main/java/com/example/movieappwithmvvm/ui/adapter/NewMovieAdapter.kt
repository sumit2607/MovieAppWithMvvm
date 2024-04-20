package com.example.movieappwithmvvm.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappwithmvvm.R
import com.example.movieappwithmvvm.databinding.ItemLayoutBinding
import com.example.movieappwithmvvm.extra.Constants.MOVIE_URL
import com.example.movieappwithmvvm.local.response.OnCardClick
import com.example.movieappwithmvvm.local.response.ResultModel
import java.util.ArrayList
import java.util.Random



//for first view pager
class NewMovieAdapter(
    val resultModelList: List<ResultModel>,
    val onCardClicked: OnCardClick
) : RecyclerView.Adapter<NewMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMoviesViewHolder {
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_layout, parent, false
            )
        return NewMoviesViewHolder(itemLayoutBinding, onCardClicked)
    }

    override fun onBindViewHolder(holder: NewMoviesViewHolder, position: Int) {
        val resultModel = resultModelList[position]
        holder.setData(resultModel)
    }

    override fun getItemCount(): Int {
        return resultModelList.size
    }


}

class NewMoviesViewHolder(
    val itemLayoutBinding: ItemLayoutBinding,
    val onCardClicked: OnCardClick
) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun setData(resultModel: ResultModel) {
        itemLayoutBinding.apply {

            Glide.with(ivMovieCard).load(MOVIE_URL + resultModel.posterPath).placeholder(
                ColorDrawable(getRandomColor())
            )
                .into(ivMovieCard)

            ivMovieCard.setOnClickListener {
                onCardClicked.onCardClicked(resultModel)
            }
        }
    }

    fun getRandomColor(): Int {
        val colours: MutableList<Int> = ArrayList()
        colours.add(Color.parseColor("#FED8A9"))
        colours.add(Color.parseColor("#C599D6"))
        colours.add(Color.parseColor("#78D6C6"))
        colours.add(Color.parseColor("#A6B8FF"))
        colours.add(Color.parseColor("#E5B9D2"))
        colours.add(Color.parseColor("#FFEABF"))
        colours.add(Color.parseColor("#CCBBE5"))
        colours.add(Color.parseColor("#BCE4FE"))
        colours.add(Color.parseColor("#DAF5A8"))
        colours.add(Color.parseColor("#FFA4B5"))
        colours.add(Color.parseColor("#92CED8"))
        colours.add(Color.parseColor("#DBCBA7"))
        val rand = Random()
        return colours[rand.nextInt(colours.size)]
    }
}
