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

    //declaration
    static TextView textView;//temp
    View mDecorView;
    boolean isDownValve_1, isDownValve_2, isDownValve_3 = false;
    SoundMeter soundMeter;
    TrumpetModel trumpet;
    double amplitude;
    private ImageView valve_1, valve_2, valve_3;
    private Timer myTimer;
    private int note;
    //==================================================================================================
    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            //get Mic amplitude
            amplitude = soundMeter.getAmplitude();

            if (amplitude <= 800.0) trumpet.stop();

            textView.setText(String.valueOf(amplitude));//temp

            if (amplitude > 800.0) trumpet.play(note);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //temp
        textView = findViewById(R.id.textView);

        //init trumpet;
        trumpet = new TrumpetModel(this);
        note = TrumpetModel.soundC;

        //init timer
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 500);

        //init soundmeter
        soundMeter = new SoundMeter();

        //on FullScreen mode
        mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

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
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    isDownValve_1 = true;
                    valve_1.setImageResource(R.drawable.valve_png_down);
                } else {
                    isDownValve_1 = false;
                    valve_1.setImageResource(R.drawable.valve_png_up);
                }
            }
            note = NoteCalculation.calculate(isDownValve_1, isDownValve_2, isDownValve_3, 0);
            break;

            //**************Valve_2*******************
            case (R.id.imageValve2): {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    isDownValve_2 = true;
                    valve_2.setImageResource(R.drawable.valve_png_down);
                } else {
                    isDownValve_2 = false;
                    valve_2.setImageResource(R.drawable.valve_png_up);
                }
            }
            note = NoteCalculation.calculate(isDownValve_1, isDownValve_2, isDownValve_3, 0);
            break;

            //**************Valve_3*******************
            case (R.id.imageValve3): {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    isDownValve_3 = true;
                    valve_3.setImageResource(R.drawable.valve_png_down);
                } else {
                    isDownValve_3 = false;
                    valve_3.setImageResource(R.drawable.valve_png_up);
                }
            }
            note = NoteCalculation.calculate(isDownValve_1, isDownValve_2, isDownValve_3, 0);
            break;

        }
        return true;
    }

    //==================================================================================================
    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    //==============================================================================================
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (trumpet != null) trumpet.destroy();
        if (soundMeter != null) soundMeter.destroy();
    }
}
