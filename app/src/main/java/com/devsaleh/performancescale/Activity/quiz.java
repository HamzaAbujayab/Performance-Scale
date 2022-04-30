package com.devsaleh.performancescale.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devsaleh.performancescale.Model.ParentStudentQuizes;
import com.devsaleh.performancescale.Model.ParentUserAnswerQuiz;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityQuizBinding;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class quiz extends AppCompatActivity {
    ActivityQuizBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token, type_school;
    int to_quiz = 0, to_question = 0;
    int from_quiz = 1, from_question = 1;
    int show_quiz = 1, show_to_question = 1, show_from_question = 1;
    private CountDownTimer countDownTimer;
    // private long time = 600000; // 10 min
    // private long time = 240000; // 4min
    private static long time;
    private int question_id;
    private String answers;
    MediaPlayer click, click2;
    private String[] questions_id;
    private String[] answers_id;
    private int position = 0;
    private int test = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        type_school = sp.getString(login.TYPE_SCHOOL, "");
        editor.apply();
        click = MediaPlayer.create(getApplicationContext(), R.raw.click1);
        click2 = MediaPlayer.create(getApplicationContext(), R.raw.click2);
        postViewModel.getStudentQuizes(token);
        postViewModel.MldStudentQuizes.observe(quiz.this, new Observer<ParentStudentQuizes>() {
            @Override
            public void onChanged(ParentStudentQuizes parentStudentQuizes) {
                if (parentStudentQuizes.getStatus()) {
                    binding.quizBtnNext.setEnabled(true);
                    //  binding.quizTvNumberTimeQuiz.setText(parentStudentQuizes.getAllQuizesTime());
                    //  time = Long.parseLong(parentStudentQuizes.getAllQuizesTime());
                    //  TimerMethod(time * 60000);
//                    from_quiz = parentStudentQuizes.getStudentQuizes().size() - 1;
//                    from_question = parentStudentQuizes.getStudentQuizes().get(to_quiz).getQuestions().size() - 1;
//                    show_to_question = to_question + 1;
//                    show_from_question = from_question + 1;
//                    show_quiz = to_quiz + 1;
//                    binding.quizTvNumberQuizzesQuestion.setText("Question " + to_question + "/" + from_question);
//                    binding.quizTvNumberQuiz.setText("Quiz " + to_quiz);
                }
            }
        });
        QuizMethod(to_quiz, to_question);
        binding.quizBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.quizTvFirstAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a1));
                binding.quizTvSecondAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a2));
                binding.quizTvThirdAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a3));
                binding.quizTvForthAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a4));
                click2.start();
                position = position + 1;
                if (to_question == from_question) {
                    //request send post answer quiz1
                    Map<String, String> partmap = new HashMap<>();
                    for (int i = 0; i < questions_id.length; i++) {
                        partmap.put("question_ids[" + i + "]", questions_id[i]);
                        partmap.put("quiz_answers[" + i + "]", answers_id[i]);
                        Log.d("TAG", "question_ids: [" + i + "]" + questions_id[i]);
                        Log.d("TAG", "answers_id: [" + i + "]" + answers_id[i]);
                    }
                    postViewModel.userAnswerQuiz(token, partmap);
                    postViewModel.MldUserAnswerQuiz.observe(quiz.this, new Observer<ParentUserAnswerQuiz>() {
                        @Override
                        public void onChanged(ParentUserAnswerQuiz parentUserAnswerQuiz) {
                            // Toast.makeText(startQuiz.this, parentUserAnswerQuiz.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    position = 0;
                    to_quiz = to_quiz + 1;
                    to_question = -1;
                    time = 0;
                    test = 0;
                    countDownTimer.cancel();
                }
                QuizMethod(to_quiz, to_question += 1);
            }
        });
        // answer

        binding.quizTvFirstAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                binding.quizTvFirstAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clik_answer));
                binding.quizTvSecondAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a2));
                binding.quizTvThirdAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a3));
                binding.quizTvForthAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a4));
                questions_id[position] = String.valueOf(question_id);
                answers_id[position] = binding.quizTvFirstAnswer.getText().toString();

            }
        });
        binding.quizTvSecondAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                binding.quizTvSecondAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clik_answer));
                binding.quizTvFirstAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a1));
                binding.quizTvThirdAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a3));
                binding.quizTvForthAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a4));
                questions_id[position] = String.valueOf(question_id);
                answers_id[position] = binding.quizTvSecondAnswer.getText().toString();
            }
        });
        binding.quizTvThirdAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                binding.quizTvThirdAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clik_answer));
                binding.quizTvFirstAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a1));
                binding.quizTvSecondAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a2));
                binding.quizTvForthAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a4));
                questions_id[position] = String.valueOf(question_id);
                answers_id[position] = binding.quizTvThirdAnswer.getText().toString();

            }

        });
        binding.quizTvForthAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                binding.quizTvForthAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clik_answer));
                binding.quizTvFirstAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a1));
                binding.quizTvSecondAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a2));
                binding.quizTvThirdAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a3));
                questions_id[position] = String.valueOf(question_id);
                answers_id[position] = binding.quizTvForthAnswer.getText().toString();
            }
        });
        // next
        /*
        binding.quizBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.quizBtnNext.setEnabled(false);
                postViewModel.userAnswerQuiz(token, String.valueOf(question_id), answers);
                postViewModel.MldUserAnswerQuiz.observe(quiz.this, new Observer<ParentUserAnswerQuiz>() {
                    @Override
                    public void onChanged(ParentUserAnswerQuiz parentUserAnswerQuiz) {
                        binding.quizBtnNext.setEnabled(true);
                        binding.quizTvFirstAnswer.setEnabled(true);
                        binding.quizTvSecondAnswer.setEnabled(true);
                        binding.quizTvThirdAnswer.setEnabled(true);
                        binding.quizTvForthAnswer.setEnabled(true);
                        binding.quizTvFirstAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a1));
                        binding.quizTvSecondAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a2));
                        binding.quizTvThirdAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a3));
                        binding.quizTvForthAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.a4));
                    }
                });

                if (from_question == to_question) {
                    to_quiz++;
                    to_question = 0;
                    QuizMethod(to_quiz, to_question);
                    return;
                }
                to_question++;
                QuizMethod(to_quiz, to_question);
            }
        });
        */
        binding.quizTvBaxk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void TimerMethod(long t) {
        updateTime();
        countDownTimer = new CountDownTimer(t, 1000) {
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateTime();
            }

            public void onFinish() {
                countDownTimer.cancel();
                binding.quizTvNumberTimeQuiz.setText("Done");
                Toast.makeText(getApplicationContext(), "Time is Done", Toast.LENGTH_SHORT).show();
                time = 0;
                test = 0;
                position = 0;
                to_question = -1;
                to_quiz++;
                QuizMethod(to_quiz, to_question += 1);
            }
        }.start();
    }

    private void updateTime() {
        int minutes = (int) time / 60000;
        int seconds = (int) time % 60000 / 1000;
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        binding.quizTvNumberTimeQuiz.setText(timeLeftText);
    }

    private void QuizMethod(int quiz, int question) {
        if (quiz == from_quiz || quiz > from_quiz) {
            binding.quizBtnNext.setText("End Quiz");
            countDownTimer.cancel();
            Toast.makeText(this, "Questions are over", Toast.LENGTH_SHORT).show();
            if (type_school.equals("public")) {
                startActivity(new Intent(getApplicationContext(), successAssessment.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), resultStudent.class));
                finish();
            }
            return;
        }


        postViewModel.getStudentQuizes(token);
        postViewModel.MldStudentQuizes.observe(quiz.this, new Observer<ParentStudentQuizes>() {
            @Override
            public void onChanged(ParentStudentQuizes parentStudentQuizes) {
                if (parentStudentQuizes.getStatus()) {
                    binding.quizBtnNext.setEnabled(true);
                    question_id = parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(position).getId();
                    from_quiz = parentStudentQuizes.getStudentQuizes().size();
                    from_question = parentStudentQuizes.getStudentQuizes().get(to_quiz).getQuestions().size() - 1;
                    show_to_question = to_question + 1;
                    show_from_question = from_question + 1;
                    show_quiz = to_quiz + 1;
                    binding.quizTvNumberQuizzesQuestion.setText("Question " + show_to_question + "/" + show_from_question);
                    binding.quizTvNumberQuiz.setText("Quiz " + show_quiz);
                    if (test != 1) {
                        time = parentStudentQuizes.getStudentQuizes().get(quiz).getTime();
                        binding.quizTvNumberTimeQuiz.setText(String.valueOf(time));
                        TimerMethod(time * 60000);
                        test = 1;
                        questions_id = new String[parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().size()];
                        answers_id = new String[parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().size()];
                        binding.quizBtnNext.setText("Next");
                    }

                    binding.quizTvQuestion.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getQuestion());
                    binding.quizTvFirstAnswer.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getAnswersRandomly().get(0));
                    binding.quizTvSecondAnswer.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getAnswersRandomly().get(1));
                    binding.quizTvThirdAnswer.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getAnswersRandomly().get(2));
                    binding.quizTvForthAnswer.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getAnswersRandomly().get(3));
                    /*
                    binding.quizTvFirstAnswer.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getAnswer1());
                    binding.quizTvSecondAnswer.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getAnswer2());
                    binding.quizTvThirdAnswer.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getAnswer3());
                    binding.quizTvForthAnswer.setText(parentStudentQuizes.getStudentQuizes().get(quiz).getQuestions().get(question).getAnswer4());
                */
                } else
                    Toast.makeText(quiz.this, parentStudentQuizes.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}