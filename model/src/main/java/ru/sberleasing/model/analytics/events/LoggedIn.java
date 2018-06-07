package ru.sberleasing.model.analytics.events;

import ru.sberleasing.model.analytics.Event;

public class LoggedIn implements Event {
    public enum Type {
        PASSWORD, BIOMETRIC
    }

    public final Type type;

    public LoggedIn(Type type) {
        this.type = type;
    }
}
