package com.application.autoform.model.bean;

import java.io.Serializable;

/**
 * Created by Sandeep on 05/04/2017.
 */

public class WhyUsPageData implements Serializable {
    String image;
    String description;
    int priority;
    String whyUsID;
    String status;


    public String getImage() {
        image = image.replace('\\', '/').replace(" ", "%20");
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getWhyUsID() {
        return whyUsID;
    }

    public void setWhyUsID(String whyUsID) {
        this.whyUsID = whyUsID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
