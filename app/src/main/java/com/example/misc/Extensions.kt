package com.example.misc

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.controllers.Controller

fun <T : Controller<*>> T.handleError(t: Throwable) {
    Log.e(this::class.qualifiedName, "Error: ", t)
    activity.toast(t.message ?: t.javaClass.simpleName)
}

fun <T : Context?> T.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    if (this != null) {
        Toast.makeText(this, text, length).show()
    }
}