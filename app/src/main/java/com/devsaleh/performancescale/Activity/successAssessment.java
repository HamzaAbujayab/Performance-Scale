package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.devsaleh.performancescale.Activity.home;
import com.devsaleh.performancescale.databinding.ActivitySuccessAssessmentBinding;

public class successAssessment extends AppCompatActivity {
    ActivitySuccessAssessmentBinding binding;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token, type_school;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuccessAssessmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        type_school = sp.getString(login.TYPE_SCHOOL, "");
        editor.apply();

//        if (type_school.equals("public"))
//            binding.successAssessmentBtnTakeAnother.setEnabled(false);
//        else
//            binding.successAssessmentBtnTakeAnother.setEnabled(true);


        binding.successAssessmentBtnTakeAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });


    }
}