package ru.sberleasing.model.event;

public interface Bus {
    void register(Object o);

    void unregister(Object o);

    void post(Object o);

    boolean isRegistered(Object o);
}
