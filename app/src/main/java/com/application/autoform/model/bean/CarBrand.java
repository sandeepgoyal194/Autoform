package com.application.autoform.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep.g9 on 10/20/2016.
 */

public class CarBrand {
    @SerializedName("ImagePath")
    @Expose
    private String imagePath;
    @SerializedName("Make")
    @Expose
    private String make;

    public String getImagePath() {
        return imagePath.replace('\\', '/').replace(" ", "%20");
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
