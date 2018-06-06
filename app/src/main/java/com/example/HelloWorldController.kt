package com.example

import android.databinding.ViewDataBinding
import com.controllers.Controller
import com.example.databinding.LayoutHelloWorldBinding

class HelloWorldController : Controller<LayoutHelloWorldBinding>() {
    override fun getLayoutId(): Int = R.layout.layout_hello_world
}