package com.example.github.auth

import android.databinding.ObservableBoolean
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import com.controllers.Controller
import com.controllers.async
import com.example.R
import com.example.databinding.LayoutAuthBinding
import com.example.domain.App
import com.example.github.profile.ProfileController
import com.example.model.data.AccessToken

import java.util.UUID

/**
 * Created by Vadym Ovcharenko
 * 05.11.2016.
 */

class AuthController : Controller<LayoutAuthBinding>() {

    private val state = UUID.randomUUID().toString()
    val showProgress = ObservableBoolean()

    val authUrl = "https://github.com/login/oauth/authorize?" +
            "client_id=" + AccessToken.CLIENT_ID + "&" +
            "redirect_uri=" + AccessToken.REDIRECT_URI + "&" +
            "scope=" + "user%20user:follow%20"

    val webClient: WebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            if (url.startsWith(AccessToken.REDIRECT_URI)) {
                showProgress.set(true)
                val code = Uri.parse(url).getQueryParameter("code")
                requestAuth(code, state)
            }
        }
    }

    private fun requestAuth(code: String, state: String) = async {
        val user = App.require().api().login(code)
        replace(ProfileController(user))
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_auth
    }

    override fun getTitle(): String {
        return "Log in to GitHub"
    }
}
