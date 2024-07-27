package com.example.group.Iface;

import com.example.group.Bean.AllGroupBean;
import com.example.group.Listener.ListListener;

public interface SearchGroupIface<T> {
    void getSearchList(String search_name, ListListener<AllGroupBean> listListener);
}
