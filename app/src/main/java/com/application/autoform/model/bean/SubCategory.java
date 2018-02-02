package com.application.autoform.model.bean;

/**
 * Created by Sandeep on 06/05/2017.
 */

public class SubCategory {

    String image;
    String description;
    String subCategoryID;
    String category;
    String subCategoryName;
    String status;

    public String getImage() {
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

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        SubCategory category = (SubCategory) obj;
        return category.getSubCategoryName().equalsIgnoreCase(getSubCategoryName());
    }
}
