package com.application.autoform.view.dealers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.Dealer;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;
import com.application.autoform.utility.Call;
import com.application.autoform.utility.Email;
import com.application.autoform.utility.Messaging;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public class DealerContactListAdapter extends RecyclerView.Adapter<DealerContactListAdapter.DealerContactItem> {
    Context context;
    List<Dealer> mDealerList = new ArrayList<>();
    ImageLoader mImageLoader = null;

    public DealerContactListAdapter(Context context) {
        this.context = context;
        mImageLoader = new GlideImageLoaderImpl(context);
    }

    @Override
    public DealerContactItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dealer_contact_item_view, null, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        ));
        return new DealerContactItem(v);
    }

    @Override
    public void onBindViewHolder(DealerContactItem holder, int position) {
        holder.setContent(mDealerList.get(position));
    }

    public void setDealerList(List<Dealer> dealers) {
        this.mDealerList = dealers;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mDealerList.size();
    }

    class DealerContactItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dealerName;
        TextView dealerAddress;
        Button email;
        Button message;
        Button call;
        Dealer dealer;

        public DealerContactItem(View itemView) {
            super(itemView);
            dealerName = (TextView) itemView.findViewById(R.id.txt_name);
            dealerAddress = (TextView) itemView.findViewById(R.id.txt_address);
            email = (Button) itemView.findViewById(R.id.btn_email);
            message = (Button) itemView.findViewById(R.id.btn_message);
            call = (Button) itemView.findViewById(R.id.btn_call);
            email.setOnClickListener(this);
            message.setOnClickListener(this);
            call.setOnClickListener(this);
        }

        void setContent(Dealer dealer) {
            dealerAddress.setText(dealer.getmAddress());
            dealerName.setText(dealer.getmName());
            this.dealer = dealer;
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_call) {
                new Call(context).call(dealer.getmPhoneNo());
            }
            if (v.getId() == R.id.btn_email) {
                new Email(context).openEmailForId(dealer.getmEmailId());
            }
            if (v.getId() == R.id.btn_message) {
                new Messaging(context).messageOnNo(dealer.getmPhoneNo());
            }
        }
    }
}
