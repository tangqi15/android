package com.example.group.Fragment.Fragment_Group;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Activity.GroupDetailsActivity;
import com.example.group.R;
@SuppressLint("ValidFragment")
public class FragmentGroup_cite  extends Fragment {
    //表彰
    View view = null;

    @SuppressLint("ValidFragment")
    public FragmentGroup_cite(GroupDetailsActivity groupDetailsActivity) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_group_cite,container,false);
        return view;
    }
}
