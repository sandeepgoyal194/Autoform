package com.application.autoform.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by madhurigupta on 11/01/17.
 */

public class Accessories {

    @SerializedName("Name")
    private String accessoryName;

    @SerializedName("Image")
    private String accessoryImagePath;


    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getAccessoryImagePath() {
        return accessoryImagePath;
    }

    public void setAccessoryImagePath(String accessoryImagePath) {
        this.accessoryImagePath = accessoryImagePath;
    }
}
