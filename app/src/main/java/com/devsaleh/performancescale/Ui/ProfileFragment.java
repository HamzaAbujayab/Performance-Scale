package com.devsaleh.performancescale.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.devsaleh.performancescale.Activity.login;
import com.devsaleh.performancescale.databinding.FragmentProfileBinding;
import com.devsaleh.performancescale.databinding.FragmentStudentBinding;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(token);
        editor.apply();
        startActivity(new Intent(getContext(), login.class));
        getActivity().finish();


        return binding.getRoot();

    }

}