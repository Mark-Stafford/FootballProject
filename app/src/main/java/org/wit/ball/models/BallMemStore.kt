//Mark Stafford
//Mobile App Assignment - SSD Year 3

package org.wit.ball.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BallMemStore : BallStore, AnkoLogger {

    val balls = ArrayList<BallModel>()

    override fun findAll(): List<BallModel> {
        return balls
    }

    override fun create(ball: BallModel) {
        ball.id = getId()
        balls.add(ball)
        logAll()
    }

    override fun update(ball: BallModel) {
        var foundBall: BallModel? = balls.find { p -> p.id == ball.id }
        if (foundBall != null) {
            foundBall.title = ball.title
            foundBall.description = ball.description
            foundBall.country = ball.country
            foundBall.image = ball.image
            foundBall.lat = ball.lat
            foundBall.lng = ball.lng
            foundBall.zoom = ball.zoom
            logAll()
        }
    }

    fun logAll(){
        balls.forEach {info("${it}")}
    }

    override fun delete(ball: BallModel) {
        balls.remove(ball)
    }

}