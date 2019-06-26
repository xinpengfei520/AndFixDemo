package com.xpf.android.andfixdemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.xpf.android.andfixdemo.utils.AppUtils;

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
        mPatchManager.init(AppUtils.getVersionName(this));
        // Load patch
        mPatchManager.loadPatch();

        //addPatch();
    }

    public static void addPatch() {
        try {
            String patchFileStringPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + APATCH_PATH;
            // /storage/emulated/0/fix.apatch
            Log.i(TAG, "patchFileStringPath:" + patchFileStringPath);
            // path of the patch file that was downloaded
            mPatchManager.addPatch(patchFileStringPath);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "error:" + e.getMessage());
        }
    }

    public PatchManager getPatchManager() {
        return mPatchManager;
    }

}
