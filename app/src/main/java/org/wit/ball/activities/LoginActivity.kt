package org.wit.ball.activities
import org.wit.ball.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ball.*

import android.content.Intent
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.card_ball.*
import org.jetbrains.anko.*

class LoginActivity : AppCompatActivity() {

    private lateinit var btn : Button
    private lateinit var rb : RatingBar


    private var Name: EditText? = null
    private var Password: EditText? = null
    private var Info: TextView? = null
    private var Login: Button? = null
    //counter will be used later on as number of login attempts
    private var counter = 5
//user must enter name and password failed attempts will push out a message telling the user how many attempts remain
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn = findViewById<View>(R.id.btnclickme) as Button
        rb = findViewById<View>(R.id.ratingbar) as RatingBar



        Name = findViewById(R.id.etName) as EditText
        Password = findViewById(R.id.etPassword) as EditText
        Login = findViewById(R.id.btnLogin) as Button

    }





    //validation once five failed attempts have occurred you will no longer be able to log in to the app
    private fun validate(userName: String, userPassword: String) {
        if (userName == "Mark" && userPassword == "12345") {
            val intent = Intent(this@LoginActivity, BallListActivity::class.java)
            startActivity(intent)
        } else {
            counter--

            Info!!.text = "No of attempts remaining: $counter"

            if (counter == 0) {
                Login!!.isEnabled = false
            }
        }
    }

    fun click(view: View) {
        val ratingvalue = rb.rating
        Toast.makeText(this, "rating is:" + ratingvalue, Toast.LENGTH_LONG).show()
    }

}