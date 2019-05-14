package com.millsoftspb.trampetsimulator_ver3;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class TrumpetModel {
    private final int noteA = 1, noteB = 2, noteC = 3, noteD = 4, noteE = 5, noteF = 6, noteG = 7;
    SoundPool soundPool;
    int soundA, soundB, soundC, soundD, soundE, soundF, soundG;
    float volume;


    TrumpetModel(Context context) {
        Context myContext;
        myContext = context;
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(audioAttributes)
                .build();
        soundA = soundPool.load(myContext, R.raw.a4, 1);
        soundB = soundPool.load(myContext, R.raw.b4, 1);
        soundC = soundPool.load(myContext, R.raw.c4, 1);
        soundD = soundPool.load(myContext, R.raw.d4, 1);
        soundE = soundPool.load(myContext, R.raw.e4, 1);
        soundF = soundPool.load(myContext, R.raw.f4, 1);
        soundG = soundPool.load(myContext, R.raw.g4, 1);
    }

    public void play(int note) {

        switch (note) {
            case noteA:
                if (volume <= 0)
                    soundPool.stop(soundA);
                else {
                    soundPool.play(soundA, volume, volume, 0, 0, 1);
                }
                break;

            case noteB:
                if (volume <= 0)
                    soundPool.stop(soundB);
                else {
                    soundPool.play(soundB, volume, volume, 0, 0, 1);
                }
                break;

            case noteC:
                if (volume <= 0)
                    soundPool.stop(soundC);
                else {
                    soundPool.play(soundC, volume, volume, 0, 0, 1);
                }
                break;

            case noteD:
                if (volume <= 0) {
                    soundPool.stop(soundD);
                } else {
                    soundPool.play(soundD, volume, volume, 0, 0, 1);
                }
                break;

            case noteE:
                if (volume <= 0)
                    soundPool.stop(soundE);
                else {
                    soundPool.play(soundE, volume, volume, 0, 0, 1);
                }
                break;

            case noteF:
                if (volume <= 0)
                    soundPool.stop(soundF);
                else {
                    soundPool.play(soundF, volume, volume, 0, 0, 1);
                }
                break;

            case noteG:
                if (volume <= 0)
                    soundPool.stop(soundG);
                else {
                    soundPool.play(soundG, volume, volume, 0, 0, 1);
                }
                break;
        }
    }

    void destroyTrumpet() {
        soundPool.release();
        soundPool = null;
    }
}
