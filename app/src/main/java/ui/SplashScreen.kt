package ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.roomdatabaseexample.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))

            finish()
        }, SPLASH_TIME_OUT)

        img.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000);
        textView.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000);
        textView2.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000);
        textnewlogo.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000);
        lottie.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000);
    }
}