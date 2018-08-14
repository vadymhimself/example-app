package com.example

import android.os.Bundle
import com.controllers.ControllerActivity
import com.example.domain.App
import com.example.github.auth.AuthController
import org.greenrobot.eventbus.Subscribe

class MainActivity : ControllerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setControllerContainer(R.id.container)

        if (savedInstanceState == null) {
            show(AuthController(), 0, 0)
        }
    }

    override fun onStart() {
        super.onStart()
        App.require().bus().register(this)
    }

    override fun onStop() {
        super.onStop()
        App.require().bus().unregister(this)
    }

    @Subscribe
    fun onTransitionEvent(transition: Transition) = when (transition) {
        is Show -> show(transition.ctrl)
        is Replace -> replace(transition.ctrl)
    }
}
