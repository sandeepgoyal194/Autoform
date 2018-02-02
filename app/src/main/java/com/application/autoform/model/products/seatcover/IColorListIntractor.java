package com.application.autoform.model.products.seatcover;

import com.application.autoform.model.bean.Color;

import java.util.List;

/**
 * Created by sandeep.g9 on 11/3/2016.
 */

public interface IColorListIntractor {
    void getColorList(OnColorListLoaded presenter);

    void addColor(Color color);

    interface OnColorListLoaded {
        void setColorList(List<Color> colorList);
    }
}
