package com.example

import android.os.Bundle
import com.controllers.ControllerActivity
import com.example.github.auth.AuthController

class MainActivity : ControllerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setControllerContainer(R.id.container)

        if (savedInstanceState == null) {
            show(AuthController(), 0, 0)
        }
    }
}
