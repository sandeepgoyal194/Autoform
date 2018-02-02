package com.application.autoform.view.cars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.Car;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 04/11/2016.
 */
/*
* Repeated Code need to generalize this code
* */
public class CarVarientListAdapter extends RecyclerView.Adapter<CarVarientListAdapter.CarVariantHolder> {
    Context context;
    List<Car> carList = new ArrayList<>();
    ImageLoader mImageLoader = null;
    CarVarientFragment.OnVariantSelected mVairentSelectionListener;

    public CarVarientListAdapter(Context context) {
        this.context = context;
        mImageLoader = new GlideImageLoaderImpl(context);
    }

    public CarVarientListAdapter(Context context, CarVarientFragment.OnVariantSelected vairentSelectionListener) {
        this(context);
        mVairentSelectionListener = vairentSelectionListener;
    }

    @Override
    public CarVarientListAdapter.CarVariantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_varient_item, null);
        return new CarVariantHolder(v);
    }

    @Override
    public void onBindViewHolder(CarVarientListAdapter.CarVariantHolder holder, int position) {
        holder.setContent(carList.get(position));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public void setCarVarients(List<Car> cars) {
        this.carList = cars;
        notifyDataSetChanged();
    }

    public void addVarient(Car car) {
        carList.add(car);
        notifyDataSetChanged();
    }

    public void removeVarient(Car car) {
        carList.remove(car);
        notifyDataSetChanged();
    }


    class CarVariantHolder extends RecyclerView.ViewHolder {
        String carID;
        ImageView carImage;
        TextView carName;
        TextView carVarient;

        // TextView amount;
        // Button btnCheckout;
        public CarVariantHolder(View itemView) {
            super(itemView);
            carImage = (ImageView) itemView.findViewById(R.id.car_image);
            carName = (TextView) itemView.findViewById(R.id.car_name);
            carVarient = (TextView) itemView.findViewById(R.id.car_vairent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVairentSelectionListener.setVariant(carName.getText().toString(),carVarient.getText().toString());
                }
            });
        }

        void setContent(Car car) {
            mImageLoader.loadImage(car.getImagePath(), carImage, R.drawable.loading);
            carName.setText(car.getModelName());
            carVarient.setText(car.getVarient());
//            carID = car.getmCarID();
        }
    }

}