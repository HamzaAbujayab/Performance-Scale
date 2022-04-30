package com.devsaleh.performancescale.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devsaleh.performancescale.Model.Q;
import com.devsaleh.performancescale.databinding.CstomAddQBinding;

import java.util.ArrayList;
import java.util.List;

public class QAdapter extends RecyclerView.Adapter<QAdapter.QHolder> {

    private List<Q> qList;
    private Context mContext;
    private OnClickCancelListener listener;

    public QAdapter(List<Q> qList, Context mContext, OnClickCancelListener listener) {
        this.qList = qList;
        this.mContext = mContext;
        this.listener = listener;
    }

    public void updateData(ArrayList<Q> qList) {
        this.qList = qList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public QHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CstomAddQBinding binding = CstomAddQBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull QHolder holder, int position) {
        Q q = qList.get(position);
        holder.bind(q);
    }

    @Override
    public int getItemCount() {
        return qList.size();
    }

    public OnClickCancelListener getListener() {
        return listener;
    }

    public void setListener(OnClickCancelListener listener) {
        this.listener = listener;
    }


    class QHolder extends RecyclerView.ViewHolder {
        CstomAddQBinding binding;
        Q q1;

        public QHolder(@NonNull View itemView) {
            super(itemView);
            binding = CstomAddQBinding.bind(itemView);
            binding.customAddQIvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(q1);
                }
            });
        }

        public void bind(Q q) {
            binding.customAddQTvQuestion.setText(q.getQuestion());
            binding.customAddQTvAnswer.setText(q.getAnswer());
            this.q1 = q;
        }
    }


    public interface OnClickCancelListener {
        void onClick(Q q);
    }
}
