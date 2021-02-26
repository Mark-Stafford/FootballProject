//Mark Stafford
//Mobile App Assignment - SSD Year 2
//Lecturer - Rob O Connor



package org.wit.ball.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.ball.models.BallJSONStore
import org.wit.ball.models.BallStore


class MainApp : Application(), AnkoLogger {


    lateinit var balls: BallStore

    override fun onCreate() {
        super.onCreate()
            balls = BallJSONStore(applicationContext)




        info("Football started")
    }
}