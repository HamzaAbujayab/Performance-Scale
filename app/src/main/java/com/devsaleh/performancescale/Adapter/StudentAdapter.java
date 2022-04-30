package com.devsaleh.performancescale.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devsaleh.performancescale.Model.Student;
import com.devsaleh.performancescale.databinding.CustomItemStudentBinding;

import java.util.ArrayList;
import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    List<Student> students = new ArrayList<>();
    private final Context mContext;
    private OnClickItemListener student_listener;
    private OnClickImageListener mark_listener;

    public StudentAdapter(List<Student> students, Context mContext) {
        this.students = students;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemStudentBinding binding = CustomItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StudentHolder(binding.getRoot());
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = students.get(position);
        holder.binding.customTvStudentName.setText(student.getName());
        holder.binding.customTvStudentScale.setText(String.valueOf(student.getAverage()) + " %");
        holder.binding.customTvStudentSpecialization.setText(student.getSpecialized());
        holder.stu = student;

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setListStores(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public void setStudent_listener(OnClickItemListener student_listener) {
        this.student_listener = student_listener;
        notifyDataSetChanged();
    }

    public void setMark_listener(OnClickImageListener mark_listener) {
        this.mark_listener = mark_listener;
        notifyDataSetChanged();
    }

    class StudentHolder extends RecyclerView.ViewHolder {
        CustomItemStudentBinding binding;
        Student stu;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomItemStudentBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    student_listener.onClick(stu);
                }
            });
            binding.djthdtj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mark_listener.onClick(stu);
                }
            });

        }
    }

    public interface OnClickItemListener {
        void onClick(Student student);
    }

    public interface OnClickImageListener {
        void onClick(Student student);
    }


}
