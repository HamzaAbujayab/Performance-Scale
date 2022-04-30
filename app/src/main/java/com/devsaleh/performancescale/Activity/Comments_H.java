package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.devsaleh.performancescale.R;

public class Comments_H extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_h);

        //Uri uri = Uri.parse("/ui/wp-content/uploads/2016/04/videoviewtestingvideo.mp4");
        // https://youtu.be/64643Op6WJo
        VideoView videoView = (VideoView) findViewById(R.id.simpleVideoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.math_explanation));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        //videoView.start();

        TextView tv_right_comment = findViewById(R.id.tv_right_comment);
        tv_right_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllSubjects_H.class);
                startActivity(intent);
            }
        });
    }
}