package com.application.autoform.view.whyus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.WhyUsPageData;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;
import com.application.autoform.presenter.products.whyus.IWhyUsPresenter;
import com.application.autoform.presenter.products.whyus.WhyUsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 24/03/2017.
 */

public class AutoFormWhyUs extends Fragment implements IWhyUsView {
    ImageView imageViewLoader;
    View staticView;
    ViewPager viewPager;

    public static AutoFormWhyUs newInstance() {
        AutoFormWhyUs fragment = new AutoFormWhyUs();
        return fragment;
    }

    IWhyUsPresenter presenter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle(getResources().getString(R.string.why_us));
        View view = inflater.inflate(R.layout.why_us_layout, container, false);
        imageViewLoader = (ImageView) view.findViewById(R.id.progress_bar);
        staticView = view.findViewById(R.id.static_view);
        viewPager = (ViewPager) view.findViewById(R.id.whys_us_pager);
        presenter = new WhyUsPresenterImpl(this);
        presenter.onViewStarted();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void loadingData() {

    }

    @Override
    public void loadingComplete() {
        imageViewLoader.setVisibility(View.GONE);
    }

    @Override
    public void setWhyUsData(List<WhyUsPageData> whyUsData) {
        viewPager.setVisibility(View.VISIBLE);
        WhyUSAdapter adapter = new WhyUSAdapter(getContext());
        adapter.setmSeatCovers(whyUsData);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onLoadingFail() {
        staticView.setVisibility(View.VISIBLE);
    }

    class WhyUSAdapter extends PagerAdapter {
        ImageLoader mImageLoader;
        Context mContext;
        List<WhyUsPageData> whyUsData = new ArrayList<>();

        public WhyUSAdapter(Context mContext) {
            this.mContext = mContext;
            mImageLoader = new GlideImageLoaderImpl(mContext);
        }

        @Override
        public int getCount() {
            if (whyUsData.size() > 0)
                return whyUsData.size();
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = layoutInflater.inflate(R.layout.why_us_item, container, false);
            WhyUSAdapter.ViewHolder holder = new WhyUSAdapter.ViewHolder(itemView);
            holder.setContent(whyUsData.get(position));
            container.addView(itemView);
            return itemView;
        }

        public List<WhyUsPageData> getmSeatCovers() {
            return whyUsData;
        }

        public void setmSeatCovers(List<WhyUsPageData> mSeatCoverImages) {
            this.whyUsData = mSeatCoverImages;
            notifyDataSetChanged();
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }

        public WhyUsPageData getCurrentMajorMinor(int currentIndex) {
            return whyUsData.get(currentIndex);
        }

        private class ViewHolder {
            ImageView productImage;

            ViewHolder(View itemView) {
                productImage = (ImageView) itemView.findViewById(R.id.imageView2);
            }


            void setContent(WhyUsPageData whyUsPageData) {
                mImageLoader.loadImage(whyUsPageData.getImage(), productImage, R.drawable.loading);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewStopped();

    }
}
