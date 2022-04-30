package com.devsaleh.performancescale.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.devsaleh.performancescale.Model.ExpectedGpa;
import com.devsaleh.performancescale.Model.ParentAverage;
import com.devsaleh.performancescale.Model.Performance;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.Ui.StudentFragment;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityResultBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.squareup.picasso.Picasso;


public class result extends AppCompatActivity {
    // هاي الصفحة ملغية لانو الصفحة الصح والمستخدمة هي resultStudent
    ActivityResultBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token, Specialization_user, user_name, school_name;
    private String ar, en, ma, ch, ph, bi, re, so_st, co, sp_ar, ge, hi, in_dr, in_sc;
    private String allQuizesAvg, sub1_avg, sub2_avg, sub3_avg, sub4_avg, sub5_avg, sub6_avg, sub7_avg, sub8_avg, sub9_avg, sub10_avg, sub11_avg, sub12_avg, sub13_avg, sub14_avg;
    private String gpa, assessment = "80";
    private double gpa1;
    private double num1;
    private double num2;
    private double num3;
    private double max;
    ProgressBar progressBar1, progressBar2, progressBar3;

    @Override
    protected void onStart() {
        super.onStart();
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        progressBar1 = (ProgressBar) findViewById(R.id.spin_kit1);
        Sprite ThreeBounce1 = new ThreeBounce();
        progressBar1.setIndeterminateDrawable(ThreeBounce1);
        getMyMovementsMethod();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        user_id = sp.getString(login.USER_ID, "");
        Specialization_user = sp.getString(login.SPECIALIZATION_USER, "");
        user_name = sp.getString(login.USER_NAME, "");
        school_name = sp.getString(login.SCHOOL_NAME, "");
        editor.apply();
        binding.resultTvUsername.setText(user_name);
        binding.resultTvSpAndSchoolName.setText(Specialization_user + " , " + school_name);
        //----------------
        Intent intent = getIntent();
        user_id = String.valueOf(intent.getIntExtra(StudentFragment.STUDENT_ID, 1));
        Specialization_user = intent.getStringExtra(StudentFragment.STUDENT_SPECIALIZED);
        binding.resultTvUsername.setText(intent.getStringExtra(StudentFragment.STUDENT_NAME));
        binding.resultTvSpAndSchoolName.setText(intent.getStringExtra(StudentFragment.STUDENT_SPECIALIZED) + " , " + intent.getStringExtra(StudentFragment.STUDENT_SCHOOL_NAME));
        progressBar1 = (ProgressBar) findViewById(R.id.spin_kit1);
        progressBar2 = (ProgressBar) findViewById(R.id.spin_kit2);
        progressBar3 = (ProgressBar) findViewById(R.id.spin_kit3);
        Sprite ThreeBounce1 = new ThreeBounce();
        Sprite ThreeBounce2 = new ThreeBounce();
        Sprite ThreeBounce3 = new ThreeBounce();
        progressBar1.setIndeterminateDrawable(ThreeBounce1);
        progressBar2.setIndeterminateDrawable(ThreeBounce2);
        progressBar3.setIndeterminateDrawable(ThreeBounce3);

        getMyMovementsMethod();

    }

    private void getMyMovementsMethod() {
        postViewModel.averageAllSubjects(token, Integer.parseInt(user_id));
        postViewModel.MldParentAverage.observe(result.this, new Observer<ParentAverage>() {
            @Override
            public void onChanged(ParentAverage parentAverage) {
                progressBar1.setVisibility(View.GONE);
                allQuizesAvg = String.valueOf(parentAverage.getYearsQuizAvg());
                binding.resultTvGrade.setText(String.valueOf(parentAverage.getYearsQuizAvg()) + " %");
                sub1_avg = String.valueOf(parentAverage.getSub1Avg());
                sub2_avg = String.valueOf(parentAverage.getSub2Avg());
                sub3_avg = String.valueOf(parentAverage.getSub3Avg());
                sub4_avg = String.valueOf(parentAverage.getSub4Avg());
                sub5_avg = String.valueOf(parentAverage.getSub5Avg());
                sub6_avg = String.valueOf(parentAverage.getSub6Avg());
                sub7_avg = String.valueOf(parentAverage.getSub7Avg());
                sub8_avg = String.valueOf(parentAverage.getSub8Avg());
                sub9_avg = String.valueOf(parentAverage.getSub9Avg());
                sub12_avg = String.valueOf(parentAverage.getSub12Avg());
                ExpectedGpa(allQuizesAvg, sub1_avg, sub2_avg, sub7_avg, sub12_avg, sub3_avg, sub5_avg, sub4_avg, sub6_avg, sub9_avg, sub8_avg);
            }
        });
    }

