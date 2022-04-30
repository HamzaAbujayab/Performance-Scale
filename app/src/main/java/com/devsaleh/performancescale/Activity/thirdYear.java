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

import com.devsaleh.performancescale.Activity.fourYear;
import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityThirdYearBinding;

public class thirdYear extends AppCompatActivity {
    ActivityThirdYearBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdYearBinding.inflate(getLayoutInflater());
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

        binding.thirdYearBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.thirdYearBtnContinue.setEnabled(false);
                ar = binding.thirdYearEtAr.getText().toString();
                if (binding.thirdYearEtAr.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtAr.getText().toString()) > 100 ) {
                    binding.thirdYearEtAr.setError("Please Enter Arabic Grade and less than 100");
                    binding.thirdYearEtAr.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                    return;
                }
                en = binding.thirdYearEtEn.getText().toString();
                if (binding.thirdYearEtEn.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtEn.getText().toString()) > 100 ) {
                    binding.thirdYearEtEn.setError("Please Enter English Grade and less than 100");
                    binding.thirdYearEtEn.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                    return;
                }
                ma = binding.thirdYearEtMa.getText().toString();
                if (binding.thirdYearEtMa.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtMa.getText().toString()) > 100) {
                    binding.thirdYearEtMa.setError("Please Enter Maths Grade and less than 100");
                    binding.thirdYearEtMa.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                    return;
                }
                ch = binding.thirdYearEtCh.getText().toString();
                if (binding.thirdYearEtCh.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtCh.getText().toString()) > 100 ) {
                    binding.thirdYearEtCh.setError("Please Enter Chemistry Grade and less than 100");
                    binding.thirdYearEtCh.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                    return;
                }
                ph = binding.thirdYearEtPh.getText().toString();
                if (binding.thirdYearEtPh.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtPh.getText().toString()) > 100 ) {
                    binding.thirdYearEtPh.setError("Please Enter Physics Grade and less than 100");
                    binding.thirdYearEtPh.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                    return;
                }
                bi = binding.thirdYearEtBi.getText().toString();
                if (binding.thirdYearEtBi.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtBi.getText().toString()) > 100 ) {
                    binding.thirdYearEtBi.setError("Please Enter Biology Grade and less than 100");
                    binding.thirdYearEtBi.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                    return;
                }
                re = binding.thirdYearEtRe.getText().toString();
                if (binding.thirdYearEtRe.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtRe.getText().toString()) > 100) {
                    binding.thirdYearEtRe.setError("Please Enter Religion Grade and less than 100");
                    binding.thirdYearEtRe.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                    return;
                }
                so_st = binding.thirdYearEtSs.getText().toString();
                if (binding.thirdYearEtSs.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtSs.getText().toString()) > 100 ) {
                    binding.thirdYearEtSs.setError("Please Enter Social Studies Grade and less than 100");
                    binding.thirdYearEtSs.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                    return;
                }
                co = binding.thirdYearEtCo.getText().toString();
                if (binding.thirdYearEtCo.getText().toString().trim().equals("") || Integer.parseInt(binding.thirdYearEtCo.getText().toString()) > 100 ) {
                    binding.thirdYearEtCo.setError("Please Enter Computer Grade and less than 100");
                    binding.thirdYearEtCo.requestFocus();
                    binding.thirdYearBtnContinue.setEnabled(true);
                } else {
                    grad = ar + "," + en + "," + ma + "," + ch + "," + ph + "," + bi + "," + re + "," + so_st + "," + co
                            + "," + "0"+ "," + "0"+ "," + "0"+ "," + "0"+ "," + "0";
                    postViewModel.enterUserGrade(token, "3", grad);
                    postViewModel.MldParentItem.observe(thirdYear.this, new Observer<ParentItem>() {
                        @Override
                        public void onChanged(ParentItem parentItem) {
                            if (parentItem.getStatus()) {
                                Toast.makeText(thirdYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), fourYear.class));
                                finish();

                            } else
                                Toast.makeText(thirdYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}