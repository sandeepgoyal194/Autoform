package com.application.autoform.networknew;

import com.application.autoform.model.bean.Accessories;
import com.application.autoform.model.bean.Car;
import com.application.autoform.model.bean.CarBrand;
import com.application.autoform.model.bean.Color;
import com.application.autoform.model.bean.Dealer;
import com.application.autoform.model.bean.Product;
import com.application.autoform.model.bean.Response;
import com.application.autoform.model.bean.SubCategory;
import com.application.autoform.model.bean.User;
import com.application.autoform.model.bean.UserWishListData;
import com.application.autoform.model.bean.WhyUsPageData;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServicesWrapper {
    public final static String BASE_URL = "http://122.160.30.50:8080/AutoformsFinal/webapi/";
    public final static String IMAGE_URL = "http://122.160.30.50:8080/";
    public final static String OLD_BASE_URL = "http://122.160.30.50:8080/AutoformsFinal/";
    private static WebServicesWrapper wrapper;
    protected WebServices webServices;
    private Gson gson;


    private WebServicesWrapper(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        webServices = new Retrofit.Builder()
                .addConverterFactory(new RetrofitConverter())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build().create(WebServices.class);

        gson = new Gson();
    }

    public static WebServicesWrapper getInstance() {
        if (wrapper == null)
            wrapper = new WebServicesWrapper(BASE_URL);
        return wrapper;
    }

    private Map<String, String> getPartMap(Object object) {
        return (LinkedTreeMap) gson.fromJson(gson.toJson(object), Object.class);
    }

    private MultipartBody.Part getPart(String name, File file) {
        if (file == null || name == null)
            return null;
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(name, file.getName(), requestFile);
        return body;
    }

    public Call<ArrayList<CarBrand>> getMakeList(ResponseResolver<ArrayList<CarBrand>> responseResolver) {
        Call<ArrayList<CarBrand>> makeListCall = webServices.getMakeList();
        makeListCall.enqueue(responseResolver);
        return makeListCall;
    }

    public Call<ArrayList<Car>> getModelList(String make, ResponseResolver<ArrayList<Car>> responseResolver) {
        Call<ArrayList<Car>> modelListCall = webServices.getModelList(make);
        modelListCall.enqueue(responseResolver);
        return modelListCall;
    }

    public Call<ArrayList<Dealer>> getDealersList(ResponseResolver<ArrayList<Dealer>> responseResolver) {
        Call<ArrayList<Dealer>> dealerListCall = webServices.getDealerList();
        dealerListCall.enqueue(responseResolver);
        return dealerListCall;
    }

    public Call<ArrayList<Color>> getColorsList(ResponseResolver<ArrayList<Color>> responseResolver) {
        Call<ArrayList<Color>> colorListCall = webServices.getColorsList("Active");
        colorListCall.enqueue(responseResolver);
        return colorListCall;
    }

    public Call<ArrayList<Product>> getSeatCovers(String orderList, String designId, ResponseResolver<ArrayList<Product>> responseResolver, String accessory) {
        Call<ArrayList<Product>> seatCoversList = webServices.getSeatCovers(orderList, designId, accessory);
        seatCoversList.enqueue(responseResolver);
        return seatCoversList;
    }

    public Call<ArrayList<Accessories>> getAccessoriesList(ResponseResolver<ArrayList<Accessories>> responseResolver) {
        Call<ArrayList<Accessories>> colorListCall = webServices.getAccessoriesList("Active");
        colorListCall.enqueue(responseResolver);
        return colorListCall;
    }

    public Call<ArrayList<Product>> getRecommendDesign(ResponseResolver<ArrayList<Product>> responseResolver, String model, String varient) {
        Call<ArrayList<Product>> recommendDesignCall = webServices.getRecommendDesign(model, varient);
        recommendDesignCall.enqueue(responseResolver);
        return recommendDesignCall;
    }

    public Call<Response> submitWishList(ResponseResolver<Response> responseResolver, UserWishListData wishListData) {
        Call<Response> recommendDesignCall = webServices.submitWishListforUser(wishListData);
        recommendDesignCall.enqueue(responseResolver);
        return recommendDesignCall;
    }

    public Call<Response> contactusSubmit(ResponseResolver<Response> responseResolver, User user) {
        Call<Response> recommendDesignCall = webServices.contactusSubmit(user);
        recommendDesignCall.enqueue(responseResolver);
        return recommendDesignCall;
    }

    public Call<List<WhyUsPageData>> whyUsPageDataCall(ResponseResolver<List<WhyUsPageData>> responseResolver, String s) {
        Call<List<WhyUsPageData>> whyUsData = webServices.getWhyUsPages("Active");
        whyUsData.enqueue(responseResolver);
        return whyUsData;
    }

    public Call<List<SubCategory>> getSubCategories(ResponseResolver<List<SubCategory>> responseResolver) {
        Call<List<SubCategory>> subCategoryies = webServices.getSubCategories("Active");
        subCategoryies.enqueue(responseResolver);
        return subCategoryies;
    }

//    public Call<BlossomResponse<LoginResponse>> login(String username, String password, ResponseResolver<LoginResponse> responseResolver) {
//        Call<BlossomResponse<LoginResponse>> loginCall = webServices.login(APP_HEADER, username, password);
//        loginCall.enqueue(responseResolver);
//        return loginCall;
//    }
//
//    public Call<BlossomResponse<ArrayList<School>>> getSchool(ResponseResolver<ArrayList<School>> responseResolver) {
//        Call<BlossomResponse<ArrayList<School>>> schoolResponseCall = webServices.getSchool(APP_HEADER, getAccessToken(), getCoachId());
//        schoolResponseCall.enqueue(responseResolver);
//        return schoolResponseCall;
//    }
//
//    public Call<BlossomResponse<ArrayList<Event>>> getEventList(int schoolId, ResponseResolver<ArrayList<Event>> responseResolver) {
//        Call<BlossomResponse<ArrayList<Event>>> eventResponseCall = webServices.getEventList(APP_HEADER, getAccessToken(), schoolId);
//        eventResponseCall.enqueue(responseResolver);
//        return eventResponseCall;
//    }
//
//    public Call<BlossomResponse<StudentListResponse>> getStudentList(int eventId, ResponseResolver<StudentListResponse> responseResolver) {
//        Call<BlossomResponse<StudentListResponse>> eventResponseCall = webServices.getStudentList(APP_HEADER, getAccessToken(), eventId);
//        eventResponseCall.enqueue(responseResolver);
//        return eventResponseCall;
//    }
//
//    public Call<BlossomResponse<AssessmentResponse>> getAssessment(int assessmentId, ResponseResolver<AssessmentResponse> responseResolver) {
//        Call<BlossomResponse<AssessmentResponse>> assessmentResponseCall = webServices.getAssessment(APP_HEADER, getAccessToken(), assessmentId);
//        assessmentResponseCall.enqueue(responseResolver);
//        return assessmentResponseCall;
//    }
//
//    public Call<BlossomResponse<LoginResponse>> ping(String accessToken, ResponseResolver<LoginResponse> responseResolver) {
//        Call<BlossomResponse<LoginResponse>> loginCall = webServices.ping(APP_HEADER, accessToken);
//        loginCall.enqueue(responseResolver);
//        return loginCall;
//    }
//
//    public Call<BlossomResponse<Child>> postChild(Child child, ResponseResolver<Child> responseResolver) {
//        Call<BlossomResponse<Child>> childResponseCall = webServices.postChild(APP_HEADER, getAccessToken(), child);
//        childResponseCall.enqueue(responseResolver);
//        return childResponseCall;
//    }
//
//    public Call<BlossomResponse<Child>> updateChildImage(File file, int childId, ResponseResolver<Child> responseResolver) {
//        Call<BlossomResponse<Child>> childResponseCall = webServices.updateChildImage
//                (APP_HEADER, getAccessToken(), getPart("photo", file), "child", childId);
//        childResponseCall.enqueue(responseResolver);
//        return childResponseCall;
//    }

}
