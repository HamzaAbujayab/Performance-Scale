package com.devsaleh.performancescale.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devsaleh.performancescale.Model.Student;
import com.devsaleh.performancescale.databinding.CustomItemQuizBinding;
import com.devsaleh.performancescale.databinding.CustomItemStudentBinding;

import java.util.ArrayList;
import java.util.List;


public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.ExamsHolder> {

    List<Integer> no_exams = new ArrayList<>();
    private final Context mContext;
    private OnClickItemListener quiz_listener;


    public ExamsAdapter(List<Integer> no_exams, Context mContext) {
        this.no_exams = no_exams;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ExamsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemQuizBinding binding = CustomItemQuizBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ExamsHolder(binding.getRoot());
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ExamsHolder holder, int position) {
        Integer no_exam = no_exams.get(position);
        holder.binding.customQuizTvNumber.setText(String.valueOf(position + 1));
        holder.q = no_exam;
    }

    @Override
    public int getItemCount() {
        return no_exams.size();
    }

    public void setListStores(List<Integer> no_exams) {
        this.no_exams = no_exams;
        notifyDataSetChanged();
    }

    public void setQuiz_listener(OnClickItemListener quiz_listener) {
        this.quiz_listener = quiz_listener;
        notifyDataSetChanged();
    }

    class ExamsHolder extends RecyclerView.ViewHolder {
        CustomItemQuizBinding binding;
        int q;

        public ExamsHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomItemQuizBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quiz_listener.onClick(q);
                }
            });

        }
    }


    public interface OnClickItemListener {
        void onClick(int quiz_id);
    }


}
