package com.example.model.data;

import android.content.SharedPreferences;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vadym Ovcharenko
 * 06.11.2016.
 */

public class AccessToken {
    public static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    public static final String CLIENT_ID = "29d1e2e1ac8187d42a66";
    public static final String CLIENT_SECRET = "3ee945256d99110f00a771373584e1422435f292";
    public static final String REDIRECT_URI = "http://127.0.0.1/";

    private static final String KEY_ACCESS_TOKEN = "_access_token";
    private static final String KEY_TOKEN_TYPE = "_token_type";

    @SerializedName("access_token") public String accessToken;
    @SerializedName("token_type") public String tokenType;

    public AccessToken(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public static AccessToken restoreFromPreferences(SharedPreferences pref) {
        String accessToken = pref.getString(KEY_ACCESS_TOKEN, null);
        String tokenType = pref.getString(KEY_TOKEN_TYPE, null);
        if (accessToken != null && tokenType != null) {
            return new AccessToken(accessToken, tokenType);
        }
        return null;
    }

    public void save(SharedPreferences preferences) {
        preferences.edit()
                .putString(KEY_ACCESS_TOKEN, accessToken)
                .putString(KEY_TOKEN_TYPE, tokenType)
                .apply();
    }
}
