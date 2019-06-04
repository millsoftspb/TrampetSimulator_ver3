package com.millsoftspb.trampetsimulator_ver3;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaMetadataRetriever;
import android.media.SoundPool;
import android.net.Uri;

public class TrumpetModel {

    public   int soundA, soundB, soundC, soundD, soundE, soundF, soundG;
    private int currentSound = 0;
    private SoundPool sounds;
    public long millSecond;


    //constructor trumpet
    public TrumpetModel(Context myContext) {

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sounds = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

        //filling the soundpool filling with sounds
        soundA = sounds.load(myContext, R.raw.a4, 1);
        soundB = sounds.load(myContext, R.raw.b4, 1);
        soundC = sounds.load(myContext, R.raw.c4, 1);
        soundD = sounds.load(myContext, R.raw.d4, 1);
        soundE = sounds.load(myContext, R.raw.e4, 1);
        soundF = sounds.load(myContext, R.raw.f4, 1);
        soundG = sounds.load(myContext, R.raw.g4, 1);

    }

    //start play
    public void play(int sound){
         if (currentSound!=sound || currentSound == 0) {
             sounds.stop(currentSound);

             sounds.play(sound, 1, 1, 0, -1, 1);

             currentSound = sound;
         }
    }

    //stop play
    public void stop(){
        if (currentSound!=0) sounds.autoPause();
    }

    //destroy trumpet
    public void destroy(){
        if (sounds != null) sounds.release();
        sounds = null;
    }

}
