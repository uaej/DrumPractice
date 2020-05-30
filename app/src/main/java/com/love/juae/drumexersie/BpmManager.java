package com.love.juae.drumexersie;

import android.app.Activity;

import java.util.Timer;

public class BpmManager {
    private Activity activity;
    private int bpm = 0;
    private long msecond;

    public BpmManager(Activity activity) {
        this.activity = activity;
    }

    public int up_bmp(int count) {
        bpm += count;
        msecond = 60000 / bpm;
        return bpm;
    }

    public int down_bmp(int count) {
        if (count < bpm) {
            bpm += count;
            msecond = 60000 / bpm;
            return bpm;
        } else {
            return -1;
        }
    }

    public void start_bpm() {
        if (bpm > 1) {
            bpm -= 1;
            msecond = 60000 / bpm;
        }
    }
    public void stop_bpm() {

    }
}

