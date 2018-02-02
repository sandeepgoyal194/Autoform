package com.application.autoform.view.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.application.autoform.R;
import com.application.autoform.model.bean.WhyUsPageData;
import com.application.autoform.presenter.products.whyus.IWhyUsPresenter;
import com.application.autoform.presenter.products.whyus.WhyUsPresenterImpl;
import com.application.autoform.view.AutoFormActivity;
import com.application.autoform.view.AutoFormWebViewFragment;
import com.application.autoform.view.ContactUsFragment;
import com.application.autoform.view.dealers.DealerContactFragment;
import com.application.autoform.view.productview.seatcover.SeatCoverDetailFragment;
import com.application.autoform.view.productview.seatcover.SeatCoverRecommendationFragment;
import com.application.autoform.view.whyus.AutoFormWhyUs;
import com.application.autoform.view.whyus.IWhyUsView;
import com.application.autoform.view.whyus.WhyUsDialog;

import java.util.List;

/**
 * Created by sandeep.g9 on 10/18/2016.
 */

public class AutoFormMainActivity extends AutoFormActivity implements SeatCoverRecommendationFragment.RemoveFragmentCommand, SeatCoverDetailFragment.SetDesignOnTitle, IWhyUsView {


    protected ActionBarDrawerToggle mActionBarDrawerToggle;
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    NavigationView.OnNavigationItemSelectedListener mNavigationListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            mDrawerLayout.closeDrawers();
            if (menuItem.getItemId() == R.id.recommendation) {
                addFrgment(SeatCoverRecommendationFragment.newInstance(), "RECOMMENDATION_FRAGEMENT", R.id.container_view);
            } else if (menuItem.getItemId() == R.id.products) {
                addFrgment(AutoFormAccessoriesFragment.newInstance(), "CHOOSE_ACCESSORIES", R.id.container_view);
            } else if (menuItem.getItemId() == R.id.dealers) {
                addFrgment(DealerContactFragment.newInstance(), "DEALER_LOCATION_FRAGMENT", R.id.container_view);
            } else if (menuItem.getItemId() == R.id.why_autoform) {
                addFrgment(AutoFormWhyUs.newInstance(), "WHY_AUTOFORM_FRAGMENT", R.id.container_view);
            } else if (menuItem.getItemId() == R.id.about_us) {
                addFrgment(AutoFormWebViewFragment.newInstance("/JSP/AboutUs.html", getResources().getString(R.string.about_us)), "ABOUT_US_FRAGMENT", R.id.container_view);
            } else if (menuItem.getItemId() == R.id.contact_us) {
                addFrgment(ContactUsFragment.newInstance(), "CONTACT_US_FRAGMENT", R.id.container_view);
            } else if (menuItem.getItemId() == R.id.poem) {
                addFrgment(AutoFormWebViewFragment.newInstance("/JSP/Poem.jsp", getResources().getString(R.string.autoform_jingle)), "POEM_FRAGMENT", R.id.container_view);
            }
            setTitle(getResources().getString(R.string.app_name));
            return true;
        }

    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setmToolbar();
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView.setNavigationItemSelectedListener(mNavigationListener);
        addFrgment(AutoFormAccessoriesFragment.newInstance(), "CHOOSE_ACCESSORIES", R.id.container_view);
        presenter = new WhyUsPresenterImpl(this);
        presenter.onViewStarted();
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                handleActionBarDrawerTogglecState();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                handleActionBarDrawerTogglecState();
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        handleActionBarDrawerTogglecState();
        mActionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
                handleActionBarDrawerTogglecState();
            }
        });

    }

    IWhyUsPresenter presenter = null;


    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewStopped();
    }

    @Override
    public void removeFragment(Fragment fragment) {
        popFragment(fragment);
    }

    public void handleActionBarDrawerTogglecState() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mActionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.back_arrow);
        } else {
            mActionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.navigation_menu);

        }
    }

    public void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            mDrawerLayout.openDrawer(Gravity.LEFT);

        }
    }

    @Override
    public void setDesignName(String design) {
        setTitle(design);
    }

    @Override
    public void loadingData() {

    }

    @Override
    public void loadingComplete() {

    }

    @Override
    public void setWhyUsData(List<WhyUsPageData> whyUsData) {
        if (whyUsData != null && whyUsData.size() > 0)
            showWhyUsDialog(findTopPriorityData(whyUsData));
    }

    WhyUsDialog dialog = null;

    private void showWhyUsDialog(WhyUsPageData whyUsPageData) {
        dialog = WhyUsDialog.newInstance(whyUsPageData);
        dialog.show(getSupportFragmentManager(), "whyUsData");
    }


    @Override
    public void onLoadingFail() {

    }

    private WhyUsPageData findTopPriorityData(List<WhyUsPageData> whyUsData) {
        WhyUsPageData whyUsPageData = null;
        if (whyUsData != null) {
            for (WhyUsPageData whyUsPageData1 : whyUsData) {
                if (whyUsPageData == null || whyUsPageData1.getPriority() < whyUsPageData.getPriority()) {
                    whyUsPageData = whyUsPageData1;
                }
            }
        }
        return whyUsPageData;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
}
