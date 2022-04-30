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
import com.devsaleh.performancescale.databinding.ActivityFiveYearBinding;

public class fiveYear extends AppCompatActivity {
    ActivityFiveYearBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String Specialization_user, user_id, token;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFiveYearBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        Specialization_user = sp.getString(login.SPECIALIZATION_USER, "");
        editor.apply();
        /*

         */

        binding.fiveYearBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.fiveYearBtnContinue.setEnabled(false);
                ar = binding.fiveYearEtAr.getText().toString();
                if (binding.fiveYearEtAr.getText().toString().trim().equals("") || Integer.parseInt(binding.fiveYearEtAr.getText().toString()) > 100) {
                    binding.fiveYearEtAr.setError("Please Enter Arabic Grade and less than 100");
                    binding.fiveYearEtAr.requestFocus();
                    binding.fiveYearBtnContinue.setEnabled(true);
                    return;
                }
                en = binding.fiveYearEtEn.getText().toString();
                if (binding.fiveYearEtEn.getText().toString().trim().equals("") || Integer.parseInt(binding.fiveYearEtEn.getText().toString()) > 100 ) {
                    binding.fiveYearEtEn.setError("Please Enter English Grade and less than 100");
                    binding.fiveYearEtEn.requestFocus();
                    binding.fiveYearBtnContinue.setEnabled(true);
                    return;
                }
                re = binding.fiveYearEtRe.getText().toString();
                if (binding.fiveYearEtRe.getText().toString().trim().equals("") || Integer.parseInt(binding.fiveYearEtRe.getText().toString()) > 100 ) {
                    binding.fiveYearEtRe.setError("Please Enter Religion Grade and less than 100");
                    binding.fiveYearEtRe.requestFocus();
                    binding.fiveYearBtnContinue.setEnabled(true);
                    return;
                }
                hi = binding.fiveYearEtHi.getText().toString();
                if (binding.fiveYearEtHi.getText().toString().trim().equals("") || Integer.parseInt(binding.fiveYearEtHi.getText().toString()) > 100 ) {
                    binding.fiveYearEtHi.setError("Please Enter History Grade and less than 100");
                    binding.fiveYearEtHi.requestFocus();
                    binding.fiveYearBtnContinue.setEnabled(true);
                } else {
                    grad = ar + "," + en + "," + "0" + "," + "0" + "," + "0" + "," + "0" + "," + re + "," + "0" + "," + "0"
                            + "," + "0" + "," + "0" + "," + hi + "," + "0" + "," + "0";
                    postViewModel.enterUserGrade(token, "5", grad);
                    postViewModel.MldParentItem.observe(fiveYear.this, new Observer<ParentItem>() {
                        @Override
                        public void onChanged(ParentItem parentItem) {
                            if (parentItem.getStatus()) {
                                Toast.makeText(fiveYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                                switch (Specialization_user) {
                                    case "scientific":
                                        startActivity(new Intent(getApplicationContext(), scientificSection.class));
                                        finish();
                                        break;
                                    case "literary":
                                        startActivity(new Intent(getApplicationContext(), literarySection.class));
                                        finish();
                                        break;
                                    case "industrial":
                                        startActivity(new Intent(getApplicationContext(), IndustrialSection.class));
                                        finish();
                                        break;
                                }
                            } else
                                Toast.makeText(fiveYear.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });


    }
}