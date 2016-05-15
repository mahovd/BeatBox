package com.mahovd.bignerdranch.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahovd on 15/05/16.
 * Controller-class
 */
public class BeatBox {

    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    //BeatBox constructor
    public BeatBox(Context context) {
        mAssets = context.getAssets();
        loadSounds();
    }

    //Loads all objects from an asset to an array
    private void loadSounds(){
        String[] soundNames = new String[0];

        try{
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG,"Found "+soundNames.length+" sounds");
        }
        catch (IOException ioe){
            Log.e(TAG,"Couldn't list assets",ioe);
        }

        for (String filename : soundNames){
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }
    }

    public List<Sound> getSounds(){
        return mSounds;
    }

}
