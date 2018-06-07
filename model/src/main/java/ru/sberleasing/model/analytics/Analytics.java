package ru.sberleasing.model.analytics;

import android.app.Application;

public interface Analytics {

    void setUserIdentifier(String userIdentifier);

    void logNotFatal(Throwable throwable);

    void logEvent(Event event);

    void init(Application app);
}
