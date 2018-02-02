package com.application.autoform.network;

/**
 * Created by Sandeep on 09/01/2016.
 */
public interface IServerResponseListener {
    //  String SERVER_URL = "http://122.160.30.50:8080/PC_Test/";
    String SERVER_URL = "http://122.160.30.50:8080/AutoformsV1/webapi/";
    String IMAGE_SERVER_URL = "http://122.160.30.50:8080/";
//   String SERVER_URL = "http://10.0.0.14:8080/PC/"; // testing server
    // String SERVER_URL = "http://192.168.1.12:8080/PC/";
    // String SERVER_URL =  "http://tomcat.softminesol.in/PC160516/";

    int REQUEST_GET = 1;
    int REQUEST_POST = 2;
    int RESPONSE_OK = 200;

    int RESPONSE_ERROR_500 = 500;
    int RESPONSE_ERROR_DATA_NULL = 501;
    int RESPONSE_ERROR_UNKNOWN = -1;
    int RESPONSE_ERROR_FAILURE = 0;

    String RESPONSE_EXCEPTION_500 = "RESPONSE_EXCEPTION_500";
    String RESPONSE_EXCEPTION_UNKNOWN = "RESPONSE_EXCEPTION_UNKNOWN";

    void onResponse(String response);

    void onErrorResponse(int errorType, String response);
}
