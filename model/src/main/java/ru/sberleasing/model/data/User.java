package ru.sberleasing.model.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @SerializedName("email")
    @Expose
    @NonNull
    public String email;

    @SerializedName("lastname")
    @Expose
    public String lastname;

    @SerializedName("firstname")
    @Expose
    public String firstname;

}
