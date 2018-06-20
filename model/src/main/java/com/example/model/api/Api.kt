package com.example.model.api

import android.util.Log
import com.example.model.data.AccessToken
import com.example.model.data.User
import com.example.model.persistance.CityDao
import com.example.model.userdata.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Api @Inject internal constructor(
        private val apiInterface: ApiInterface,
        private val cityDao: CityDao,
        private val userData: UserData
) {

    private suspend fun getAccessToken(url: String, clientId: String,
                                       clientSecret: String, code: String): AccessToken {
        return apiInterface.getAccessToken(url, clientId, clientSecret, code)
    }

    suspend fun getCurrentUser(): User {
        return apiInterface.getCurrentUser()
    }

    suspend fun login(code: String): User {
        val token = getAccessToken(
                AccessToken.ACCESS_TOKEN_URL,
                AccessToken.CLIENT_ID,
                AccessToken.CLIENT_SECRET,
                code)

        userData.accessToken = token

        return getCurrentUser()
    }

}
