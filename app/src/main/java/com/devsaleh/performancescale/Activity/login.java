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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devsaleh.performancescale.Model.ParentSignUpUsers;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityLoginBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;

public class login extends AppCompatActivity {
    ActivityLoginBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token, user_type;
    public static final String TOKEN = "token";
    public static final String TYPE_USER = "type_user";
    public static final String TYPE_SCHOOL = "type_school";
    public static final String USER_ID = "user_id";
    public static final String SPECIALIZATION_USER = "Specialization_user";
    public static final String USER_NAME = "user_name";
    public static final String SCHOOL_NAME = "school_name";
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        user_id = intent.getStringExtra(personalInfo.USER_ID);
        binding.loginEtUserId.setText(user_id);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sp.edit();
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite ThreeBounce1 = new ThreeBounce();
        progressBar.setIndeterminateDrawable(ThreeBounce1);
        binding.loginTvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), personalInfo.class));
            }
        });

        binding.loginBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.loginBtnContinue.setEnabled(false);
                user_id = binding.loginEtUserId.getText().toString();
                if (TextUtils.isEmpty(user_id) || binding.loginEtUserId.getText().toString().trim().equals("")) {
                    binding.loginEtUserId.setError("Please Enter ID");
                    binding.loginEtUserId.requestFocus();
                    binding.loginBtnContinue.setEnabled(true);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    postViewModel.loginForUsers(user_id, "every", "ios");
                    postViewModel.MldSignUpUsers.observe(login.this, new Observer<ParentSignUpUsers>() {
                        @Override
                        public void onChanged(ParentSignUpUsers parentSignUpUsers) {
                            if (parentSignUpUsers.getStatus()) {
                                binding.loginBtnContinue.setEnabled(true);
                                progressBar.setVisibility(View.GONE);
                                token = parentSignUpUsers.getUser().getAccessToken();
                                editor.putString(TOKEN, token);
                                editor.putString(TYPE_USER, parentSignUpUsers.getUser().getType());
                                editor.putString(TYPE_SCHOOL, parentSignUpUsers.getUser().getSchoolType());
                                editor.putString(USER_ID, String.valueOf(parentSignUpUsers.getUser().getId()));
                                editor.putString(SPECIALIZATION_USER, parentSignUpUsers.getUser().getSpecialized());
                                editor.putString(USER_NAME, parentSignUpUsers.getUser().getName());
                                editor.putString(SCHOOL_NAME, parentSignUpUsers.getUser().getSchoolName());
                                editor.apply();
                                if (parentSignUpUsers.getUser().getType().equals("teacher")) {
                                    if (binding.loginRbStudent.isChecked()) {
                                        Toast.makeText(login.this, "You are not a student", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    startActivity(new Intent(login.this, home.class));
                                    finish();
                                } else {
                                    if (binding.loginRbTeacher.isChecked()) {
                                        Toast.makeText(login.this, "You are not a teacher", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    startActivity(new Intent(login.this, resultStudent.class));
                                    finish();
//                                    if (parentSignUpUsers.getUser().getUsers_grades().size() != 0) {
//                                        switch (parentSignUpUsers.getUser().getSpecialized()) {
//                                            case "scientific":
//                                                startActivity(new Intent(login.this, scientificSection.class));
//                                                finish();
//                                                break;
//                                            case "literary":
//                                                startActivity(new Intent(login.this, literarySection.class));
//                                                finish();
//                                                break;
//                                            case "industrial":
//                                                startActivity(new Intent(login.this, IndustrialSection.class));
//                                                finish();
//                                                break;
//                                        }
//                                    } else {
//                                        startActivity(new Intent(login.this, firstYear.class));
//                                        finish();
//                                    }

                                }
                            } else {
                                binding.loginBtnContinue.setEnabled(true);
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(login.this, parentSignUpUsers.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });

        // test hamza code
        Button testStudent = findViewById(R.id.testStudent);
        testStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), selectSubject_H.class);
                startActivity(i);
            }
        });

        Button testMinistry = findViewById(R.id.testMinistry);
        testMinistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MathsGroupMinistry_H.class);
                startActivity(i);
            }
        });

    }
}