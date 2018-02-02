package com.application.autoform.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 10/19/2016.
 */


public class Product {


    int designID;
    @SerializedName("MajorMinorList")
    @Expose
    private List<MajorMinorList> majorMinorList = null;
    @SerializedName("MaximumPrice")
    @Expose
    private String maximumPrice;
    @SerializedName("DesignName")
    @Expose
    private String designName;
    @SerializedName("MinimumPrice")
    @Expose
    private String minimumPrice;
    private String subCategory;

    private String subCategoryPath;

    public String getSubCategoryPath() {
        return subCategoryPath;
    }

    public void setSubCategoryPath(String subCategoryPath) {
        this.subCategoryPath = subCategoryPath;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public List<MajorMinorList> getMajorMinorList() {
        return majorMinorList;
    }

    public void setMajorMinorList(List<MajorMinorList> majorMinorList) {
        this.majorMinorList = majorMinorList;
    }

    public String getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(String maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }

    public String getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(String minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public void addMajorMinor(MajorMinorList majorMinor) {
        if (majorMinorList == null) {
            majorMinorList = new ArrayList();
        }
        majorMinorList.add(majorMinor);
    }

    public int getDesignID() {
        return designID;
    }

    public void setDesignID(int designID) {
        this.designID = designID;
    }

    public static class MajorMinorList {


        @SerializedName("MinorColor")
        @Expose
        private String minorColor;
        @SerializedName("MajorColorPath")
        @Expose
        private String majorColorPath;
        @SerializedName("Popularity")
        @Expose
        private String popularity;
        @SerializedName("MajorColor")
        @Expose
        private String majorColor;
        @SerializedName("MinorColorPath")
        @Expose
        private String minorColorPath;
        @SerializedName("DesignColorID")
        @Expose
        private Integer designColorID;
        @SerializedName("DesignImage")
        @Expose
        private String designImage;
        @SerializedName("MaximumPrice")
        private String maximumPrice;
        @SerializedName("MinimumPrice")
        private String minimumPrice;

        private String subCategory;


        public String getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(String subCategory) {
            this.subCategory = subCategory;
        }

        public String getMinorColor() {
            return minorColor;
        }

        public void setMinorColor(String minorColor) {
            this.minorColor = minorColor;
        }

        public String getMajorColorPath() {
            return majorColorPath;
        }

        public void setMajorColorPath(String majorColorPath) {
            this.majorColorPath = majorColorPath;
        }

        public String getPopularity() {
            return popularity;
        }

        public void setPopularity(String popularity) {
            this.popularity = popularity;
        }

        public String getMajorColor() {
            return majorColor;
        }

        public void setMajorColor(String majorColor) {
            this.majorColor = majorColor;
        }

        public String getMinorColorPath() {
            return minorColorPath;
        }

        public void setMinorColorPath(String minorColorPath) {
            this.minorColorPath = minorColorPath;
        }

        public Integer getDesignColorID() {
            return designColorID;
        }

        public void setDesignColorID(Integer designColorID) {
            this.designColorID = designColorID;
        }

        public String getDesignImage() {
            return designImage;
        }

        public void setDesignImage(String designImage) {
            this.designImage = designImage;
        }

        public String getMaximumPrice() {
            return maximumPrice;
        }

        public void setMaximumPrice(String maximumPrice) {
            this.maximumPrice = maximumPrice;
        }

        public String getMinimumPrice() {
            return minimumPrice;
        }

        public void setMinimumPrice(String minimumPrice) {
            this.minimumPrice = minimumPrice;
        }
    }

    public class LargeImageData {
        private String image;

        public String getImagePath() {
            image = image.replace('\\', '/').replace(" ", "%20");
            return image;
        }

        public void setImagePath(String image) {
            this.image = image;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Product product = (Product) obj;
        return product.getDesignName().equalsIgnoreCase(getDesignName());
    }
}
