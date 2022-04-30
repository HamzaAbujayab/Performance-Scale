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

import com.devsaleh.performancescale.Activity.IndustrialSection;
import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityLiterarySectionBinding;

public class literarySection extends AppCompatActivity {
    ActivityLiterarySectionBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLiterarySectionBinding.inflate(getLayoutInflater());
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

        binding.literarySectionYearBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.literarySectionYearBtnContinue.setEnabled(false);
                sp_ar = binding.literarySectionYearEtSpAr.getText().toString();
                if (binding.literarySectionYearEtSpAr.getText().toString().trim().equals("") || Integer.parseInt(binding.literarySectionYearEtSpAr.getText().toString()) > 100 ) {
                    binding.literarySectionYearEtSpAr.setError("Please Enter Special Arabic Grade and less than 100");
                    binding.literarySectionYearEtSpAr.requestFocus();
                    binding.literarySectionYearBtnContinue.setEnabled(true);
                    return;
                }
                ge = binding.literarySectionYearEtGe.getText().toString();
                if (binding.literarySectionYearEtGe.getText().toString().trim().equals("") ||  Integer.parseInt(binding.literarySectionYearEtGe.getText().toString()) > 100 ) {
                    binding.literarySectionYearEtGe.setError("Please Enter Geography Grade and less than 100");
                    binding.literarySectionYearEtGe.requestFocus();
                    binding.literarySectionYearBtnContinue.setEnabled(true);
                    return;
                }
                hi = binding.literarySectionYearEtHi.getText().toString();
                if (binding.literarySectionYearEtHi.getText().toString().trim().equals("") || Integer.parseInt(binding.literarySectionYearEtHi.getText().toString()) > 100 ) {
                    binding.literarySectionYearEtHi.setError("Please Enter History Grade and less than 100");
                    binding.literarySectionYearEtHi.requestFocus();
                    binding.literarySectionYearBtnContinue.setEnabled(true);
                    return;
                }
                ma = binding.literarySectionYearEtMa.getText().toString();
                if (binding.literarySectionYearEtMa.getText().toString().trim().equals("") || Integer.parseInt(binding.literarySectionYearEtMa.getText().toString()) > 100 ) {
                    binding.literarySectionYearEtMa.setError("Please Enter Maths Grade and less than 100");
                    binding.literarySectionYearEtMa.requestFocus();
                    binding.literarySectionYearBtnContinue.setEnabled(true);
                } else {
                    grad = "0" + "," + "0" + "," + ma + "," + "0" + "," + "0" + "," + "0" + "," + "0" + "," + "0" + "," + "0"
                            + "," + sp_ar + "," + ge + "," + hi + "," + "0" + "," + "0";
                    postViewModel.enterUserGrade(token, "6", grad);
                    postViewModel.MldParentItem.observe(literarySection.this, new Observer<ParentItem>() {
                        @Override
                        public void onChanged(ParentItem parentItem) {
                            if (parentItem.getStatus()) {
                               // Toast.makeText(literarySection.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), timeForQuiz.class));
                                finish();
                            } else
                                Toast.makeText(literarySection.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}