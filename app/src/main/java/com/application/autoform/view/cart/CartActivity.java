package com.application.autoform.view.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.application.autoform.R;
import com.application.autoform.model.bean.Car;
import com.application.autoform.model.bean.WishListProduct;
import com.application.autoform.model.dbhandler.PrefManager;
import com.application.autoform.presenter.products.cart.CartViewPresenterImpl;
import com.application.autoform.presenter.products.cart.ICartViewPresenter;
import com.application.autoform.view.AutoFormActivity;
import com.application.autoform.view.cars.CarSelectorActivity;
import com.application.autoform.view.dashboard.MarginDecoration;

import java.util.List;

/**
 * Created by sandeep.g9 on 3/13/2017.
 */

public class CartActivity extends AutoFormActivity implements ICartViewPresenter.ICartView, CartListAdapter.CartRemoveListener {
    ICartViewPresenter presenter;
    RecyclerView mCartItemList;
    CartListAdapter mAdapter;
    View mProceedButton;
    View mEmptyView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        mCartItemList = (RecyclerView) findViewById(R.id.cart_item_list);
        mCartItemList.addItemDecoration(new MarginDecoration(this));
        mAdapter = new CartListAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mCartItemList.setAdapter(mAdapter);
        mCartItemList.setLayoutManager(manager);
        presenter = new CartViewPresenterImpl(this);
        mProceedButton = findViewById(R.id.proceed_button);
        mEmptyView = findViewById(R.id.emptylayout);
        mProceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.getItemCount() <= 0) {
                    Toast.makeText(CartActivity.this, "No Item in Cart To Proceed", Toast.LENGTH_LONG).show();
                    return;
                }
                Car vehicle = new PrefManager(CartActivity.this).getVehicle();
                if (vehicle == null || !vehicle.isSelected()) {
                    launcheVehicleSelection();
                } else {
                    launchUserDetailActivity();
                }
            }
        });
        setmToolbar();
    }

    public void launcheVehicleSelection() {
        //TODO: make result string final
        Intent i = new Intent(this, CarSelectorActivity.class);
        startActivityForResult(i, 2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onCartViewStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onCartViewStop();
    }

    @Override
    public void setProductList(List<WishListProduct> mCartProducts) {
        mAdapter.setmCartProducts(mCartProducts);
        onCartItemRemove();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            if (resultCode == 1) {
                String modelName = data.getExtras().getString("ModelName");
                String varient = data.getExtras().getString("Varient");
                Car car = new Car();
                car.setVarient(varient);
                car.setModelName(modelName);
                new PrefManager(this).setVehicle(car);
                launchUserDetailActivity();
            } else {
                Snackbar.make(mCartItemList, "Please select vehicle", Snackbar.LENGTH_LONG).show();
            }
        } else if (requestCode == 3) {
            if (resultCode == 1) {
                Snackbar.make(mCartItemList, "Query Submitted Successfully", Snackbar.LENGTH_LONG).show();
                finish();
            }
        }
    }

    public void launchUserDetailActivity() {
        Intent i = new Intent(this, ActivityUserFillInfo.class);
        startActivityForResult(i, 3);
    }

    @Override
    public void onCartItemRemove() {
        if (mAdapter.getItemCount() <= 0) {
            mEmptyView.setVisibility(View.VISIBLE);

        }else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
