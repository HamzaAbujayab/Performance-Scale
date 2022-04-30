package com.devsaleh.performancescale.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devsaleh.performancescale.Model.Question;
import com.devsaleh.performancescale.Model.Student;
import com.devsaleh.performancescale.databinding.CustomItemQuizDetailsBinding;
import com.devsaleh.performancescale.databinding.CustomItemStudentBinding;

import java.util.ArrayList;
import java.util.List;


public class QuizDetailsAdapter extends RecyclerView.Adapter<QuizDetailsAdapter.StudentHolder> {

    List<Question> questions = new ArrayList<>();
    private final Context mContext;
    private OnClickDeleteListener deleteListener;
    private OnClickUpdateListener updateListener;
    private String question, answer, answer1, answer2, answer3, answer4;

    public QuizDetailsAdapter(List<Question> questions, Context mContext) {
        this.questions = questions;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemQuizDetailsBinding binding = CustomItemQuizDetailsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StudentHolder(binding.getRoot());
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Question question = questions.get(position);
        holder.binding.customItemQuizDetailsEtQuestion.setText(question.getQuestion());
        holder.binding.customItemQuizDetailsEtAnswer1.setText(question.getAnswer1());
        holder.binding.customItemQuizDetailsEtAnswer2.setText(question.getAnswer2());
        holder.binding.customItemQuizDetailsEtAnswer3.setText(question.getAnswer3());
        holder.binding.customItemQuizDetailsEtAnswer4.setText(question.getAnswer4());
        holder.binding.customItemQuizDetailsTvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.binding.customItemQuizDetailsLl.getVisibility() != View.VISIBLE)
                    holder.binding.customItemQuizDetailsLl.setVisibility(View.VISIBLE);
                else
                    holder.binding.customItemQuizDetailsLl.setVisibility(View.GONE);
            }
        });
        holder.qu = question;

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setListStores(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    public void setDeleteListener(OnClickDeleteListener deleteListener) {
        this.deleteListener = deleteListener;
        notifyDataSetChanged();
    }

    public void setUpdateListener(OnClickUpdateListener updateListener) {
        this.updateListener = updateListener;
        notifyDataSetChanged();
    }

    class StudentHolder extends RecyclerView.ViewHolder {
        CustomItemQuizDetailsBinding binding;
        Question qu;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomItemQuizDetailsBinding.bind(itemView);
            binding.customItemQuizDetailsBtnDeleteQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteListener.onClick(qu);
                }
            });
            binding.customItemQuizDetailsBtnUpdateQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateListener.onClick(qu,binding.customItemQuizDetailsEtQuestion.getText().toString(),
                            binding.customItemQuizDetailsEtAnswer1.getText().toString(),
                            binding.customItemQuizDetailsEtAnswer2.getText().toString(),
                            binding.customItemQuizDetailsEtAnswer3.getText().toString(),
                            binding.customItemQuizDetailsEtAnswer4.getText().toString());
                }
            });


        }
    }

    public interface OnClickDeleteListener {
        void onClick(Question question);
    }

    public interface OnClickUpdateListener {
        void onClick(Question question, String q,String a1,String a2,String a3,String a4);
    }


}
