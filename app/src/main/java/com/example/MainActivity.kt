package com.example

import android.os.Bundle
import com.controllers.ControllerActivity

class MainActivity : ControllerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setControllerContainer(R.id.container)

        if (savedInstanceState == null) {
            show(HelloWorldController(), 0, 0)
        }
    }
}
