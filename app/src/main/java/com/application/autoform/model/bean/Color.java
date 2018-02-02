package com.application.autoform.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep.g9 on 11/3/2016.
 */

public class Color {
    @SerializedName("ImagePath")
    String colorImagePath;
    @SerializedName("ColorName")
    String colorName;

    public String getColorImagePath() {
        return colorImagePath;
    }

    public void setColorImagePath(String colorImagePath) {
        this.colorImagePath = colorImagePath;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public boolean equals(Object obj) {
        return colorName.equals(((Color) obj).getColorName());
    }
}
