package com.application.autoform.presenter.products.seatcovers;

import com.application.autoform.model.bean.Color;
import com.application.autoform.model.products.seatcover.ColorListIntractorImpl;
import com.application.autoform.model.products.seatcover.IColorListIntractor;
import com.application.autoform.view.productview.seatcover.IColorListView;

import java.util.List;

/**
 * Created by sandeep.g9 on 11/3/2016.
 */

public class ColorListPresenter implements IColorListPresenter, IColorListIntractor.OnColorListLoaded {

    IColorListView mView;
    IColorListIntractor mIntractor;

    public ColorListPresenter(IColorListView mView) {
        this.mView = mView;
        mIntractor = new ColorListIntractorImpl();
    }

    @Override
    public void setColorList(List<Color> colorList) {
        mView.setColorList(colorList);
    }

    @Override
    public void getColorList() {
        mIntractor.getColorList(this);
    }

    public void addColor(Color color) {
        mIntractor.addColor(color);
    }
}
