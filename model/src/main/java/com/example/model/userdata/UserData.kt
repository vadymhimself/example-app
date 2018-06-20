package com.example.model.userdata

import com.example.model.data.AccessToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserData @Inject constructor(

) {
    var accessToken: AccessToken? = null
}