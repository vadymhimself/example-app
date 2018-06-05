package com.example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.controllers.ControllerActivity

class MainActivity : ControllerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
