package com.example.group.Listener;

import java.util.List;

public interface ListListener<T> {
    void onResponse(List<T> list);
    void onFail(String msg);

}
