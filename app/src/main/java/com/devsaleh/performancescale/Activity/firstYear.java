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
import com.devsaleh.performancescale.databinding.ActivityFirstYearBinding;

public class firstYear extends AppCompatActivity {
    ActivityFirstYearBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstYearBinding.inflate(getLayoutInflater());
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

        binding.firstYearBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.firstYearBtnContinue.setEnabled(false);
                ar = binding.firstYearEtAr.getText().toString();
                en = binding.firstYearEtEn.getText().toString();
                ma = binding.firstYearEtMa.getText().toString();
                ch = binding.firstYearEtCh.getText().toString();
                ph = binding.firstYearEtPh.getText().toString();
                bi = binding.firstYearEtBi.getText().toString();
                re = binding.firstYearEtRe.getText().toString();
                so_st = binding.firstYearEtSs.getText().toString();
                co = binding.firstYearEtCo.getText().toString();

                if (TextUtils.isEmpty(ar) || Integer.parseInt(binding.firstYearEtAr.getText().toString()) > 100) {
                    binding.firstYearEtAr.setError("Please Enter Arabic Grade and less than 100");
                    binding.firstYearEtAr.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                    return;
                } else if (TextUtils.isEmpty(en) || Integer.parseInt(binding.firstYearEtEn.getText().toString()) > 100 ) {
                    binding.firstYearEtEn.setError("Please Enter English Grade and less than 100");
                    binding.firstYearEtEn.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                    return;
                } else if (TextUtils.isEmpty(ma) || Integer.parseInt(binding.firstYearEtMa.getText().toString()) > 100) {
                    binding.firstYearEtMa.setError("Please Enter Maths Grade and less than 100");
                    binding.firstYearEtMa.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                    return;
                } else if (TextUtils.isEmpty(ch) || Integer.parseInt(binding.firstYearEtCh.getText().toString()) > 100 ) {
                    binding.firstYearEtCh.setError("Please Enter Chemistry Grade and less than 100");
                    binding.firstYearEtCh.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                    return;
                } else if (TextUtils.isEmpty(ph) || Integer.parseInt(binding.firstYearEtPh.getText().toString()) > 100  ) {
                    binding.firstYearEtPh.setError("Please Enter Physics Grade and less than 100");
                    binding.firstYearEtPh.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                    return;
                } else if (TextUtils.isEmpty(bi) || Integer.parseInt(binding.firstYearEtBi.getText().toString()) > 100 ) {
                    binding.firstYearEtBi.setError("Please Enter Biology Grade and less than 100");
                    binding.firstYearEtBi.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                    return;
                } else if (TextUtils.isEmpty(re) || Integer.parseInt(binding.firstYearEtRe.getText().toString()) > 100 ) {
                    binding.firstYearEtRe.setError("Please Enter Religion Grade and less than 100");
                    binding.firstYearEtRe.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                    return;
                } else if (TextUtils.isEmpty(so_st) || Integer.parseInt(binding.firstYearEtSs.getText().toString()) > 100 ) {
                    binding.firstYearEtSs.setError("Please Enter Social Studies Grade and less than 100");
                    binding.firstYearEtSs.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                    return;
                } else if (TextUtils.isEmpty(co) || Integer.parseInt(binding.firstYearEtCo.getText().toString()) > 100 ) {
                    binding.firstYearEtCo.setError("Please Enter Computer Grade and less than 100");
                    binding.firstYearEtCo.requestFocus();
                    binding.firstYearBtnContinue.setEnabled(true);
                } else {
                    grad = ar + "," + en + "," + ma + "," + ch + "," + ph + "," + bi + "," + re + "," + so_st + "," + co
                            + "," + "0" + "," + "0" + "," + "0" + "," + "0" + "," + "0";
                    postViewModel.enterUserGrade(token, "1", grad);
                    postViewModel.MldParentItem.observe(firstYear.this, new Observer<ParentItem>() {
                        @Override
                        public void onChanged(ParentItem parentItem) {
                            if (parentItem.getStatus()) {
                                Toast.makeText(firstYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), secondYear.class));
                                finish();
                            } else
                                Toast.makeText(firstYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}