package com.devsaleh.performancescale.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.devsaleh.performancescale.Model.Question;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.databinding.CustomItemQuizDetailsBinding;
import com.devsaleh.performancescale.databinding.CustomItemQuizQuestionBinding;

import java.util.ArrayList;
import java.util.List;


public class QuizQuestionsAdapter extends RecyclerView.Adapter<QuizQuestionsAdapter.QuizQuestionsHolder> {

    List<Question> questions = new ArrayList<>();
    private final Context mContext;
    private OnClickAnswer1Listener answer1Listener;
    private OnClickAnswer2Listener answer2Listener;
    private OnClickAnswer3Listener answer3Listener;
    private OnClickAnswer4Listener answer4Listener;
    private String question, answer, answer1, answer2, answer3, answer4;

    public QuizQuestionsAdapter(List<Question> questions, Context mContext) {
        this.questions = questions;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public QuizQuestionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemQuizQuestionBinding binding = CustomItemQuizQuestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QuizQuestionsHolder(binding.getRoot());
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull QuizQuestionsHolder holder, int position) {
        Question question = questions.get(position);
        holder.binding.customItemQuizQuestionsTvQuestion.setText(question.getQuestion());
        holder.binding.customItemQuizQuestionsTvFirstAnswer.setText(question.getAnswersRandomly().get(0));
        holder.binding.customItemQuizQuestionsTvSecondAnswer.setText(question.getAnswersRandomly().get(1));
        // if (question.getAnswersRandomly().size() >= 2){}
        holder.binding.customItemQuizQuestionsTvThirdAnswer.setText(question.getAnswersRandomly().get(2) + "");
        holder.binding.customItemQuizQuestionsTvForthAnswer.setText(question.getAnswersRandomly().get(3) + "");
        // holder.binding.customItemQuizQuestionsTvNumberQuizzesQuestion.setText("Q" + position + "/" + question.getQuizId());



        holder.qu = question;
        holder.pos = position;

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    public void setListStores(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    public void setAnswer1Listener(OnClickAnswer1Listener answer1Listener) {
        this.answer1Listener = answer1Listener;
        notifyDataSetChanged();
    }

    public void setAnswer2Listener(OnClickAnswer2Listener answer2Listener) {
        this.answer2Listener = answer2Listener;
        notifyDataSetChanged();
    }

    public void setAnswer3Listener(OnClickAnswer3Listener answer3Listener) {
        this.answer3Listener = answer3Listener;
        notifyDataSetChanged();
    }

    public void setAnswer4Listener(OnClickAnswer4Listener answer4Listener) {
        this.answer4Listener = answer4Listener;
        notifyDataSetChanged();
    }

    class QuizQuestionsHolder extends RecyclerView.ViewHolder {
        CustomItemQuizQuestionBinding binding;
        Question qu;
        int pos;

        public QuizQuestionsHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomItemQuizQuestionBinding.bind(itemView);
            binding.customItemQuizQuestionsTvFirstAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer1Listener.onClick(qu, pos);
                }
            });
            binding.customItemQuizQuestionsTvSecondAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer2Listener.onClick(qu, pos);
                }
            });
            binding.customItemQuizQuestionsTvThirdAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer3Listener.onClick(qu, pos);
                }
            });
            binding.customItemQuizQuestionsTvForthAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer4Listener.onClick(qu, pos);
                }
            });


        }
    }

    public interface OnClickAnswer1Listener {
        void onClick(Question question, int position);
    }

    public interface OnClickAnswer2Listener {
        void onClick(Question question, int position);
    }

    public interface OnClickAnswer3Listener {
        void onClick(Question question, int position);
    }

    public interface OnClickAnswer4Listener {
        void onClick(Question question, int position);
    }


}
