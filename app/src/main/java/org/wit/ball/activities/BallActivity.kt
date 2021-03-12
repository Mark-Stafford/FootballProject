//Mark Stafford
//Mobile App Assignment - SSD Year 2
//Lecturer - Rob O Connor



package org.wit.ball.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_ball.*
import org.wit.ball.models.BallModel
import org.wit.ball.R
import org.wit.ball.helpers.readImage
import org.wit.ball.helpers.readImageFromPath
import org.wit.ball.helpers.showImagePicker
import org.wit.ball.main.MainApp
import org.wit.ball.models.Location
import android.content.Intent
import android.media.Image
import kotlinx.android.synthetic.main.activity_ball.ballTitle
import kotlinx.android.synthetic.main.activity_ball.description

import kotlinx.android.synthetic.main.activity_ball.enginesize

import org.jetbrains.anko.*

class BallActivity : AppCompatActivity(), AnkoLogger {



    var ball = BallModel()
    lateinit var app : MainApp
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    var edit = false;


    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ball)
        app = application as MainApp
        var edit = false

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)



// Edit car details such as model and description // takes what the user changing these fields too and saves.
        if (intent.hasExtra("ball_edit")) {
            edit = true
            ball = intent.extras.getParcelable<BallModel>("ball_edit")
            ballTitle.setText(ball.title)
            description.setText(ball.description)
            enginesize.setText(ball.enginesize)
            ballImage.setImageBitmap(readImageFromPath(this, ball.image))
            if (ball.image != null){
                chooseImage.setText(R.string.change_ball_image)
            }
            btnAdd.setText(R.string.save_ball)



        }













// Adding car to the recycler view also if all fields are not entered by the user the car will not be added to the list.
        btnAdd.setOnClickListener() {
            ball.title = ballTitle.text.toString()
            ball.description = description.text.toString()
            ball.enginesize = enginesize.text.toString()


            if (ball.title.isEmpty() or ball.description.isEmpty() or ball.enginesize.isEmpty())
            {
                toast(R.string.enter_ball_title)


                app.balls.delete(ball)
            } else {
                if (edit) {
                    app.balls.update(ball.copy())
                } else {
                    app.balls.create(ball.copy())
                }


                if (ball.title.isNotEmpty() and ball.description.isNotEmpty() and ball.enginesize.isNotEmpty()

                ) {
                    app.balls.update(ball.copy())


                }
            }











            info("add Button Pressed: $ballTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()


        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }


        btnFixtures.setOnClickListener {
            val intent = Intent(this, Fixtures::class.java)
            startActivity(intent)


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ball, menu)
        return super.onCreateOptionsMenu(menu)
    }






    // delete selected car method.
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.balls.delete(ball)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }







    //select car image and location.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    ball.image = data.getData().toString()
                    ballImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_ball_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras.getParcelable<Location>("location")
                    ball.lat = location.lat
                    ball.lng = location.lng
                    ball.zoom = location.zoom
                }
            }
        }


    }




}