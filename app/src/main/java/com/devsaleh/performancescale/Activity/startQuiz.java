package com.devsaleh.performancescale.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.devsaleh.performancescale.Adapter.QuizQuestionsAdapter;
import com.devsaleh.performancescale.Model.ParentStudentQuizes;
import com.devsaleh.performancescale.Model.ParentUserAnswerQuiz;
import com.devsaleh.performancescale.Model.Question;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityQuizBinding;
import com.devsaleh.performancescale.databinding.ActivityStartQuizBinding;

import java.util.HashMap;
import java.util.Map;

public class startQuiz extends AppCompatActivity {
    ActivityStartQuizBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token,type_school;
    int to_quiz = 0, to_question = 0;
    int from_quiz = 1, from_question = 1;
    int show_quiz = 1, show_to_question = 1, show_from_question = 1;
    private CountDownTimer countDownTimer;
    // private long time = 600000; // 10 min
    // private long time = 240000; // 4min
    private static long time = 15;
    private int question_id;
    //---------------------------
    private QuizQuestionsAdapter quizQuestionsAdapter;
    private int quiz = 0; // first quiz
    //private int time_quiz = 15; // time quiz
    private int number_of_quiz = 1;
    private int number_of_questions = 1;
    private String[] questions_id;
    private String[] answers_id;
    private int count = 0;
    private int test = 0;
    MediaPlayer click,click2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        type_school = sp.getString(login.TYPE_SCHOOL, "");

        editor.apply();
        binding.startQuizRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.startQuizRv.setHasFixedSize(true);
        click = MediaPlayer.create(getApplicationContext(), R.raw.click1);
        click2 = MediaPlayer.create(getApplicationContext(), R.raw.click2);


        QuizMethod(quiz);
        binding.startQuizBtnNextQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.startQuizBtnNextQuiz.setEnabled(false);
                click2.start();
                /*
                for (int i = 0; i < questions_id.length; i++) {
                    Log.d("TAG2", "question " + questions_id[i] + "// answer " + answers_id[i]);
                }
                */
                Map<String, String> partmap = new HashMap<>();
                for (int i = 0; i < questions_id.length; i++) {
                    partmap.put("question_ids[" + i + "]", questions_id[i]);
                    partmap.put("quiz_answers[" + i + "]", answers_id[i]);
                }
                postViewModel.userAnswerQuiz(token, partmap);
                postViewModel.MldUserAnswerQuiz.observe(startQuiz.this, new Observer<ParentUserAnswerQuiz>() {
                    @Override
                    public void onChanged(ParentUserAnswerQuiz parentUserAnswerQuiz) {
                       // Toast.makeText(startQuiz.this, parentUserAnswerQuiz.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
                time = 0;
                test = 0;
                countDownTimer.cancel();
                binding.startQuizBtnNextQuiz.setEnabled(true);
                QuizMethod(++quiz);
            }
        });
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
                binding.startQuizBtnNextQuiz.setEnabled(true);
                QuizMethod(++quiz);
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

    private void QuizMethod(final int start_quiz) {
        if (start_quiz == number_of_quiz || start_quiz > number_of_quiz) {
            //Toast.makeText(this, "End Of Quiz", Toast.LENGTH_SHORT).show();
            binding.startQuizBtnNextQuiz.setText("End Quiz");
            countDownTimer.cancel();
            if (type_school.equals("public")){
                startActivity(new Intent(getApplicationContext(), successAssessment.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), result.class));
                finish();
            }
            return;
        }
        postViewModel.getStudentQuizes(token);
        postViewModel.MldStudentQuizes.observe(startQuiz.this, new Observer<ParentStudentQuizes>() {
            @Override
            public void onChanged(ParentStudentQuizes parentStudentQuizes) {
                if (parentStudentQuizes.getStatus()) {
                    number_of_quiz = parentStudentQuizes.getStudentQuizes().size();
                    // if time  !=  null ...
                    //  time = parentStudentQuizes.getStudentQuizes().get(quiz).getTime();
                    //  TimerMethod(time * 60000);
                    //   binding.quizTvNumberTimeQuiz.setText(String.valueOf(time));
                    //----------------------------------
                    if (test != 1) {
                        time = parentStudentQuizes.getStudentQuizes().get(start_quiz).getTime();
                        binding.quizTvNumberTimeQuiz.setText(String.valueOf(time));
                        TimerMethod(time * 60000);
                        test = 1;
                    }
                    //----------------------------------
                    count = start_quiz + 1;
                    binding.quizTvNumberQuiz.setText("Quiz " + count);
                    number_of_questions = parentStudentQuizes.getStudentQuizes().get(start_quiz).getQuestions().size() - 1;
                    questions_id = new String[parentStudentQuizes.getStudentQuizes().get(start_quiz).getQuestions().size()];
                    answers_id = new String[parentStudentQuizes.getStudentQuizes().get(start_quiz).getQuestions().size()];
                    quizQuestionsAdapter = new QuizQuestionsAdapter(parentStudentQuizes.getStudentQuizes().get(start_quiz).getQuestions(), getApplicationContext());
                    binding.startQuizRv.setAdapter(quizQuestionsAdapter);
                    quizQuestionsAdapter.setAnswer1Listener(new QuizQuestionsAdapter.OnClickAnswer1Listener() {
                        @Override
                        public void onClick(Question question, int position) {
                            if (position > number_of_questions) {
                                //QuizMethod(quiz++);
                                return;
                            }
                            click.start();
                            questions_id[position] = String.valueOf(question.getId());
                            answers_id[position] = question.getAnswer1();

                        }
                    });
                    quizQuestionsAdapter.setAnswer2Listener(new QuizQuestionsAdapter.OnClickAnswer2Listener() {
                        @Override
                        public void onClick(Question question, int position) {
                            if (position > number_of_questions) {
                                // QuizMethod(quiz++);
                                return;
                            }
                            click.start();
                            questions_id[position] = String.valueOf(question.getId());
                            answers_id[position] = question.getAnswer2();
                        }
                    });
                    quizQuestionsAdapter.setAnswer3Listener(new QuizQuestionsAdapter.OnClickAnswer3Listener() {
                        @Override
                        public void onClick(Question question, int position) {
                            if (position > number_of_questions) {
                                //QuizMethod(quiz++);
                                return;
                            }
                            click.start();
                            questions_id[position] = String.valueOf(question.getId());
                            answers_id[position] = question.getAnswer3();
                        }
                    });
                    quizQuestionsAdapter.setAnswer4Listener(new QuizQuestionsAdapter.OnClickAnswer4Listener() {
                        @Override
                        public void onClick(Question question, int position) {
                            if (position > number_of_questions) {
                                // QuizMethod(quiz++);
                                return;
                            }
                            click.start();
                            questions_id[position] = String.valueOf(question.getId());
                            answers_id[position] = question.getAnswer4();
                        }
                    });

                } else
                    Toast.makeText(startQuiz.this, parentStudentQuizes.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}