package ru.sberleasing.model.persistance;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

public final class Converters {

    @TypeConverter
    public static List<String> stringToList(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, new TypeToken<List<String>>(){}.getType());
    }

    @TypeConverter
    public static String listToString(List<String> stringList) {
        Gson gson = new Gson();
        return gson.toJson(stringList);
    }


    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
