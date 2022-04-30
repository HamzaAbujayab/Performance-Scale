package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.devsaleh.performancescale.R;

public class Filter_H extends AppCompatActivity {
    
    RadioGroup radio_group;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_h);

        radio_group = findViewById(R.id.radio_group);



    }
    public void checkButton(View v){
        int radioId = radio_group.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(getApplicationContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}