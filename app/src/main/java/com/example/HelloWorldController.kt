package com.example

import com.controllers.Controller
import com.example.databinding.LayoutHelloWorldBinding

class HelloWorldController : Controller<LayoutHelloWorldBinding>() {

    init {
        async {
            await {

            }
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_hello_world
}