    private void ExpectedGpa(String allQuizesAvg, String sub1_avg, String sub2_avg, String sub7_avg, String sub12_avg, String sub3_avg, String sub5_avg, String sub4_avg,
                             String sub6_avg, String sub9_avg, String sub8_avg) {
        postViewModel.expected_gpa(sub1_avg, sub2_avg, sub7_avg, sub12_avg, sub3_avg, sub5_avg, sub4_avg, sub6_avg, sub9_avg, sub8_avg);
        postViewModel.MldExpectedGpa.observe(result.this, new Observer<ExpectedGpa>() {
            @Override
            public void onChanged(ExpectedGpa expectedGpa) {
                num1 = Double.parseDouble(expectedGpa.getScientific());
                num2 = Double.parseDouble(expectedGpa.getLiterary());
                num3 = Double.parseDouble(expectedGpa.getIndustrial());
                assessment = allQuizesAvg;
                if (num1 >= num2 && num1 >= num3) {
                    progressBar2.setVisibility(View.GONE);
                    max = num1;
                    binding.resultTvGpaCenter.setText(expectedGpa.getScientific() + "%");
                    binding.resultTvCenter.setText("Scientific");
                    Picasso.get()
                            .load(R.drawable.ic_sciences3)
                            .placeholder(R.drawable.ic_sciences3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvCenter);
                    //-----------------------------------------------
                    binding.resultTvGpaLeft.setText(expectedGpa.getLiterary() + "%");
                    binding.resultTvLeft.setText("Literary");
                    Picasso.get()
                            .load(R.drawable.ic_geography3)
                            .placeholder(R.drawable.ic_geography3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvLeft);
                    //-----------------------------------------------
                    binding.resultTvGpaRight.setText(expectedGpa.getIndustrial() + "%");
                    binding.resultTvRight.setText("Industrial");
                    Picasso.get()
                            .load(R.drawable.ic_industrial3)
                            .placeholder(R.drawable.ic_industrial3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvRight);
                    //-----------------------------------------------
                } else if (num2 >= num1 && num2 >= num3) {
                    progressBar2.setVisibility(View.GONE);
                    max = num2;
                    binding.resultTvGpaLeft.setText(expectedGpa.getScientific() + "%");
                    binding.resultTvLeft.setText("Scientific");
                    Picasso.get()
                            .load(R.drawable.ic_sciences3)
                            .placeholder(R.drawable.ic_sciences3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvLeft);
                    //-----------------------------------------------
                    binding.resultTvGpaCenter.setText(expectedGpa.getLiterary() + "%");
                    binding.resultTvCenter.setText("Literary");
                    Picasso.get()
                            .load(R.drawable.ic_geography3)
                            .placeholder(R.drawable.ic_geography3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvCenter);
                    //-----------------------------------------------
                    binding.resultTvGpaRight.setText(expectedGpa.getIndustrial() + "%");
                    binding.resultTvRight.setText("Industrial");
                    Picasso.get()
                            .load(R.drawable.ic_industrial3)
                            .placeholder(R.drawable.ic_industrial3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvRight);
                    //-----------------------------------------------
                } else {
                    progressBar2.setVisibility(View.GONE);
                    max = num3;
                    binding.resultTvGpaLeft.setText(expectedGpa.getScientific() + "%");
                    binding.resultTvLeft.setText("Scientific");
                    Picasso.get()
                            .load(R.drawable.ic_sciences3)
                            .placeholder(R.drawable.ic_sciences3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvLeft);
                    //-----------------------------------------------
                    binding.resultTvGpaRight.setText(expectedGpa.getLiterary() + "%");
                    binding.resultTvRight.setText("Literary");
                    Picasso.get()
                            .load(R.drawable.ic_geography3)
                            .placeholder(R.drawable.ic_geography3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvRight);
                    //-----------------------------------------------
                    binding.resultTvGpaCenter.setText(expectedGpa.getIndustrial() + "%");
                    binding.resultTvCenter.setText("Industrial");
                    Picasso.get()
                            .load(R.drawable.ic_industrial3)
                            .placeholder(R.drawable.ic_industrial3)
                            .resize(200, 200)
                            .centerCrop()
                            .into(binding.resultIvCenter);
                    //-----------------------------------------------
                }

                switch (Specialization_user) {
                    case "scientific":
                        gpa = expectedGpa.getScientific();
                        gpa1 = Double.parseDouble(expectedGpa.getScientific());
                        Performance(gpa1, assessment);
                        break;
                    case "literary":
                        gpa = expectedGpa.getLiterary();
                        gpa1 = Double.parseDouble(expectedGpa.getLiterary());
                        Performance(gpa1, assessment);
                        break;
                    case "industrial":
                        gpa = expectedGpa.getIndustrial();
                        gpa1 = Double.parseDouble(expectedGpa.getIndustrial());
                        Performance(gpa1, assessment);
                        break;
                }
            }
        });
    }

