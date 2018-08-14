package com.example.model.analytics;

public interface Analytics {

    void setUserIdentifier(String userIdentifier);

    void logNotFatal(Throwable throwable);

    void logEvent(Event event);

}
