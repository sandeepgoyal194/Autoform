package com.application.autoform.view.whyus;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.application.autoform.R;
import com.application.autoform.model.bean.WhyUsPageData;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;

/**
 * Created by Sandeep on 09/05/2017.
 */

public class WhyUsDialog extends DialogFragment {

    public static WhyUsDialog newInstance(WhyUsPageData whyUsPageData) {
        WhyUsDialog fragment = new WhyUsDialog();
        Bundle args = new Bundle();
        args.putSerializable("whyus", whyUsPageData);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.why_us_dialog, container, false);
        getDialog().requestWindowFeature(1);
        View closeButton = (View) view.findViewById(R.id.btn_close);
        ImageView imageView = (ImageView) view.findViewById(R.id.why_us_image);
        WhyUsPageData whyUsPageData = (WhyUsPageData) getArguments().getSerializable("whyus");
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        new GlideImageLoaderImpl(getContext()).loadImage(whyUsPageData.getImage(), imageView, R.drawable.loading);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                super.onBackPressed();
                dismiss();
            }
        };
        return dialog;
    }
}
