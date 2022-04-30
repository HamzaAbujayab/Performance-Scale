package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.devsaleh.performancescale.Activity.quiz;
import com.devsaleh.performancescale.Model.ParentQuizesHomeScreen;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityTimeForQuizBinding;

public class timeForQuiz extends AppCompatActivity {
    ActivityTimeForQuizBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeForQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        editor.apply();
        postViewModel.quizesHomeScreen(token);
        postViewModel.MldQuizesHomeScreen.observe(timeForQuiz.this, new Observer<ParentQuizesHomeScreen>() {
            @Override
            public void onChanged(ParentQuizesHomeScreen parentQuizesHomeScreen) {
                if (parentQuizesHomeScreen.getStatus()) {
                    final long time = Long.parseLong(parentQuizesHomeScreen.getAllQuizesTime());
                    binding.timeForQuizTvNumberQuestions.setText(String.valueOf(parentQuizesHomeScreen.getQuestionsCount()));
                    binding.timeForQuizTvNumberQuizzes.setText(String.valueOf(parentQuizesHomeScreen.getQuizzesCount()));
                    binding.timeForQuizTvTimeQuizzes.setText(String.valueOf(parentQuizesHomeScreen.getAllQuizesTime()));
                    binding.timeForQuizBtnStart.setText("Start Quiz " + parentQuizesHomeScreen.getQuizzesCount() + "/" + parentQuizesHomeScreen.getQuestionsCount());
                } else
                    Toast.makeText(timeForQuiz.this, parentQuizesHomeScreen.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.timeForQuizBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), startQuiz.class));
                startActivity(new Intent(getApplicationContext(), quiz.class));
            }
        });


    }
}