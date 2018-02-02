package com.application.autoform.view.cars;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.application.autoform.R;
import com.application.autoform.view.AutoFormActivity;

/**
 * Created by sandeep.g9 on 10/19/2016.
 */

public class CarSelectorActivity extends AutoFormActivity implements CarBrandListFragment.OnBrandSelected, CarVarientFragment.OnVariantSelected {
    String mBrand;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selector);
        addFrgment(CarBrandListFragment.newInstance(), "Car_Brand_Fragment", R.id.container_view);

    }


    @Override
    public void setBrand(String brand) {
        mBrand = brand;
        addFrgment(CarVarientFragment.newInstance(brand), "Car_Varient_Fragment", R.id.container_view);
    }

    @Override
    public void setVariant(String modelName,String varient) {
        Intent intent = new Intent();
        intent.putExtra("ModelName", modelName);;
        intent.putExtra("Varient", varient);
        setResult(1, intent);
        finish();
    }
}
