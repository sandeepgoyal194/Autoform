package com.application.autoform.view.productview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.application.autoform.ApplicationConfiguration;
import com.application.autoform.R;
import com.application.autoform.view.AutoFormActivity;
import com.application.autoform.view.productview.seatcover.SeatCoverFullListActivity;

/**
 * Created by sandeep.g9 on 3/10/2017.
 */

public class AutoFromSeatCoverAdvertiserActivity extends AutoFormActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ApplicationConfiguration.getInstance().getmShouldShowAdvertiserPage()) {
            finish();
            //start next activity
            Intent i = new Intent(this, SeatCoverFullListActivity.class);
            startActivity(i);
        }
        setContentView(R.layout.activity_seat_cover_advertiser);
        setmToolbar();
    }
}
