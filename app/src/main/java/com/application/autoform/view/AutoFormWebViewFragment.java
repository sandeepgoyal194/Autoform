package com.application.autoform.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.application.autoform.R;
import com.application.autoform.networknew.WebServicesWrapper;
import com.application.autoform.utility.WebViewFragment;

/**
 * Created by Chinin on 5/1/2015.
 */
public class AutoFormWebViewFragment extends WebViewFragment {
    public static AutoFormWebViewFragment newInstance(String url,String title) {
        AutoFormWebViewFragment fragment = new AutoFormWebViewFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String url = getArguments().getString("url");
        String title = getArguments().getString("title");
        getActivity().setTitle(title);
        WebView webView = (WebView) super.onCreateView(inflater, container, savedInstanceState);
        webView.loadUrl(WebServicesWrapper.OLD_BASE_URL + url);
        return webView;
    }
}