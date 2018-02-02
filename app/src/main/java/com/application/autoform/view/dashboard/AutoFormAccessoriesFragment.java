package com.application.autoform.view.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.autoform.R;
import com.application.autoform.model.bean.Accessories;
import com.application.autoform.presenter.AccessoriesPresenterImpl;
import com.application.autoform.presenter.IAccessoriesListPresenter;
import com.application.autoform.view.IItemSelectedListener;

import java.util.ArrayList;

public class AutoFormAccessoriesFragment extends Fragment implements IAccessoriesView, IItemSelectedListener {

    RecyclerView mAccessoriesListView;
    AutoFormAccessoriesListAdapter mAdapter = null;
    IAccessoriesListPresenter mPresenter;

    public static AutoFormAccessoriesFragment newInstance() {
        AutoFormAccessoriesFragment fragment = new AutoFormAccessoriesFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autoform_products, container, false);
        init(view);
        setAdapter();
        mPresenter = new AccessoriesPresenterImpl(this);
        mPresenter.getAccessoriesList();
        getActivity().setTitle(getResources().getString(R.string.app_name));
        return view;
    }

    private void init(View view) {
        mAccessoriesListView = (RecyclerView) view.findViewById(R.id.accessory_view);
        mAccessoriesListView.addItemDecoration(new MarginDecoration(getContext()));
    }

    private void setAdapter() {
        mAdapter = new AutoFormAccessoriesListAdapter(getContext(), this);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
       /* manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 1;
                }
                return 2;
            }
        });*/
        mAccessoriesListView.setLayoutManager(manager);
        mAccessoriesListView.setAdapter(mAdapter);
    }

    @Override
    public void setAccessoriesList(ArrayList<Accessories> accessories) {
        for (Accessories accessories1 : accessories) {
            if (accessories1.getAccessoryName().equalsIgnoreCase("Seat Covers") || accessories1.getAccessoryName().equalsIgnoreCase("Seat Cover")) {
                accessories.remove(accessories1);
                accessories.add(0, accessories1);
                break;
            }
        }
        if (accessories != null) {
            mAdapter.setAccessoriesList(accessories);
        }
    }

    @Override
    public void onItemSelected(String title) {

    }
}
