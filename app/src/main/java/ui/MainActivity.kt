package ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdatabaseexample.R

//the dependency that have used for room
//uses kapt which is kotlin annotation processing tool
//as for creating room we use different annotations so we have to implement this dependency

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}