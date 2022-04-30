package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devsaleh.performancescale.Adapter.QuizDetailsAdapter;
import com.devsaleh.performancescale.Model.Parent;
import com.devsaleh.performancescale.Model.ParentQuestion;
import com.devsaleh.performancescale.Model.ParentQuizDetails;
import com.devsaleh.performancescale.Model.Question;
import com.devsaleh.performancescale.Model.StudentQuizQuestions;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.Ui.ExamsFragment;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityExamsBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class exams extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ActivityExamsBinding binding;
    private int quiz_id;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    ProgressBar progressBar;
    private QuizDetailsAdapter quizDetailsAdapter;
    private String question, answer, answer1, answer2, answer3, answer4;
    private EditText et_question, et_answer1, et_answer2, et_answer3, et_answer4;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExamsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        quiz_id = intent.getIntExtra(ExamsFragment.QUIZ_ID, 1);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        editor.apply();
        progressBar = (ProgressBar) binding.spinKit;
        Sprite ThreeBounce1 = new Circle();
        progressBar.setIndeterminateDrawable(ThreeBounce1);
        binding.examsRvQuestion.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.examsRvQuestion.setHasFixedSize(true);
        postViewModel.getQuizDetails(token, String.valueOf(quiz_id));
        postViewModel.MldParentQuizDetails.observe(exams.this, new Observer<ParentQuizDetails>() {
            @Override
            public void onChanged(ParentQuizDetails parentQuizDetails) {
                if (parentQuizDetails.getStatus()) {
                    if (parentQuizDetails.getQuiz().getQuestions().size() != 0) {
                        quizDetailsAdapter = new QuizDetailsAdapter(parentQuizDetails.getQuiz().getQuestions(), getApplicationContext());
                        quizDetailsAdapter.setDeleteListener(new QuizDetailsAdapter.OnClickDeleteListener() {
                            @Override
                            public void onClick(Question question) {
                                postViewModel.deleteQuestion(token, String.valueOf(question.getId()));
                                postViewModel.MldParent.observe(exams.this, new Observer<Parent>() {
                                    @Override
                                    public void onChanged(Parent parent) {
                                        Toast.makeText(exams.this, parent.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                        quizDetailsAdapter.setUpdateListener(new QuizDetailsAdapter.OnClickUpdateListener() {
                            @Override
                            public void onClick(Question question, String q, String a1, String a2, String a3, String a4) {
                                //Toast.makeText(exams.this, "q: " + q, Toast.LENGTH_SHORT).show();
                                String answer0 = a1 + "," + a2 + "," + a3 + "," + a4;
                                postViewModel.updateQuestion(token, String.valueOf(question.getId()), q, answer0);
                                postViewModel.MldPParentQuestion.observe(exams.this, new Observer<ParentQuestion>() {
                                    @Override
                                    public void onChanged(ParentQuestion parentQuestion) {
                                        Toast.makeText(exams.this, parentQuestion.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
                        binding.examsRvQuestion.setAdapter(quizDetailsAdapter);
                        binding.examsTvDate.setText(parentQuizDetails.getQuiz().getCreatedAt().substring(1, 10));
                        binding.examsTvNoQuestion.setText(String.valueOf(parentQuizDetails.getQuiz().getQuestions().size() + " Qu"));
                        binding.examsTvTime.setText(String.valueOf(parentQuizDetails.getQuiz().getTime()));
                    } else
                        Toast.makeText(exams.this, "No Question", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(exams.this, parentQuizDetails.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.examsBtnEditTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = binding.examsTvTime.getText().toString();
                if (binding.examsTvTime.getText().toString().trim().equals("")) {
                    binding.examsTvTime.setError("Please Enter time");
                    binding.examsTvTime.requestFocus();
                    return;
                }
                postViewModel.changeQuizeTime(token, String.valueOf(quiz_id), time);
                postViewModel.changeQuizeTime.observe(exams.this, new Observer<Parent>() {
                    @Override
                    public void onChanged(Parent parent) {
                        Toast.makeText(exams.this, parent.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.examsBtnAddOtherQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(exams.this, R.style.BottomSheetDialogThem);
                View bottomSheetView = LayoutInflater.from(exams.this).inflate(R.layout.bottom_add_new_q, (LinearLayout) view.findViewById(R.id.bottom_sheet_container));
                et_question = bottomSheetView.findViewById(R.id.bottom_add_new_question_et_question);
                et_answer1 = bottomSheetView.findViewById(R.id.bottom_add_new_question_et_answer1);
                et_answer2 = bottomSheetView.findViewById(R.id.bottom_add_new_question_et_answer2);
                et_answer3 = bottomSheetView.findViewById(R.id.bottom_add_new_question_et_answer3);
                et_answer4 = bottomSheetView.findViewById(R.id.bottom_add_new_question_et_answer4);
                bottomSheetView.findViewById(R.id.bottom_add_new_question_btn_save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        question = et_question.getText().toString();
                        answer1 = et_answer1.getText().toString();
                        answer2 = et_answer2.getText().toString();

                        if (TextUtils.isEmpty(question) || et_question.getText().toString().trim().equals("")) {
                            et_question.setError("Pleas Enter Question");
                            et_question.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(answer1) || et_answer1.getText().toString().trim().equals("")) {
                            et_answer1.setError("Pleas Enter First Answer");
                            et_answer1.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(answer2) || et_answer2.getText().toString().trim().equals("")) {
                            et_answer2.setError("Pleas Enter Second Answer");
                            et_answer2.requestFocus();
                        } else {
                            answer3 = et_answer3.getText().toString();
                            answer4 = et_answer4.getText().toString();
                            answer = answer1 + "," + answer2 + "," + answer3 + "," + answer4;
                            postViewModel.enterQuizQuestions(token, String.valueOf(quiz_id), question, answer);
                            postViewModel.MldStudentQuizQuestions.observe(exams.this, new Observer<ParentQuestion>() {
                                @Override
                                public void onChanged(ParentQuestion parentQuestion) {
                                    if (parentQuestion.getStatus()) {
                                        Toast.makeText(exams.this, parentQuestion.getMessage(), Toast.LENGTH_SHORT).show();
                                        bottomSheetDialog.dismiss();
                                    }
                                }
                            });
                        }

                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

            }
        });


    }

    public void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.pop_menu);
        popupMenu.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item1:
                postViewModel.deleteQuiz(token, String.valueOf(quiz_id));
                postViewModel.MldParent.observe(exams.this, new Observer<Parent>() {
                    @Override
                    public void onChanged(Parent parent) {
                        Toast.makeText(exams.this, parent.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
//            case R.id.item2:
//                return true;
            default:
                return false;
        }
    }
}