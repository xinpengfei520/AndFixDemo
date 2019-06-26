package com.xpf.android.andfixdemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * Created by x-sir on 2019-06-25 :)
 * Function:
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private static final String APATCH_PATH = "/fix.apatch";
    private static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mPatchManager = new PatchManager(this);
        // current app version
        mPatchManager.init("1.0");
        // Load patch
        mPatchManager.loadPatch();

        addPatch();
    }

    private void addPatch() {
        try {
            String patchFileStringPath = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
            // /storage/emulated/0/fix.apatch
            Log.i(TAG, "patchFileStringPath:" + patchFileStringPath);
            //path of the patch file that was downloaded
            mPatchManager.addPatch(APATCH_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "error:" + e.getMessage());
        }
    }

    public PatchManager getPatchManager() {
        return mPatchManager;
    }

}
