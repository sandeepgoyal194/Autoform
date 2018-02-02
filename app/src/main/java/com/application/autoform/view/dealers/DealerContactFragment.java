package com.application.autoform.view.dealers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.application.autoform.R;
import com.application.autoform.apppermission.PermissionFragment;
import com.application.autoform.model.bean.Dealer;
import com.application.autoform.presenter.dealers.DealerListPresenterImpl;
import com.application.autoform.presenter.dealers.IDealerListPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.application.autoform.apppermission.Permissions.PERMISSION_CALL_PHONE;

/**
 * Created by sandeep.g9 on 11/8/2016.
 */

public class DealerContactFragment extends PermissionFragment implements IDealerListView {
    // TODO: Rename and change types and number of parameters
    RecyclerView mDealerLocationListView;
    DealerContactListAdapter mAdapter = null;
    IDealerListPresenter mPresenter;
    private static final int REQUEST_FOR_CALL_ACTION = 0x1;
    private Menu mMenu;

    public static DealerContactFragment newInstance() {
        DealerContactFragment fragment = new DealerContactFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dealer_location, container, false);
        init(view);
        setAdapter();
        boolean granted = getPermission(PERMISSION_CALL_PHONE,REQUEST_FOR_CALL_ACTION);
        mPresenter = new DealerListPresenterImpl(this);
        mPresenter.getDealerList();
        getActivity().setTitle(getResources().getString(R.string.app_name));
        setHasOptionsMenu(true);
        return view;
    }

    private void init(View view) {
        mDealerLocationListView = (RecyclerView) view.findViewById(R.id.dealer_location_list_view);
    }

    private void setAdapter() {
        mAdapter = new DealerContactListAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mDealerLocationListView.setLayoutManager(manager);
        mDealerLocationListView.setAdapter(mAdapter);
    }

    @Override
    public void setDealerList(List<Dealer> dealerList) {
        mAdapter.setDealerList(dealerList);
    }

    @Override
    public void setPermission(int requestCode, boolean isGranted) {
        if(requestCode == REQUEST_FOR_CALL_ACTION) {
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.dealer_menu, menu);
        mMenu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(getActivity(), DealerLocationFilter.class);
        i.putExtra("dealerList",mPresenter.getmDealerList());
        startActivityForResult(i, 1);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                String stateSelected = data.getStringExtra("selected");
                String state = data.getStringExtra("selectedState");
                String city = data.getStringExtra("selectedCity");
                if (stateSelected.equals("stateCitySelected")) {
                    mMenu.getItem(1).setTitle(city);

                    ArrayList<Dealer> dealerLocationList = (ArrayList<Dealer>) mPresenter.getmDealerList().clone();
                    for (int i = 0; i < dealerLocationList.size(); i++) {
                        Dealer dealerData = dealerLocationList.get(i);
                        if ((state != null && !dealerData.getmState().equals(state)) || (city != null && !dealerData.getmCity().equals(city))) {
                            dealerLocationList.remove(dealerData);
                            i--;
                        }
                    }
                    mAdapter.setDealerList(dealerLocationList);
                }
            }
        }
    }
}
