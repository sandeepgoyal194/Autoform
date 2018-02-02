package com.application.autoform.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep.g9 on 10/20/2016.
 */

public class Car {


    @SerializedName("InteriorColor")
    @Expose
    private String interiorColor;
    @SerializedName("ModelName")
    @Expose
    private String modelName;
    @SerializedName("ImagePath")
    @Expose
    private String imagePath;
    @SerializedName("Varient")
    @Expose
    private String varient;

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getImagePath() {
        return imagePath.replace('\\', '/').replace(" ", "%20");
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }

    public boolean isSelected() {
        return modelName != null;
    }

}
