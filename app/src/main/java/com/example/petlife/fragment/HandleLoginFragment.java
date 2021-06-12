package com.example.petlife.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.petlife.activity.LoginActivity;
import com.example.petlife.activity.MainActivity;
import com.example.petlife.R;


public class HandleLoginFragment extends Fragment {

   Button btnFazerLogin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handle_login,container,false);
        btnFazerLogin = view.findViewById(R.id.btnFazerLogin);

        btnFazerLogin.setOnClickListener(v -> {
                Intent i = new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
        });
        return view;
    }


}