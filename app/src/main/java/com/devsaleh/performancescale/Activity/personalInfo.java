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

import com.devsaleh.performancescale.Activity.firstYear;
import com.devsaleh.performancescale.Model.ParentSignUpUsers;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityPersonalInfoBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;

public class personalInfo extends AppCompatActivity {
    ActivityPersonalInfoBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token, name, gender="Male", school_name, school_type="Public", specialization="Scientific";
    public static final String TOKEN = "token";
    public static final String TYPE_USER = "type_user";
    public static final String USER_ID = "user_id";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sp.edit();
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite ThreeBounce1 = new ThreeBounce();
        progressBar.setIndeterminateDrawable(ThreeBounce1);
        binding.personalBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.personalBtnStart.setEnabled(false);
                name = binding.personalEtName.getText().toString();
                user_id = binding.personalEtUserId.getText().toString();
                if (TextUtils.isEmpty(user_id) || binding.personalEtUserId.getText().toString().trim().equals("")) {
                    binding.personalEtUserId.setError("Please Enter User ID");
                    binding.personalEtUserId.requestFocus();
                    binding.personalBtnStart.setEnabled(true);
                    return;
                }
                if (TextUtils.isEmpty(name) || binding.personalEtName.getText().toString().trim().equals("")) {
                    binding.personalEtName.setError("Please Enter Name");
                    binding.personalEtName.requestFocus();
                    binding.personalBtnStart.setEnabled(true);
                } else {
                    if (binding.personalRbMale.isChecked())
                        gender = "Male";
                    if (binding.personalRbFemale.isChecked())
                        gender = "Female";
                    if (binding.personalRbPublic.isChecked())
                        school_type = "Public";
                    if (binding.personalRbPrivate.isChecked())
                        school_type = "Private";
                    if (binding.personalRbScientific.isChecked())
                        specialization = "Scientific";
                    if (binding.personalRbLiterary.isChecked())
                        specialization = "Literary";
                    if (binding.personalRbIndustrial.isChecked())
                        specialization = "Industrial";
                    school_name = binding.personalEtSchoolName.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    postViewModel.signUpUsers(name, gender, school_type, school_name, specialization, "student", user_id);
                    postViewModel.MldSignUpUsers.observe(personalInfo.this, new Observer<ParentSignUpUsers>() {
                        @Override
                        public void onChanged(ParentSignUpUsers parentSignUpUsers) {
                            if (parentSignUpUsers.getStatus()) {
                                binding.personalBtnStart.setEnabled(true);
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), login.class);
                                intent.putExtra(USER_ID,parentSignUpUsers.getUser().getUserId());
                                startActivity(intent);
                                finish();

                                /*
                                token = parentSignUpUsers.getUser().getAccessToken();
                                editor.putString(TOKEN, token);
                                editor.putString(TYPE_USER, parentSignUpUsers.getUser().getType());
                                editor.apply();
                                switch (parentSignUpUsers.getUser().getSpecialized()) {
                                    case "scientific":
                                        startActivity(new Intent(personalInfo.this, scientificSection.class));
                                        finish();
                                        break;
                                    case "literary":
                                        startActivity(new Intent(personalInfo.this, literarySection.class));
                                        finish();
                                        break;
                                    case "industrial":
                                        startActivity(new Intent(personalInfo.this, IndustrialSection.class));
                                        finish();
                                        break;
                                }
                                */

                            } else
                                Toast.makeText(personalInfo.this, parentSignUpUsers.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }
}