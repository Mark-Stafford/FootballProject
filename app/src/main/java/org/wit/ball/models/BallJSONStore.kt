//Mark Stafford
//Mobile App Assignment - SSD Year 3

package org.wit.ball.models



    import android.content.Context
    import com.google.gson.Gson
    import com.google.gson.GsonBuilder
    import com.google.gson.reflect.TypeToken
    import org.jetbrains.anko.AnkoLogger
    import org.wit.ball.helpers.*
    import java.util.*

    val JSON_FILE = "balls.json"
    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    val listType = object : TypeToken<java.util.ArrayList<BallModel>>() {}.type

    fun generateRandomId(): Long {
        return Random().nextLong()
    }

    class BallJSONStore : BallStore, AnkoLogger {

        val context: Context
        var balls = mutableListOf<BallModel>()

        constructor (context: Context) {
            this.context = context
            if (exists(context, JSON_FILE)) {
                deserialize()
            }
        }

        override fun findAll(): MutableList<BallModel> {
            return balls
        }

        override fun create(ball: BallModel) {
            ball.id = generateRandomId()
            balls.add(ball)
            serialize()
        }



        override fun update(ball: BallModel) {
            val ballsList = findAll() as ArrayList<BallModel>
            var foundBall: BallModel? = ballsList.find { p -> p.id == ball.id }
            if (foundBall != null) {
                foundBall.title = ball.title
                foundBall.description = ball.description
                foundBall.country = ball.country
                foundBall.image = ball.image
                foundBall.lat = ball.lat
                foundBall.lng = ball.lng
                foundBall.zoom = ball.zoom
            }
            serialize()
        }

        private fun serialize() {
            val jsonString = gsonBuilder.toJson(balls, listType)
            write(context, JSON_FILE, jsonString)
        }

        private fun deserialize() {
            val jsonString = read(context, JSON_FILE)
            balls = Gson().fromJson(jsonString, listType)
        }

        override fun delete(ball: BallModel) {
            balls.remove(ball)
            serialize()
        }
    }
