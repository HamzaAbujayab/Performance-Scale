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

import com.devsaleh.performancescale.Activity.literarySection;
import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityScientificSectionBinding;

public class scientificSection extends AppCompatActivity {
    ActivityScientificSectionBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScientificSectionBinding.inflate(getLayoutInflater());
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
         */

        binding.scientificSectionYearBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.scientificSectionYearBtnContinue.setEnabled(false);
                ch = binding.scientificSectionYearEtCh.getText().toString();
                if (binding.scientificSectionYearEtCh.getText().toString().trim().equals("") || Integer.parseInt(binding.scientificSectionYearEtCh.getText().toString()) > 100 ) {
                    binding.scientificSectionYearEtCh.setError("Please Enter Chemistry Grade and less than 100");
                    binding.scientificSectionYearEtCh.requestFocus();
                    binding.scientificSectionYearBtnContinue.setEnabled(true);
                    return;
                }
                ph = binding.scientificSectionYearEtPh.getText().toString();
                if (binding.scientificSectionYearEtPh.getText().toString().trim().equals("") || Integer.parseInt(binding.scientificSectionYearEtPh.getText().toString()) > 100 ) {
                    binding.scientificSectionYearEtPh.setError("Please Enter Physics Grade and less than 100");
                    binding.scientificSectionYearEtPh.requestFocus();
                    binding.scientificSectionYearBtnContinue.setEnabled(true);
                    return;
                }
                bi = binding.scientificSectionYearEtBi.getText().toString();
                if (binding.scientificSectionYearEtBi.getText().toString().trim().equals("") || Integer.parseInt(binding.scientificSectionYearEtBi.getText().toString()) > 100 ) {
                    binding.scientificSectionYearEtBi.setError("Please Enter Biology Grade and less than 100");
                    binding.scientificSectionYearEtBi.requestFocus();
                    binding.scientificSectionYearBtnContinue.setEnabled(true);
                    return;
                }
                ma = binding.scientificSectionYearEtMa.getText().toString();
                if (binding.scientificSectionYearEtMa.getText().toString().trim().equals("") || Integer.parseInt(binding.scientificSectionYearEtMa.getText().toString()) > 100 ) {
                    binding.scientificSectionYearEtMa.setError("Please Enter Maths Grade and less than 100");
                    binding.scientificSectionYearEtMa.requestFocus();
                    binding.scientificSectionYearBtnContinue.setEnabled(true);
                } else {
                    grad = "0" + "," + "0" + "," + ma + "," + ch + "," + ph + "," + bi + "," + "0" + "," + "0" + "," + "0"
                            + "," + "0" + "," + "0" + "," + "0" + "," + "0" + "," + "0";
                    postViewModel.enterUserGrade(token, "6", grad);
                    postViewModel.MldParentItem.observe(scientificSection.this, new Observer<ParentItem>() {
                        @Override
                        public void onChanged(ParentItem parentItem) {
                            if (parentItem.getStatus()) {
                                //Toast.makeText(scientificSection.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), timeForQuiz.class));
                                finish();

                            } else
                                Toast.makeText(scientificSection.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}