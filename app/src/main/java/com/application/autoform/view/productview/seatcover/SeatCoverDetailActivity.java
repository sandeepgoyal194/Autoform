package com.application.autoform.view.productview.seatcover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.application.autoform.R;
import com.application.autoform.view.AutoFormActivity;

/**
 * Created by sandeep.g9 on 10/19/2016.
 */

public class SeatCoverDetailActivity extends AutoFormActivity implements SeatCoverDetailFragment.SetDesignOnTitle {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_design_detail);
        int design = getIntent().getExtras().getInt("DESIGN");
        Fragment fragment = SeatCoverDetailFragment.newInstance(design);
        addFrgment(fragment, "DetailDesign", R.id.container);
        setmToolbar();
    }

    @Override
    public void setDesignName(String design) {
        setTitle(design);
    }


}
