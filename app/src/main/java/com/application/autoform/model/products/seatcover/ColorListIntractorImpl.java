package com.application.autoform.model.products.seatcover;

import com.application.autoform.model.bean.Color;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by sandeep.g9 on 11/3/2016.
 */

public class ColorListIntractorImpl implements IColorListIntractor {
    private static List<Color> mColorList = new ArrayList<>();


    @Override
    public void getColorList(final OnColorListLoaded listener) {
        if (mColorList.size() <= 0) {
            WebServicesWrapper.getInstance().getColorsList(new ResponseResolver<ArrayList<Color>>() {
                @Override
                public void onSuccess(ArrayList<Color> colors, Response response) {
                    mColorList = colors;
                    listener.setColorList(mColorList);
                }

                @Override
                public void onFailure(RestError error, String msg) {
                    listener.setColorList(mColorList);
                }
            });
        } else {
            listener.setColorList(mColorList);
        }
    }

    public void addColor(Color color) {
        mColorList.add(color);
    }

}
