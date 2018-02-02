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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServices {

    @GET("Make/")
    Call<ArrayList<CarBrand>> getMakeList();

    @POST("models")
    Call<ArrayList<Car>> getModelList(@Query("Make") String make);

    @GET("Dealers/")
    Call<ArrayList<Dealer>> getDealerList();

    @POST("colors/")
    Call<ArrayList<Color>> getColorsList(@Query("status") String status);

    @POST("SeatCovers")
    Call<ArrayList<Product>> getSeatCovers(@Query("orderList") String orderList, @Query("DesignId") String designId, @Query("accessory") String accessory);

    @POST("Category/")
    Call<ArrayList<Accessories>> getAccessoriesList(@Query("status") String status);

    @POST("SeatCovers/RecomendedDesign/")
    Call<ArrayList<Product>> getRecommendDesign(@Query("model") String model, @Query("varient") String varient);


    @POST("Queries/")
    Call<Response> submitWishListforUser(@Body UserWishListData wishListData);

    @POST("Queries/")
    Call<Response> contactusSubmit(@Body User user);


    @POST("WhyUs/")
    Call<List<WhyUsPageData>> getWhyUsPages(@Query("status") String status);

 /*
    @GET("ping")
    Call<BlossomResponse<LoginResponse>> ping(@Header("X-Application") String appHeader,
                               O               @Header("Authorization") String auth);

    @POST("login")
    @Multipart
    Call<BlossomResponse<LoginResponse>> login(@Header("X-Application") String appHeader,
                                               @Part("username") String username,
                                               @Part("password") String password);

    @POST("Child")
    Call<BlossomResponse<Child>> postChild(@Header("X-Application") String appHeader,
                                           @Header("Authorization") String auth,
                                           @Body() Child child);

    @POST("document/photo")
    @Multipart
    Call<BlossomResponse<Child>> updateChildImage(@Header("X-Application") String appHeader,
                                                  @Header("Authorization") String auth,
                                                  @Part() MultipartBody.Part file,
                                                  @Part("resource_type") String resourceType,
                                                  @Part("resource_id") int resourceId);

    @GET("Coach/{id}/School")
    Call<BlossomResponse<ArrayList<School>>> getSchool(@Header("X-Application") String appHeader,
                                                       @Header("Authorization") String auth,
                                                       @Path("id") int id);

    @GET("School/{id}/Event")
    Call<BlossomResponse<ArrayList<Event>>> getEventList(@Header("X-Application") String appHeader,
                                                         @Header("Authorization") String auth,
                                                         @Path("id") int id);

    @GET("Event/{id}")
    Call<BlossomResponse<StudentListResponse>> getStudentList(@Header("X-Application") String appHeader,
                                                              @Header("Authorization") String auth,
                                                              @Path("id") int id);

    @GET("Assessment/{id}")
    Call<BlossomResponse<AssessmentResponse>> getAssessment(@Header("X-Application") String appHeader,
                                                            @Header("Authorization") String auth,
                                                            @Path("id") int id);
*/
 @POST("SubCategory/")
 Call<List<SubCategory>> getSubCategories(@Query("status") String status);
}