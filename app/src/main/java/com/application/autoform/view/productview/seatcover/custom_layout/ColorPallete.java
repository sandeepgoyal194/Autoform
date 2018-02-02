package com.application.autoform.view.productview.seatcover.custom_layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.application.autoform.model.bean.Color;
import com.application.autoform.presenter.products.seatcovers.ColorListPresenter;
import com.application.autoform.presenter.products.seatcovers.IColorListPresenter;
import com.application.autoform.view.productview.seatcover.IColorListView;

import java.util.List;

/**
 * Created by sandeep.g9 on 11/3/2016.
 */

public class ColorPallete extends RecyclerView implements IColorListView {
    ColorListAdapter mAdapter;
    IColorListPresenter mPresenter;
    int mSelectedPosition = -1;

    public ColorPallete(Context context) {
        super(context);
        mAdapter = new ColorListAdapter(context);
        setAdapter(context);

    }

    public ColorPallete(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mAdapter = new ColorListAdapter(context);
        setAdapter(context);

    }

    public ColorPallete(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mAdapter = new ColorListAdapter(context);
        setAdapter(context);
    }

    private void setAdapter(Context context) {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        setLayoutManager(layoutManager);
        setAdapter(mAdapter);
        mPresenter = new ColorListPresenter(this);
        mPresenter.getColorList();
    }

    public void setColorList(List<Color> colorList) {
        mAdapter.setColorList(colorList);
    }

    public void selectColor(Color color) {

        int position = mAdapter.selectColor(color);
        if (position < 0) {
            mAdapter.addColor(color);
            position = mAdapter.selectColor(color);
        }
        scrollToPosition(position);
        mSelectedPosition = position;
    }

    public Color getSelectedColor() {
        return mAdapter.getSelectedColor();
    }
}
