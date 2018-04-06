package com.example.vivek.aurora2k18;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.util.TimerTask;

/**
 * Created by Vivek on 06/04/2018.
 */

public class splash_screen extends AppCompatActivity{
    TextView textView1,textView2;
    Object lock = new Object();
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splash_layout);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        final String stringer[] = {"A","U","R","O","R","A"};
        final Runnable runnable = new Runnable() {
            int k=0;
            String stringman = new String();
            @Override
            public void run() {
                textView2.setText(stringer[k]);
                stringman = stringman + stringer[k++];
                textView1.setText(stringman);
                if(k==6) textView2.setText("");
            }
        };
        Thread thread =  new Thread(){
            @Override
            public void run() {
                super.run();
                int c=0;
                while (c++!=6){
                    runOnUiThread(runnable);
                    synchronized (lock){
                        try {
                            lock.wait(900);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Intent intent = new Intent(splash_screen.this, MainActivity.class);
                startActivity(intent);

            }
        };

        thread.start();
        finish();
    }
}
