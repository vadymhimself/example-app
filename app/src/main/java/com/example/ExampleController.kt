package com.example

import android.databinding.ViewDataBinding
import com.controllers.Controller
import com.example.domain.App
import org.greenrobot.eventbus.EventBusException

abstract class ExampleController<B : ViewDataBinding> : Controller<B>() {

  override fun onAttachedToRouter() {
    super.onAttachedToRouter()
    try {
      App.require()
          .bus()
          .register(this)
    } catch (e: Exception) {
      if (e is EventBusException) {
        e.printStackTrace()
      } else {
        throw e
      }
    }
  }

  override fun onDetachedFromRouter() {
    super.onDetachedFromRouter()
    try {
      App.require()
          .bus()
          .unregister(this)
    } catch (e: Exception) {
      if (e is EventBusException) {
        e.printStackTrace()
      } else {
        throw e
      }
    }
  }

}