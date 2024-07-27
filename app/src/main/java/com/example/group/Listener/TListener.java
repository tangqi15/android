package com.example.group.Listener;


public interface TListener<T> {
    void onResponse(T t);
    void onFail(String msg);
}
