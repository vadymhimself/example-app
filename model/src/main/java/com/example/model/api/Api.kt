package com.example.model.api

import com.example.model.data.AccessToken
import com.example.model.data.User
import com.example.model.persistance.CityDao
import com.example.model.userdata.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Api @Inject internal constructor(
        private val networkInterface: NetworkInterface,
        private val cityDao: CityDao,
        private val userData: UserData
) {

    private suspend fun getAccessToken(url: String, clientId: String,
                                       clientSecret: String, code: String): AccessToken {
        return networkInterface.getAccessToken(url, clientId, clientSecret, code)
    }

    suspend fun getCurrentUser(): User {
        return networkInterface.getCurrentUser()
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

    suspend fun getFollowers(login: String) : List<User> {
        return networkInterface.getFollowers(login)
    }

    suspend fun getFollowing(login: String) : List<User> {
        return networkInterface.getFollowing(login)
    }

}
