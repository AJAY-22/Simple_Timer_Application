package com.example.simpletimerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
{
    TextView tvClockText;
    Button startPauseBtn;
    Button resetBtn;

    CountDownTimer timer;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    boolean is_timer_running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvClockText = findViewById(R.id.tvClockText);
        startPauseBtn = findViewById(R.id.btnStartPause);
        resetBtn = findViewById(R.id.btnReset);

        UpdateTimerText();

        timer = new CountDownTimer(60000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinish)
            {
                seconds++;

                if (seconds == 60)
                {
                    seconds = 0;
                    minutes++;
                }

                UpdateTimerText();
            }

            @Override
            public void onFinish()
            {
                timer.start();
            }

        };

        if (minutes == 60)
        {
            minutes = 0;
            hours++;
        }



        startPauseBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                is_timer_running = !is_timer_running;

                if (is_timer_running)
                {
                    startPauseBtn.setText("pause");
                    timer.start();
                }
                else
                {
                    startPauseBtn.setText("start");
                    timer.cancel();
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                timer.cancel();
                is_timer_running = false;
                startPauseBtn.setText("start");
                seconds = minutes = hours = 0;
                UpdateTimerText();
            }
        });

    }

    private void UpdateTimerText()
    {
        String time = String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds);
        tvClockText.setText(time);
    }

    void PauseTimer()
    {
        timer.cancel();
    }

    void ResetTimer()
    {
        timer.cancel();
        seconds = minutes = hours = 0;
    }
}
