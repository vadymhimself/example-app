package com.example.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Artem on 01.11.2016.
 */

data class User(
        val login: String,
        @SerializedName("avatar_url") val avatarUrl: String
) : Serializable
