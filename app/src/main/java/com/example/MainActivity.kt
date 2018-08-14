package com.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.controllers.Controller
import com.controllers.FragmentTransitions
import com.controllers.RouterBuilder
import com.controllers.core.Router
import com.example.domain.App
import com.example.github.auth.AuthController
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    lateinit var mainRouter : Router<Controller<*>>

    val containerId = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(noFragmentsRestore(savedInstanceState))
        setContentView(R.layout.activity_main)

        mainRouter = RouterBuilder<Controller<*>>()
            .with(findViewById(containerId))
            .build()

        if (savedInstanceState == null) {
            onTransitionEvent(Show(AuthController(), 0, 0))
        }
    }

  override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
    super.onRestoreInstanceState(savedInstanceState)
    // if not empty, rerender upon recreation
    mainRouter.top?.let {
      mainRouter.make(FragmentTransitions.Render(containerId, this, it))
    }
  }

  /**
     * Improve bundle to prevent restoring of fragments.
     *
     * @param bundle bundle container
     * @return improved bundle with removed "fragments parcelable"
     */
    private fun noFragmentsRestore(bundle: Bundle?): Bundle? {
        bundle?.remove("android:support:fragments")
        return bundle
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
        is Show -> mainRouter.make(
            FragmentTransitions.Show(
                containerId,
                this,
                transition.enter,
                transition.exit,
                transition.ctrl
            )
        )
        is Replace -> mainRouter.make(
            FragmentTransitions.Replace(
                containerId,
                this,
                transition.enter,
                transition.exit,
                transition.ctrl
            )
        )
        is Back -> mainRouter.make(
            FragmentTransitions.Back(
                containerId,
                this,
                transition.enter,
                transition.exit
            )
        )
    }

    override fun onBackPressed() {
        if (mainRouter.size() > 1 && !mainRouter.top!!.onBackPressed()) {
            onTransitionEvent(Back())
        } else {
            super.onBackPressed()
        }
    }

}
