package com.example

import com.controllers.Controller

sealed class Transition

data class Show(val ctrl : Controller<*>) : Transition()
data class Replace(val ctrl : Controller<*>) : Transition()
