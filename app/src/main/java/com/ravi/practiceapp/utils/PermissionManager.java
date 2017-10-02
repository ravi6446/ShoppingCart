package com.ravi.practiceapp.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by ravi on 02/10/17.
 */

public class PermissionManager {

    public static final int CALL_PERMISSION_REQUEST_CODE = 420;

    public static boolean checkPermission(String permission, Context context) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
