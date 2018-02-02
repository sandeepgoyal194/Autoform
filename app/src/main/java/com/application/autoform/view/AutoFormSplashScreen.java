package com.application.autoform.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;
import com.application.autoform.R;
import com.application.autoform.model.bean.WhyUsPageData;
import com.application.autoform.presenter.products.seatcovers.SubCategoryPresenterImpl;
import com.application.autoform.presenter.products.whyus.IWhyUsPresenter;
import com.application.autoform.presenter.products.whyus.WhyUsPresenterImpl;
import com.application.autoform.view.dashboard.AutoFormMainActivity;
import com.application.autoform.view.whyus.IWhyUsView;

import java.util.List;

public class AutoFormSplashScreen extends Activity implements IWhyUsView {

    /**
     * Splash screen timer
     */

    private static int SPLASH_MIN_TIME_OUT = 3000;
    private static int SPLASH_MAX_TIME_OUT = 5000;
    private boolean isDataLoaded = false;
    private boolean isMinTimeCompleted = false;
    IWhyUsPresenter mPrsenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        new SubCategoryPresenterImpl(null).onViewFocus();
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                /**
                 * This method will be executed once the timer is over
                 * Start your app main activity
                 */
                Log.e("Autoform","Minimum Time Complete");
                if (isDataLoaded) {
                    mHandler.removeMessages(START_ACTIVITY);
                    startMainActivity();
                } else {
                    isMinTimeCompleted = true;
                    mHandler.sendEmptyMessageDelayed(START_ACTIVITY, SPLASH_MAX_TIME_OUT - SPLASH_MIN_TIME_OUT);
                }

                /**
                 * close this activity
                 */


            }
        }, SPLASH_MIN_TIME_OUT);


    }

    static final int START_ACTIVITY = 1;
    H mHandler = new H();

    class H extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START_ACTIVITY:
                    Log.e("Autoform","Handler start activity");
                    startMainActivity();
                    // finish();
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPrsenter = new WhyUsPresenterImpl(this);
        mPrsenter.onViewStarted();
    }

    @Override
    public void loadingData() {

    }

    @Override
    public void loadingComplete() {
        Log.e("Autoform","Loading Complete");
        if (isMinTimeCompleted) {
            mHandler.removeMessages(START_ACTIVITY);

        } else {
            isDataLoaded = true;
        }
    }

    public void startMainActivity() {
        Log.e("Autoform","Start activity");
        Intent i = new Intent(AutoFormSplashScreen.this, AutoFormMainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void setWhyUsData(List<WhyUsPageData> whyUsData) {
    }



    @Override
    public void onLoadingFail() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        mPrsenter.onViewStopped();
    }
}
