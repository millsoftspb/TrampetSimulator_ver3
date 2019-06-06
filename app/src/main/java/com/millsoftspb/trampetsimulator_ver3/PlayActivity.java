package com.millsoftspb.trampetsimulator_ver3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity implements View.OnTouchListener {

    static TextView textView, textView2, textView3, textTimerTick;//temp
    int tick = 0;//temp
    View mDecorView;
    private ImageView valve_1, valve_2, valve_3;
    boolean isDownValve_1, isDownValve_2, isDownValve_3 = false;
    SoundMeter soundMeter;
    private Timer myTimer;
    TrumpetModel trumpet;
    private int note;
    double amplitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //temp
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textTimerTick = findViewById(R.id.textTimerTick);

        //init trumpet;
        trumpet = new TrumpetModel(this);

        //init timer
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 100);

        //init soundmeter
        soundMeter = new SoundMeter();

        //on FullScreen mode
        mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        |View.SYSTEM_UI_FLAG_FULLSCREEN
        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //init valve view
        valve_1 = findViewById(R.id.imageValve1);
        valve_1.setOnTouchListener(this);
        valve_2 = findViewById(R.id.imageValve2);
        valve_2.setOnTouchListener(this);
        valve_3 = findViewById(R.id.imageValve3);
        valve_3.setOnTouchListener(this);
    }
    //==============================================================================================
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            //**************Valve_1*******************
            case (R.id.imageValve1): {
                if (event.getAction() == MotionEvent.ACTION_DOWN||event.getAction() == MotionEvent.ACTION_MOVE) {
                    isDownValve_1 = true;
                    valve_1.setImageResource(R.drawable.valve_png_down);
                } else {
                    isDownValve_1 = false;
                    valve_1.setImageResource(R.drawable.valve_png_up);
                }
            }
            break;
            //**************Valve_2*******************
            case (R.id.imageValve2): {
                if (event.getAction() == MotionEvent.ACTION_DOWN||event.getAction() == MotionEvent.ACTION_MOVE) {
                    isDownValve_2 = true;
                    valve_2.setImageResource(R.drawable.valve_png_down);
                } else {
                    isDownValve_2 = false;
                    valve_2.setImageResource(R.drawable.valve_png_up);
                }
            }
            break;
            //**************Valve_3*******************
            case (R.id.imageValve3): {
                if (event.getAction() == MotionEvent.ACTION_DOWN||event.getAction() == MotionEvent.ACTION_MOVE) {
                    isDownValve_3 = true;
                    valve_3.setImageResource(R.drawable.valve_png_down);
                } else {
                    isDownValve_3 = false;
                    valve_3.setImageResource(R.drawable.valve_png_up);
                }
            }
            break;
            //******************************************
        }
        return true;
    }
//==================================================================================================
    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }
    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            //temp
            tick = tick + 1;
            textTimerTick.setText(String.valueOf(tick));

            //get Mic amplitude
            amplitude = soundMeter.getAmplitude();

            if(amplitude<3000.0)trumpet.stop();

            textView.setText(String.valueOf(amplitude));//temp
            textView3.setText("");//temp

             //******D*******
             if (isDownValve_1&!isDownValve_2&isDownValve_3) {
                 note = trumpet.soundD;//  >=тТт=-
                 textView2.setText(">=тТт=-");
             }
             //******F*******
             if (isDownValve_1&!isDownValve_2&!isDownValve_3) {
                 note = trumpet.soundF;//  >=TТт=-
                 textView2.setText(">=TТт=-");
             }
             //******B*******
             if (!isDownValve_1&isDownValve_2&!isDownValve_3) {
                 note = trumpet.soundB;//  >=TтT=-
                 textView2.setText(">=TтT=-");
             }
             //******E*******
             if (isDownValve_1&isDownValve_2&!isDownValve_3) {
                 note = trumpet.soundE;//  >=Tтт=-
                 textView2.setText(">=Tтт=-");
             }
             //******A*******
             if (isDownValve_1&isDownValve_2&!isDownValve_3) {
                 note = trumpet.soundA;//  >=Tтт=-
                 textView2.setText(">=Tтт=-");
             }
             //******C*******
             if (!isDownValve_1&!isDownValve_2&!isDownValve_3) {
                 note = trumpet.soundC;//  >=TTT=-
                 textView2.setText(">=TTT=-");
             }
             if(amplitude>3000.0) trumpet.play(note);
     }

    };
    //==============================================================================================
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (trumpet != null) trumpet.destroy();
        if (soundMeter != null) soundMeter.destroy();
    }
}
