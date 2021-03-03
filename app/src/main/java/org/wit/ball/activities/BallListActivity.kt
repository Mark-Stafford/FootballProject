//Mark Stafford
//Mobile App Assignment - SSD Year 3

package org.wit.ball.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_ball_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.ball.R
import org.wit.ball.main.MainApp
import org.wit.ball.models.BallModel

class BallListActivity : AppCompatActivity(), BallListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ball_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = BallAdapter(app.balls.findAll(), this)
        loadBalls()

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<BallActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBallClick(ball: BallModel) {
        startActivityForResult(intentFor<BallActivity>().putExtra("ball_edit", ball), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_car_list.xml
        recyclerView.adapter?.notifyDataSetChanged()
        loadBalls()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadBalls() {
        showBalls(app.balls.findAll())
    }



    fun showBalls (balls: List<BallModel>) {
        recyclerView.adapter = BallAdapter(balls, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }







}