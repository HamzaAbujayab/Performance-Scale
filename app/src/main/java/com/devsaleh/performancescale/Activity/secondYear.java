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

import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivitySecondYearBinding;

public class secondYear extends AppCompatActivity {
    ActivitySecondYearBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondYearBinding.inflate(getLayoutInflater());
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

        binding.secondYearBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.secondYearBtnContinue.setEnabled(false);
                ar = binding.secondYearEtAr.getText().toString();
                if (TextUtils.isEmpty(ar) || Integer.parseInt(binding.secondYearEtAr.getText().toString()) > 100) {
                    binding.secondYearEtAr.setError("Please Enter Arabic Grade and less than 100");
                    binding.secondYearEtAr.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                    return;
                }
                en = binding.secondYearEtEn.getText().toString();
                if (TextUtils.isEmpty(en) || Integer.parseInt(binding.secondYearEtEn.getText().toString()) > 100 ) {
                    binding.secondYearEtEn.setError("Please Enter English Grade and less than 100");
                    binding.secondYearEtEn.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                    return;
                }
                ma = binding.secondYearEtMa.getText().toString();
                if (TextUtils.isEmpty(ma) || Integer.parseInt(binding.secondYearEtMa.getText().toString()) > 100) {
                    binding.secondYearEtMa.setError("Please Enter Maths Grade and less than 100");
                    binding.secondYearEtMa.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                    return;
                }
                ch = binding.secondYearEtCh.getText().toString();
                if (TextUtils.isEmpty(ch) || Integer.parseInt(binding.secondYearEtCh.getText().toString()) > 100 ) {
                    binding.secondYearEtCh.setError("Please Enter Chemistry Grade and less than 100");
                    binding.secondYearEtCh.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                    return;
                }
                ph = binding.secondYearEtPh.getText().toString();
                if (TextUtils.isEmpty(ph) || Integer.parseInt(binding.secondYearEtPh.getText().toString()) > 100 ) {
                    binding.secondYearEtPh.setError("Please Enter Physics Grade and less than 100");
                    binding.secondYearEtPh.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                    return;
                }
                bi = binding.secondYearEtBi.getText().toString();
                if (TextUtils.isEmpty(bi) || Integer.parseInt(binding.secondYearEtBi.getText().toString()) > 100 ) {
                    binding.secondYearEtBi.setError("Please Enter Biology Grade and less than 100");
                    binding.secondYearEtBi.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                    return;
                }
                re = binding.secondYearEtRe.getText().toString();
                if (TextUtils.isEmpty(re) || Integer.parseInt(binding.secondYearEtRe.getText().toString()) > 100 ) {
                    binding.secondYearEtRe.setError("Please Enter Religion Grade and less than 100");
                    binding.secondYearEtRe.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                    return;
                }
                so_st = binding.secondYearEtSs.getText().toString();
                if (TextUtils.isEmpty(so_st) || Integer.parseInt(binding.secondYearEtSs.getText().toString()) > 100 ) {
                    binding.secondYearEtSs.setError("Please Enter Social Studies Grade and less than 100");
                    binding.secondYearEtSs.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                    return;
                }
                co = binding.secondYearEtCo.getText().toString();
                if (TextUtils.isEmpty(co) || Integer.parseInt(binding.secondYearEtCo.getText().toString()) > 100 ) {
                    binding.secondYearEtCo.setError("Please Enter Computer Grade and less than 100");
                    binding.secondYearEtCo.requestFocus();
                    binding.secondYearBtnContinue.setEnabled(true);
                } else {
                    grad = ar + "," + en + "," + ma + "," + ch + "," + ph + "," + bi + "," + re + "," + so_st + "," + co
                            + "," + "0"+ "," + "0"+ "," + "0"+ "," + "0"+ "," + "0";
                    postViewModel.enterUserGrade(token, "2", grad);
                    postViewModel.MldParentItem.observe(secondYear.this, new Observer<ParentItem>() {
                        @Override
                        public void onChanged(ParentItem parentItem) {
                            if (parentItem.getStatus()) {
                                Toast.makeText(secondYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), thirdYear.class));
                                finish();

                            } else
                                Toast.makeText(secondYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}