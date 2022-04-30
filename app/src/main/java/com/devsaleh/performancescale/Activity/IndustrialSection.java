package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityIndustrialSectionBinding;

public class IndustrialSection extends AppCompatActivity {
    ActivityIndustrialSectionBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIndustrialSectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        editor.apply();
        /*
        Arabic 1
        English 2
        Maths 3
        Chemistry 4
        Physics 5
        Biology 6
        Religion 7
        Social Studies 8
        Computer 9
        Special Arabic 10
        Geography 11
        History 12
        Industrial Drawing 13
        sciences Drawing 14
         */

        binding.industrialSectionYearBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.industrialSectionYearBtnContinue.setEnabled(false);
                in_dr = binding.industrialSectionYearEtInDr.getText().toString();
                if (binding.industrialSectionYearEtInDr.getText().toString().trim().equals("") || Integer.parseInt(binding.industrialSectionYearEtInDr.getText().toString()) > 100 ) {
                    binding.industrialSectionYearEtInDr.setError("Please Enter Industrial Drawing Grade and less than 100");
                    binding.industrialSectionYearEtInDr.requestFocus();
                    binding.industrialSectionYearBtnContinue.setEnabled(true);
                    return;
                }
                ph = binding.industrialSectionYearEtPh.getText().toString();
                if (binding.industrialSectionYearEtPh.getText().toString().trim().equals("") || Integer.parseInt(binding.industrialSectionYearEtPh.getText().toString()) > 100 ) {
                    binding.industrialSectionYearEtPh.setError("Please Enter Physics Grade and less than 100");
                    binding.industrialSectionYearEtPh.requestFocus();
                    binding.industrialSectionYearBtnContinue.setEnabled(true);
                    return;
                }
                ma = binding.industrialSectionYearEtMa.getText().toString();
                if (binding.industrialSectionYearEtMa.getText().toString().trim().equals("") || Integer.parseInt(binding.industrialSectionYearEtMa.getText().toString()) > 100 ) {
                    binding.industrialSectionYearEtMa.setError("Please Enter Maths Grade and less than 100");
                    binding.industrialSectionYearEtMa.requestFocus();
                    binding.industrialSectionYearBtnContinue.setEnabled(true);
                    return;
                }
                in_sc = binding.industrialSectionYearEtInSc.getText().toString();
                if (binding.industrialSectionYearEtInSc.getText().toString().trim().equals("") || Integer.parseInt(binding.industrialSectionYearEtInSc.getText().toString()) > 100 ) {
                    binding.industrialSectionYearEtInSc.setError("Please Enter sciences Drawing Grade and less than 100");
                    binding.industrialSectionYearEtInSc.requestFocus();
                    binding.industrialSectionYearBtnContinue.setEnabled(true);
                } else {
                    grad = "0" + "," + "0" + "," + ma + "," + "0" + "," + ph + "," + "0" + "," + "0" + "," + "0" + "," + "0"
                            + "," + "0" + "," + "0" + "," + "0" + "," + in_dr + "," + in_sc;
                    postViewModel.enterUserGrade(token, "6", grad);
                    postViewModel.MldParentItem.observe(IndustrialSection.this, new Observer<ParentItem>() {
                        @Override
                        public void onChanged(ParentItem parentItem) {
                            if (parentItem.getStatus()) {
                              //  Toast.makeText(IndustrialSection.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), timeForQuiz.class));
                                finish();
                            } else
                                Toast.makeText(IndustrialSection.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });
    }
}