    private void Performance(double gpa0, String assessment) {
        postViewModel.performance(String.valueOf(gpa0), assessment);
        postViewModel.MldPerformance.observe(result.this, new Observer<Performance>() {
            @Override
            public void onChanged(Performance performance) {
                switch (performance.getPerformance()) {
                    case "High":
                        progressBar3.setVisibility(View.GONE);
                        binding.resultTvSection.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_1));
                        binding.resultTvTrack.setText("You are in the Best Direction");
                        binding.resultTvSection.setText("High");
                        //  binding.resultTvSection.setBackground(getResources().getDrawable(R.drawable.bg_1));

                        break;
                    case "Medium":
                        progressBar3.setVisibility(View.GONE);
                        binding.resultTvSection.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_2));
                        binding.resultTvSection.setText("Medium");
                        if (gpa0 == max) {
                            binding.resultTvTrack.setText("You are in the Best Direction");
                            break;
                        } else {
                            if (num1 == max) {
                                binding.resultTvTrack.setText("You are in a Good Direction\n but scientific is Better for you");
                            }
                            if (num2 == max) {
                                binding.resultTvTrack.setText("You are in a Good Direction\n but literary is Better for you");
                            }
                            if (num3 == max) {
                                binding.resultTvTrack.setText("You are in a Good Direction\n but industrial is Better for you");
                            }
                        }

                        break;
                    case "Low":
                        progressBar3.setVisibility(View.GONE);
                        binding.resultTvSection.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_3));
                        binding.resultTvSection.setText("Low");
//                        if (gpa1 == num2) {
//                            binding.resultTvTrack.setText("You are in the Best Direction");
//                        } else if (gpa1 == num3) {
//                            binding.resultTvTrack.setText("You are in the Best Direction");
//                        }
                        if (num1 < 65 && num2 < 65 && num3 < 65) {
                            if (gpa1 < num2) {
                                binding.resultTvTrack.setText("is Better for you industrial");
                            } else if (gpa1 < num3) {
                                binding.resultTvTrack.setText("is Better for you literary");
                            }
                        } else {
                            if (num1 == max) {
                                binding.resultTvTrack.setText("You are in a Wrong Direction\n but scientific is Better for you");
                            }
                            if (num2 == max) {
                                binding.resultTvTrack.setText("You are in a Wrong Direction\n but literary is Better for you");
                            }
                            if (num3 == max) {
                                binding.resultTvTrack.setText("You are in a Wrong Direction\n but industrial is Better for you");
                            }
                            if (gpa1 == max) {
                                binding.resultTvTrack.setText("You are in the Best Direction");
                            }


                        }
                        break;


                }
            }
        });
    }


}