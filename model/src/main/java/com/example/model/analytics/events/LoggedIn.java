package com.example.model.analytics.events;

import com.example.model.analytics.Event;

public class LoggedIn implements Event {
    public enum Type {
        PASSWORD, BIOMETRIC
    }

    public final Type type;

    public LoggedIn(Type type) {
        this.type = type;
    }
}
