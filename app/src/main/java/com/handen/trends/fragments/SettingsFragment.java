package com.handen.trends.fragments;


import android.opengl.ETC1;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.handen.trends.R;
public class SettingsFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText nicknameEditText;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        emailEditText = (EditText) view.findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) view.findViewById(R.id.password_edit_text);
        nicknameEditText = (EditText) view.findViewById(R.id.nickname_edit_text);
        // Inflate the layout for this fragment

        return view;
    }

}
