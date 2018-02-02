package com.application.autoform.utility;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public class Call {
    Context mContext;

    public Call(Context mContext) {
        this.mContext = mContext;
    }

    public void call(String phonNO) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phonNO));
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(mContext,"Dont have call permissions",Toast.LENGTH_LONG).show();
                return;
            }
            mContext.startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Toast.makeText(mContext, "There are no Call clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
