package com.example

import android.widget.Toast
import com.controllers.Controller
import com.controllers.coroutines.async
import com.example.databinding.LayoutHelloWorldBinding
import kotlinx.coroutines.experimental.delay

class HelloWorldController : Controller<LayoutHelloWorldBinding>() {

    init {
        async {
            await { delay(2000) }
            Toast.makeText(activity, "Test!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_hello_world
}