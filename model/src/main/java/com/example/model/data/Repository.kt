package com.example.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Artem Sisetskyi on 6/22/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
data class Repository(
        var name: String,
        var description: String,
        var language: String?,
        @SerializedName("stargazers_count") var stargazersCount :Int,
        var owner: User,
        val login: String,
        @SerializedName("avatar_url") val avatarUrl: String
) : Serializable