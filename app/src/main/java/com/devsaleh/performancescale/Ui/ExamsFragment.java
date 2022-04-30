package com.devsaleh.performancescale.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.devsaleh.performancescale.Activity.exams;
import com.devsaleh.performancescale.Activity.login;
import com.devsaleh.performancescale.Activity.newExam;
import com.devsaleh.performancescale.Adapter.ExamsAdapter;
import com.devsaleh.performancescale.Adapter.StudentAdapter;
import com.devsaleh.performancescale.Model.ParentSpecializedQuizes;
import com.devsaleh.performancescale.Model.ParentStudent;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.FragmentExamsBinding;
import com.devsaleh.performancescale.databinding.FragmentStudentBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

import java.util.ArrayList;
import java.util.List;


public class ExamsFragment extends Fragment {
    public static final String QUIZ_ID = "quiz_id";
    FragmentExamsBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private ExamsAdapter examsAdapter;
    ProgressBar progressBar;
    private String specialized = "scientific";
    List<Integer> nos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExamsBinding.inflate(inflater, container, false);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        editor.apply();
        progressBar = (ProgressBar) binding.spinKit;
        Sprite ThreeBounce1 = new Circle();
        progressBar.setIndeterminateDrawable(ThreeBounce1);
        binding.examsRvNumberQuizzes.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.examsRvNumberQuizzes.setHasFixedSize(true);
        binding.examsRbScientific.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    specialized = "scientific";
                    getExamsMethod(specialized);
                }
            }
        });
        binding.examsRbLiterary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    specialized = "literary";
                    getExamsMethod(specialized);
                }
            }
        });
        binding.examsRbIndustrial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    specialized = "industrial";
                    getExamsMethod(specialized);
                }
            }
        });

        getExamsMethod(specialized);
        binding.examsBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), newExam.class));
            }
        });

        return binding.getRoot();

    }

    private void getExamsMethod(String specialized) {
        postViewModel.getQuizesBySpecialized(token, specialized);
        postViewModel.MldSpecializedQuizes.observe(getViewLifecycleOwner(), new Observer<ParentSpecializedQuizes>() {
            @Override
            public void onChanged(ParentSpecializedQuizes parentSpecializedQuizes) {
                if (parentSpecializedQuizes.getStatus()) {
                    progressBar.setVisibility(View.GONE);
                    nos = new ArrayList<>();
                    for (int i = 0; i < parentSpecializedQuizes.getStudentQuizes().size(); i++) {
                        nos.add(parentSpecializedQuizes.getStudentQuizes().get(i).getId());
                    }
                    examsAdapter = new ExamsAdapter(nos, getContext());
                    examsAdapter.setQuiz_listener(new ExamsAdapter.OnClickItemListener() {
                        @Override
                        public void onClick(int quiz_id) {
                            Intent intent = new Intent(getContext(), exams.class);
                            intent.putExtra(QUIZ_ID, quiz_id);
                            startActivity(intent);
                        }
                    });
                    binding.examsRvNumberQuizzes.setAdapter(examsAdapter);
                } else
                    Toast.makeText(getContext(), parentSpecializedQuizes.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}