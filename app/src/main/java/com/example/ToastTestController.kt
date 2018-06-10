package com.example

import android.util.Log
import android.widget.Toast
import com.controllers.Controller
import com.controllers.async
import com.example.databinding.LayoutToastTestBinding
import kotlinx.coroutines.experimental.delay

class ToastTestController : Controller<LayoutToastTestBinding>() {

    init {
        async {
            delay(2000)
            Log.e("XXX", "toast shown")
            Toast.makeText(activity, "Test!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_toast_test
}