package com.application.autoform.utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public class Messaging {
    Context mContext;

    public Messaging(Context mContext) {
        this.mContext = mContext;
    }

    public void messageOnNo(String phoneNo) {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
//                            smsIntent.putExtra("sms_body", "sms message goes here");
        mContext.startActivity(smsIntent);
    }
}
