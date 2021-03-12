//Mark Stafford
//Mobile App Assignment - SSD Year 3

package org.wit.ball.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_ball.view.*
import kotlinx.android.synthetic.main.card_ball.view.*
import kotlinx.android.synthetic.main.card_ball.view.ballTitle
import kotlinx.android.synthetic.main.card_ball.view.description

import kotlinx.android.synthetic.main.card_ball.view.enginesize

import org.wit.ball.R
import org.wit.ball.helpers.readImageFromPath
import org.wit.ball.models.BallModel

interface BallListener {
    fun onBallClick(ball: BallModel)
}

class BallAdapter constructor(private var balls: List<BallModel>,
                                   private val listener: BallListener) : RecyclerView.Adapter<BallAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_ball, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val ball = balls[holder.adapterPosition]
        holder.bind(ball, listener)
    }

    override fun getItemCount(): Int = balls.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(ball: BallModel,  listener : BallListener) {
            itemView.ballTitle.text = ball.title
            itemView.description.text = ball.description
            itemView.enginesize.text = ball.enginesize
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, ball.image))
            itemView.setOnClickListener { listener.onBallClick(ball) }
        }
    }
}