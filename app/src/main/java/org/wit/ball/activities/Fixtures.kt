package org.wit.ball.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_ball.*
import kotlinx.android.synthetic.main.activity_fixtures.*
import org.jetbrains.anko.AnkoLogger
import org.wit.ball.R

class Fixtures : AppCompatActivity(), AnkoLogger {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixtures)

        btnReturn.setOnClickListener {
            val intent = Intent(this, BallListActivity::class.java)
            startActivity(intent)



        }

    }





}