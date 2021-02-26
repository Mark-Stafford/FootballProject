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

    override fun update(car: BallModel) {
        var foundBall: BallModel? = balls.find { p -> p.id == car.id }
        if (foundBall != null) {
            foundBall.title = car.title
            foundBall.description = car.description
            foundBall.enginesize = car.enginesize
            foundBall.image = car.image
            foundBall.lat = car.lat
            foundBall.lng = car.lng
            foundBall.zoom = car.zoom
            logAll()
        }
    }

    fun logAll(){
        balls.forEach {info("${it}")}
    }

    override fun delete(car: BallModel) {
        balls.remove(car)
    }

}