package com.mahovd.bignerdranch.beatbox;

import android.support.v4.app.Fragment;

public class BeatBoxActivity extends SingleFragmentActivity {

    //Kicks-off the main inner process
    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
