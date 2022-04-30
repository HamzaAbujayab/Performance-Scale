package com.devsaleh.performancescale.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.devsaleh.performancescale.Activity.MathsGroup_H;
import com.devsaleh.performancescale.Model.SelectSubject;
import com.devsaleh.performancescale.R;

import java.util.ArrayList;
import java.util.List;

public class SelectSubjectAdapter extends RecyclerView.Adapter<SelectSubjectAdapter.SelectSubjectHolder> {

    private Context mcontext;
    private List<SelectSubject> selectSubjectList;

    public SelectSubjectAdapter(Context mcontext, List<SelectSubject> selectSubjectList) {
        this.mcontext = mcontext;
        this.selectSubjectList = selectSubjectList;
    }

    @NonNull
    @Override
    public SelectSubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_select_subject, parent, false);
        return new SelectSubjectAdapter.SelectSubjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSubjectHolder holder, int position) {

        SelectSubject selectSubject = selectSubjectList.get(position);
        holder.subjectName.setText(selectSubject.getSubjectName());
        holder.subjectGroupNumber.setText(selectSubject.getSubjectGroupNumber());
        holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext , MathsGroup_H.class);
                //intent.putExtra("id" , selectSubject.getIdNumber());
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return selectSubjectList.size();
    }


    public static class SelectSubjectHolder extends RecyclerView.ViewHolder {

        ImageView studentImage;
        TextView subjectName;
        TextView subjectGroupNumber;
        ConstraintLayout click_layout;
        public SelectSubjectHolder(@NonNull View itemView) {
            super(itemView);
            studentImage = itemView.findViewById(R.id.custom_img_select_subject);
            subjectName = itemView.findViewById(R.id.custom_tv_subject_name);
            subjectGroupNumber = itemView.findViewById(R.id.custom_tv_subject_group_number);
            click_layout = itemView.findViewById(R.id.click_layout);
        }
    }
}
