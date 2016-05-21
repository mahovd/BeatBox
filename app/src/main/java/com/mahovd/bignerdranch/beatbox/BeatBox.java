package com.mahovd.bignerdranch.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
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
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds;
    private SoundPool mSoundPool;

    //BeatBox constructor
    public BeatBox(Context context) {
        mAssets = context.getAssets();

        //This old constructor is deprecated, but we need it for compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);

        loadSounds();
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId==null){
            return;
        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }

    public void release(){
        mSoundPool.release();
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


        mSounds = new ArrayList<>();
        for (String filename : soundNames){
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            }
            catch (IOException ioe){
                Log.e(TAG,"Couldn't load sound "+filename,ioe);
            }
        }
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds(){
        return mSounds;
    }

}
