package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.devsaleh.performancescale.R;

public class MathsGroup_H extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_group_h);

        ImageView send_message = findViewById(R.id.send_message);
        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SendMessage_H.class);
                startActivity(intent);
            }
        });

        TextView tv_add_commends_square = findViewById(R.id.tv_add_commends_square);
        tv_add_commends_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Comments_H.class);
                startActivity(intent);
            }
        });
    }
}