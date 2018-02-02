package com.application.autoform.network;

/**
 * Created by Sandeep on 21/01/2016.
 */
public class NetworkConnectivity {

    public static boolean isNetworkAvailable() {
      /*  ConnectivityManager connectivityManager = (ConnectivityManager) GlanceApplicationController.getInstance().getSystemService(GlanceApplicationController.getInstance().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();*/
        return true;
    }
}
