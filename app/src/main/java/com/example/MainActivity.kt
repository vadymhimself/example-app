package com.example

import android.os.Bundle
import com.controllers.ControllerActivity
import com.controllers.FragmentTransitions
import com.example.domain.App
import com.example.github.auth.AuthController
import org.greenrobot.eventbus.Subscribe

class MainActivity : ControllerActivity() {

    val container = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setControllerContainer(container)

        if (savedInstanceState == null) {
            onTransitionEvent(Show(AuthController()))
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
    fun onTransitionEvent(transition: Transition) : Boolean = when (transition) {
        is Show -> make(
            FragmentTransitions.Show(
                container,
                this,
                transition.enter,
                transition.exit,
                transition.ctrl
            )
        )
        is Replace -> make(
            FragmentTransitions.Replace(
                container,
                this,
                transition.enter,
                transition.exit,
                transition.ctrl
            )
        )
        is Back -> make(
            FragmentTransitions.Back(
                container,
                this,
                transition.enter,
                transition.exit
            )
        )
    }

    override fun onBackPressed() {
        if (stack.size() > 1 && !stack.peek()!!.onBackPressed()) {
            onTransitionEvent(Back())
        } else {
            super.onBackPressed()
        }
    }

}
