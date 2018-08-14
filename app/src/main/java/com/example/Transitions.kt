package com.example

import android.support.annotation.AnimRes
import com.controllers.Controller

sealed class Transition

data class Show(
  val ctrl: Controller<*>,
  @AnimRes val enter: Int = com.controllers.R.anim.slide_in_right,
  @AnimRes val exit: Int = com.controllers.R.anim.slide_out_left
) : Transition()

data class Replace(
  val ctrl: Controller<*>,
  @AnimRes val enter: Int = com.controllers.R.anim.fade_in_short,
  @AnimRes val exit: Int = com.controllers.R.anim.fade_out_short
) : Transition()

data class Back(
  @AnimRes val enter: Int = com.controllers.R.anim.slide_in_left,
  @AnimRes val exit: Int = com.controllers.R.anim.slide_out_right
) : Transition()
