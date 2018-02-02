package com.application.autoform.view.productview.seatcover;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.autoform.R;
import com.application.autoform.model.bean.Color;
import com.application.autoform.model.bean.Product;
import com.application.autoform.model.bean.SubCategory;
import com.application.autoform.model.bean.WishListProduct;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;
import com.application.autoform.presenter.products.cart.CartViewPresenterImpl;
import com.application.autoform.presenter.products.seatcovers.ISeatCoverPresenter;
import com.application.autoform.presenter.products.seatcovers.ISubCategoryPresenter;
import com.application.autoform.presenter.products.seatcovers.SeatCoverPresenterImpl;
import com.application.autoform.presenter.products.seatcovers.SubCategoryPresenterImpl;
import com.application.autoform.view.cars.CarSelectorActivity;
import com.application.autoform.view.productview.seatcover.adapter.SeatCoverImageAdapter;
import com.application.autoform.view.productview.seatcover.custom_layout.ColorPallete;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 11/4/2016.
 */

public class SeatCoverDetailFragment extends Fragment implements ISeatCoverDetailView, ISubCategoryView {

    ViewPager mSeatCoverPager;
    TextView mSeatCoverPrice;
    SeatCoverImageAdapter mSeatCoverImageAdapter;
    ISeatCoverPresenter mPresneter;
    ColorPallete mMajorColorList;
    ColorPallete mMinorColorList;
    LinearLayout mAddToWishListLayout;

    ImageLoader mImageLoader;
    ArrayList<Product> mSeatCover = null;
    int currentIndex = -1;
    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentIndex = position;
            Product product = findProductFromList(position);
            if (product == null) {
                return;
            }
            Color majorColor = new Color();
            Color minorColor = new Color();
            majorColor.setColorImagePath(product.getMajorMinorList().get(position).getMajorColorPath());
            minorColor.setColorImagePath(product.getMajorMinorList().get(position).getMinorColorPath());
            majorColor.setColorName(product.getMajorMinorList().get(position).getMajorColor());
            minorColor.setColorName(product.getMajorMinorList().get(position).getMinorColor());
            selectColorImages(majorColor, minorColor);
            setCoverDesignName(product.getDesignName());

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public static SeatCoverDetailFragment newInstance(int design) {
        SeatCoverDetailFragment fragment = new SeatCoverDetailFragment();
        Bundle args = new Bundle();
        args.putInt("Design", design);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cover_design_detail, container, false);
        init(view);
        setAdapter();
        mImageLoader = new GlideImageLoaderImpl(getContext());
        mPresneter = new SeatCoverPresenterImpl(this);
        int design = getArguments().getInt("Design");
        mPresneter.getSeatCover(design);
        mSeatCoverPager.addOnPageChangeListener(pageChangeListener);
        mAddToWishListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishListProduct product = new WishListProduct();
                Product tempProduct = findProductFromList(currentIndex);
                product.setDesignName(tempProduct.getDesignName());
                product.setProductDesginPath(mSeatCoverImageAdapter.getCurrentMajorMinor(currentIndex).getDesignImage());
                product.setMajorColorPath(mMajorColorList.getSelectedColor().getColorImagePath());
                product.setMinorColorPath(mMinorColorList.getSelectedColor().getColorImagePath());
                product.setMajorColor(mMajorColorList.getSelectedColor().getColorName());
                product.setMinorColor(mMinorColorList.getSelectedColor().getColorName());
                new CartViewPresenterImpl().addProductToCart(product);
                Toast.makeText(getContext(), "Added to WishList Successfully", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    SetDesignOnTitle title;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        title = (SetDesignOnTitle) context;
    }

    protected void setAdapter() {
        mSeatCoverImageAdapter = new SeatCoverImageAdapter(getContext());
        mSeatCoverPager.setAdapter(mSeatCoverImageAdapter);
    }

    protected void init(View view) {
        mSeatCoverPager = (ViewPager) view.findViewById(R.id.pager_cover_selector);
        mMajorColorList = (ColorPallete) view.findViewById(R.id.img_major_color);
        mMinorColorList = (ColorPallete) view.findViewById(R.id.img_minor_color);
        mAddToWishListLayout = (LinearLayout) view.findViewById(R.id.wishlist_button);
    }

    @Override
    public void setSeatCoverImages(ArrayList<Product> products) {
        mSeatCover = products;
        List<Product.MajorMinorList> majorMinorList = new ArrayList<>();
        for (Product product : products) {
            majorMinorList.addAll(product.getMajorMinorList());
            for (Product.MajorMinorList majorMinor : majorMinorList) {
                majorMinor.setSubCategory(product.getSubCategory());
                majorMinor.setMinimumPrice(product.getMinimumPrice());
                majorMinor.setMaximumPrice(product.getMaximumPrice());
            }
        }
        mSeatCoverImageAdapter.setmSeatCovers(majorMinorList);
        setDeignOnScreen(0);
    }

    private void setDeignOnScreen(int position) {
        currentIndex = position;
        pageChangeListener.onPageSelected(0);
    }

    public void setCoverDesignName(String name) {
        title.setDesignName(name);
    }

    void selectColorImages(Color majorColor, Color minorColor) {
        // select color image in recyclerview
        mMinorColorList.selectColor(minorColor);
        mMajorColorList.selectColor(majorColor);
    }

    Product findProductFromList(int currPagePosition) {
        int count = 0;
        Product prevProduct = null;
        for (Product product : mSeatCover) {
            if (count >= currPagePosition) {
                return product;
            }
            prevProduct = product;
            count += product.getMajorMinorList().size();
        }
        return prevProduct;

    }

    public void launcheVehicleSelection() {
        //TODO: make result string final
        Intent i = new Intent(getContext(), CarSelectorActivity.class);
        startActivityForResult(i, 2);
    }

    @Override
    public void setSubCategoryDetail(SubCategory subCategoryDetail) {

    }

    public interface SetDesignOnTitle {
        public void setDesignName(String design);
    }

}
