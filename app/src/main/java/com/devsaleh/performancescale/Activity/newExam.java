package com.devsaleh.performancescale.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devsaleh.performancescale.Adapter.QAdapter;
import com.devsaleh.performancescale.Model.ParentQuiz;
import com.devsaleh.performancescale.Model.Q;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityNewExamBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class newExam extends AppCompatActivity {
    ActivityNewExamBinding binding;
    private String question, answer, answer1, answer2, answer3, answer4;
    ArrayList<Q> dataProductArrayList = new ArrayList<>();
    String[] questions;
    String[] answers;
    int count = 10;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String Specialization = "Scientific", user_id, token;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewExamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        editor.apply();
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite ThreeBounce1 = new Circle();
        progressBar.setIndeterminateDrawable(ThreeBounce1);

        binding.newExamRvAllQuestion.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        binding.newExamRvAllQuestion.setHasFixedSize(true);

        binding.newExamBtnAddOtherQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question = binding.newExamEtQuestion.getText().toString();
                answer1 = binding.newExamEtAnswer1.getText().toString();
                answer2 = binding.newExamEtAnswer2.getText().toString();

                if (TextUtils.isEmpty(question) || binding.newExamEtQuestion.getText().toString().trim().equals("")) {
                    binding.newExamEtQuestion.setError("Pleas Enter Question");
                    binding.newExamEtQuestion.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(answer1) || binding.newExamEtAnswer1.getText().toString().trim().equals("")) {
                    binding.newExamEtAnswer1.setError("Pleas Enter First Answer");
                    binding.newExamEtAnswer1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(answer2) || binding.newExamEtAnswer2.getText().toString().trim().equals("")) {
                    binding.newExamEtAnswer2.setError("Pleas Enter Second Answer");
                    binding.newExamEtAnswer2.requestFocus();
                } else {
                    answer3 = binding.newExamEtAnswer3.getText().toString();
                    answer4 = binding.newExamEtAnswer4.getText().toString();
                    answer = answer1 + "," + answer2 + "," + answer3 + "," + answer4;
                    dataProductArrayList.add(new Q(1, question, answer));
                    QAdapter qAdapter = new QAdapter(dataProductArrayList, getApplicationContext(), null);
                    qAdapter.setListener(new QAdapter.OnClickCancelListener() {
                        @Override
                        public void onClick(Q q) {
                            dataProductArrayList.remove(q);
                            if (qAdapter != null) {
                                qAdapter.updateData(dataProductArrayList);
                            }
                        }
                    });
                    binding.newExamRvAllQuestion.setAdapter(qAdapter);
                    binding.newExamEtQuestion.setText("");
                    binding.newExamEtAnswer1.setText("");
                    binding.newExamEtAnswer2.setText("");
                    binding.newExamEtAnswer3.setText("");
                    binding.newExamEtAnswer4.setText("");

                }
            }
        });

        binding.newExamBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.newExamBtnSave.setEnabled(false);
                questions = new String[dataProductArrayList.size()];
                answers = new String[dataProductArrayList.size()];
                if (binding.newExamRbScientific.isChecked())
                    Specialization = "Scientific";
                if (binding.newExamRbLiterary.isChecked())
                    Specialization = "Literary";
                if (binding.newExamRbIndustrial.isChecked())
                    Specialization = "Industrial";
                Map<String, String> partmap = new HashMap<>();
                partmap.put("time", String.valueOf(count));
                partmap.put("specialized", Specialization);
                for (int i = 0; i < dataProductArrayList.size(); i++) {
                    partmap.put("question[" + i + "]", (String.valueOf(dataProductArrayList.get(i).getQuestion())));
                    partmap.put("answers[" + i + "]", (dataProductArrayList.get(i).getAnswer()));
                }
                postViewModel.createNewqQuiz(token, partmap);
                postViewModel.MldParentQuiz.observe(newExam.this, new Observer<ParentQuiz>() {
                    @Override
                    public void onChanged(ParentQuiz parentQuiz) {
                        if (parentQuiz.getStatus()) {
                            progressBar.setVisibility(View.GONE);
                            binding.newExamBtnSave.setEnabled(true);
                            Toast.makeText(newExam.this, parentQuiz.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            binding.newExamBtnSave.setEnabled(true);
                            Toast.makeText(newExam.this, parentQuiz.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        // time
        binding.newExamIvPlush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                binding.newExamTvTime.setText(String.valueOf(count));
            }
        });
        binding.newExamIvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 1)
                    return;
                count--;
                binding.newExamTvTime.setText(String.valueOf(count));
            }
        });

    }

    @NonNull
    private RequestBody createPartFromString(String description) {
        return RequestBody.create(MultipartBody.FORM, description);
    }

}