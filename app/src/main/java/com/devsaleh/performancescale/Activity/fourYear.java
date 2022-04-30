package com.devsaleh.performancescale.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.devsaleh.performancescale.Activity.fiveYear;
import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityFourYearBinding;

public class fourYear extends AppCompatActivity {
    ActivityFourYearBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFourYearBinding.inflate(getLayoutInflater());
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

        binding.fourYearBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.fourYearBtnContinue.setEnabled(false);
                ar = binding.fourYearEtAr.getText().toString();
                if (binding.fourYearEtAr.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtAr.getText().toString()) > 100 ) {
                    binding.fourYearEtAr.setError("Please Enter Arabic Grade and less than 100");
                    binding.fourYearEtAr.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                    return;
                }
                en = binding.fourYearEtEn.getText().toString();
                if (binding.fourYearEtEn.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtEn.getText().toString()) > 100 ) {
                    binding.fourYearEtEn.setError("Please Enter English Grade and less than 100");
                    binding.fourYearEtEn.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                    return;
                }
                ma = binding.fourYearEtMa.getText().toString();
                if (binding.fourYearEtMa.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtMa.getText().toString()) > 100) {
                    binding.fourYearEtMa.setError("Please Enter Maths Grade and less than 100");
                    binding.fourYearEtMa.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                    return;
                }
                ch = binding.fourYearEtCh.getText().toString();
                if (binding.fourYearEtCh.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtCh.getText().toString()) > 100 ) {
                    binding.fourYearEtCh.setError("Please Enter Chemistry Grade and less than 100");
                    binding.fourYearEtCh.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                    return;
                }
                ph = binding.fourYearEtPh.getText().toString();
                if ( binding.fourYearEtPh.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtPh.getText().toString()) > 100 ) {
                    binding.fourYearEtPh.setError("Please Enter Physics Grade and less than 100");
                    binding.fourYearEtPh.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                    return;
                }
                bi = binding.fourYearEtBi.getText().toString();
                if (binding.fourYearEtBi.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtBi.getText().toString()) > 100) {
                    binding.fourYearEtBi.setError("Please Enter Biology Grade and less than 100");
                    binding.fourYearEtBi.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                    return;
                }
                re = binding.fourYearEtRe.getText().toString();
                if (binding.fourYearEtRe.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtRe.getText().toString()) > 100 ) {
                    binding.fourYearEtRe.setError("Please Enter Religion Grade and less than 100");
                    binding.fourYearEtRe.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                    return;
                }
                so_st = binding.fourYearEtSs.getText().toString();
                if (binding.fourYearEtSs.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtSs.getText().toString()) > 100 ) {
                    binding.fourYearEtSs.setError("Please Enter Social Studies Grade and less than 100");
                    binding.fourYearEtSs.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                    return;
                }
                co = binding.fourYearEtCo.getText().toString();
                if (binding.fourYearEtCo.getText().toString().trim().equals("") || Integer.parseInt(binding.fourYearEtCo.getText().toString()) > 100 ) {
                    binding.fourYearEtCo.setError("Please Enter Computer Grade and less than 100");
                    binding.fourYearEtCo.requestFocus();
                    binding.fourYearBtnContinue.setEnabled(true);
                } else {
                    grad = ar + "," + en + "," + ma + "," + ch + "," + ph + "," + bi + "," + re + "," + so_st + "," + co
                            + "," + "0"+ "," + "0"+ "," + "0"+ "," + "0"+ "," + "0";
                    postViewModel.enterUserGrade(token, "4", grad);
                    postViewModel.MldParentItem.observe(fourYear.this, new Observer<ParentItem>() {
                        @Override
                        public void onChanged(ParentItem parentItem) {
                            if (parentItem.getStatus()) {
                                Toast.makeText(fourYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), fiveYear.class));
                                finish();

                            } else
                                Toast.makeText(fourYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}