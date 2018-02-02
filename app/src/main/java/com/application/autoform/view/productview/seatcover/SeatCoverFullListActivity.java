package com.application.autoform.view.productview.seatcover;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.application.autoform.R;
import com.application.autoform.model.bean.Product;
import com.application.autoform.presenter.products.seatcovers.ISeatCoverListPresenter;
import com.application.autoform.presenter.products.seatcovers.SeatCoverListPresenterImpl;
import com.application.autoform.view.AutoFormActivity;
import com.application.autoform.view.productview.seatcover.adapter.SeatCoverListAdapter;

import java.util.List;

/**
 * Created by sandeep.g9 on 10/19/2016.
 */

public class SeatCoverFullListActivity extends AutoFormActivity implements ISeatCoverListView {

    RecyclerView mSeatCoverList;
    SeatCoverListAdapter mAdapter;
    ISeatCoverListPresenter mPresenter;
    View emptyLayout;
    View coverListLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_all_design);
        emptyLayout = findViewById(R.id.emptylayout);
        coverListLayout = findViewById(R.id.coverl_list_layout);
        setAdapter();
        setmToolbar();
        mPresenter = new SeatCoverListPresenterImpl(this);
        mPresenter.getSeatCoverDesignList();
    }


    private void setAdapter() {
        if (mSeatCoverList == null) {
            mSeatCoverList = (RecyclerView) findViewById(R.id.seat_coverl_list);
        }
        mAdapter = new SeatCoverListAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mSeatCoverList.setLayoutManager(manager);
        mSeatCoverList.setAdapter(mAdapter);
    }


    @Override
    public void setAllDesignSeatCover(List<Product> seatCovers) {
        if(seatCovers.size() == 0) {
            emptyLayout.setVisibility(View.VISIBLE);
            coverListLayout.setVisibility(View.GONE);
            return;
        }
        mAdapter.setmSeatCovers(seatCovers);
    }


}
