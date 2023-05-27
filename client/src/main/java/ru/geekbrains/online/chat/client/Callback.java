package ru.geekbrains.online.chat.client;

@FunctionalInterface
public interface Callback {
    void callback(Object... args);
}
