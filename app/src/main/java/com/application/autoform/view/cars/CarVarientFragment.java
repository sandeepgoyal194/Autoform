package com.application.autoform.view.cars;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.autoform.R;
import com.application.autoform.model.bean.Car;
import com.application.autoform.presenter.cars.CarVarientListPresenterImpl;
import com.application.autoform.presenter.cars.ICarVarientListPresenter;

import java.util.List;

/**
 * Created by sandeep.g9 on 10/19/2016.
 */

public class CarVarientFragment extends Fragment implements ICarVarientListView {
    RecyclerView mCarList;
    CarVarientListAdapter mAdapter;
    ICarVarientListPresenter mPresenter;
    OnVariantSelected mVairentSelectionListener;

    public static CarVarientFragment newInstance(String brand) {
        CarVarientFragment fragment = new CarVarientFragment();
        Bundle args = new Bundle();
        args.putString("Brand", brand);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mVairentSelectionListener = (OnVariantSelected) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_varient, container, false);
        mPresenter = new CarVarientListPresenterImpl(this);
        setAdapter(view);
        String brand = getArguments().getString("Brand");
        mPresenter.getCarVarients(brand);
        return view;
    }

    private void setAdapter(View view) {
        if (mCarList == null) {
            mCarList = (RecyclerView) view.findViewById(R.id.car_varient_list);
        }
        mAdapter = new CarVarientListAdapter(getContext(), mVairentSelectionListener);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        mCarList.setLayoutManager(manager);
        mCarList.setAdapter(mAdapter);
    }


    @Override
    public void setCarVarientList(List<Car> cars) {
        mAdapter.setCarVarients(cars);
    }

    public interface OnVariantSelected {
        public void setVariant(String modelName,String varientName);

    }
}
