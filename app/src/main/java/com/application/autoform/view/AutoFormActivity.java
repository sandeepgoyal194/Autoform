package com.application.autoform.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.application.autoform.R;
import com.application.autoform.view.cart.CartActivity;

/**
 * Created by sandeep.g9 on 10/25/2016.
 */

public abstract class AutoFormActivity extends AppCompatActivity {

    public static final String TAG = "AutoForm";
    protected Fragment mAddedFragment;
    protected ActionBarDrawerToggle mActionBarDrawerToggle;
    protected Toolbar mToolbar;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setmToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_arrow));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setmToolbarColor(int color) {
        if(mToolbar != null) {
            mToolbar.setBackgroundColor(color);
        }
    }

    protected void addFrgment(Fragment fragment, String fragmentTag, int containerView) {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction mOwnerTransaction = mFragmentManager.beginTransaction();
        Fragment fragment1 = mFragmentManager.findFragmentByTag(fragmentTag);
        if (fragment1 != null) {
            popFragment(fragment1);
            fragment = fragment1;
        }
        if (!mFragmentManager.popBackStackImmediate(fragment.getClass().getName(), 0)) {
            mOwnerTransaction.replace(containerView, fragment, fragmentTag);
            if (mFragmentManager.getFragments() != null && mFragmentManager.getFragments().size() > 0)
                mOwnerTransaction.addToBackStack(null);
            mAddedFragment = fragment;
            mOwnerTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_base_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.wishlist) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void popFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }
}
