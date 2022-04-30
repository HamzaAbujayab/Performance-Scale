package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.devsaleh.performancescale.R;

public class AllSubjects_H extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_subjects_h);

        TextView tv_all_subject = findViewById(R.id.tv_all_subject);
        tv_all_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Filter_H.class);
                startActivity(intent);
            }
        });
    }
}