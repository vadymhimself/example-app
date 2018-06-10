package com.example

import android.view.View
import android.widget.Toast
import com.controllers.Controller
import com.controllers.async
import com.example.databinding.LayoutHelloWorldBinding
import kotlinx.coroutines.experimental.delay

class HelloWorldController : Controller<LayoutHelloWorldBinding>() {

    fun onClick(v: View) {
        show(ToastTestController().also {
            async {
                delay(1000)
                it.back()
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.layout_hello_world
}