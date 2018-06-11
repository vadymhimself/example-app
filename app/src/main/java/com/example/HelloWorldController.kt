package com.example

import android.view.View
import com.controllers.Controller
import com.controllers.async
import com.example.databinding.LayoutHelloWorldBinding
import kotlinx.coroutines.experimental.delay

class HelloWorldController : Controller<LayoutHelloWorldBinding>() {

    fun onClick(v: View) {
        show(WeatherController())
    }

    override fun getLayoutId(): Int = R.layout.layout_hello_world
}