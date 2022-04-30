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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devsaleh.performancescale.Model.ParentSignUpUsers;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.Ui.StudentFragment;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityAddStudentBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

public class addStudent extends AppCompatActivity {
    ActivityAddStudentBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token, name, gender="Male", school_name, school_type="public", specialization="Scientific";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sp.edit();
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite ThreeBounce1 = new Circle();
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
//                    if (binding.personalRbPublic.isChecked())
//                        school_type = "Public";
//                    if (binding.personalRbPrivate.isChecked())
//                        school_type = "Private";
                    if (binding.personalRbScientific.isChecked())
                        specialization = "Scientific";
                    if (binding.personalRbLiterary.isChecked())
                        specialization = "Literary";
                    if (binding.personalRbIndustrial.isChecked())
                        specialization = "Industrial";
                    school_name = binding.personalEtSchoolName.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    postViewModel.signUpUsers(name, gender, "public", school_name, specialization, "student", user_id);
                    postViewModel.MldSignUpUsers.observe(addStudent.this, new Observer<ParentSignUpUsers>() {
                        @Override
                        public void onChanged(ParentSignUpUsers parentSignUpUsers) {
                            if (parentSignUpUsers.getStatus()) {
                                binding.personalBtnStart.setEnabled(true);
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), addStudentMarks.class);
                                intent.putExtra(StudentFragment.STUDENT_ID, parentSignUpUsers.getUser().getId());
                                startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(addStudent.this, parentSignUpUsers.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });



    }
}