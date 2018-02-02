package com.application.autoform.utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public class Email {
    Context mContext;

    public Email(Context mContext) {
        this.mContext = mContext;
    }

    public void openEmailForId(String emailId) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{emailId, "saur.pr@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT, "body of email");
        try {
            mContext.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
