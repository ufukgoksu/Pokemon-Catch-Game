package com.ufukgoksu.pokemongame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.ufukgoksu.pokemongame.databinding.ActivityGameBinding
import kotlin.random.Random
import android.content.Intent as ContentIntent

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    var say = 0
    var runnable = Runnable {  }
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var imageArray = ArrayList<ImageView>()
        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)


        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random
                val index = random.nextInt(9)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable,800)
            }

        }
        handler.post(runnable)
        object : CountDownTimer(10500,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Süre ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                val intent = Intent(this@GameActivity,FinishActivity::class.java)
                intent.putExtra("skor",binding.skorText.text.toString())
                startActivity(intent)
                finish()
            }

        }.start()

    }

    fun say(view: View) {
        if (binding.timeText.text != "Süre 0") {
            say = say + 1
            binding.skorText.text = "Skor ${say}"
        }
    }
}