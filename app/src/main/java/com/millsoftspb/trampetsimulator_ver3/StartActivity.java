package com.millsoftspb.trampetsimulator_ver3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {
    View mDecorViewStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //on FullScreen mode
        mDecorViewStart = getWindow().getDecorView();
        mDecorViewStart.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    public void startPlay(View view) {
        Intent playActivity = new Intent(this, PlayActivity.class);
        startActivity(playActivity);
    }
}
