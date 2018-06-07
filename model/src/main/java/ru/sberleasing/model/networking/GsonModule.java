package ru.sberleasing.model.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Map;

@Module
public class GsonModule {

    @Provides
    @Singleton
    Gson provideGson(Map<Class<?>, TypeAdapter> typeAdapters) {
        GsonBuilder builder = new GsonBuilder();
        for (Map.Entry<Class<?>, TypeAdapter> entry : typeAdapters.entrySet()) {
            builder.registerTypeAdapter(entry.getKey(), entry.getValue());
        }
        return builder.create();
    }

    @Provides
    @IntoMap
    @ClassKey(Boolean.class)
    TypeAdapter provideBooleanTypeAdapter() {
        return new TypeAdapter<Boolean>() {
            @Override
            public void write(JsonWriter out, Boolean value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    out.value(value);
                }
            }

            @Override
            public Boolean read(JsonReader in) throws IOException {
                JsonToken peek = in.peek();
                switch (peek) {
                    case BOOLEAN:
                        return in.nextBoolean();
                    case NULL:
                        in.nextNull();
                        return null;
                    case NUMBER:
                        return in.nextInt() != 0;
                    case STRING:
                        return Boolean.parseBoolean(in.nextString());
                    default:
                        throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
                }
            }
        };
    }

}
