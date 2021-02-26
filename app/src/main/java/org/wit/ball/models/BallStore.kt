//Mark Stafford
//Mobile App Assignment - SSD Year 3


package org.wit.ball.models

interface BallStore {
    fun findAll(): List<BallModel>
    fun create(ball: BallModel)
    fun update(ball: BallModel)
    fun delete(ball: BallModel)

}

