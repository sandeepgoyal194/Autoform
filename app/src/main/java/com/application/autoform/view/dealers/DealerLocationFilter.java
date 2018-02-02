package com.application.autoform.view.dealers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.Dealer;
import com.application.autoform.presenter.dealers.DealerListFilterPresenter;
import com.application.autoform.presenter.dealers.DealerListPresenterImpl;
import com.application.autoform.presenter.dealers.IDealerListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep on 22-06-2015.
 */
public class DealerLocationFilter extends Activity {
    private String SELECT_STATE = "STATE";
    private String SELECT_CITY = "CITY";
    private String SELECT_CITY_SELECTED = "CITY_SELECTED";
    String mStateList[] = null;
    ArrayList<String> mCityList = new ArrayList<>();
    String mState = SELECT_STATE;
    TextView mSelectState = null;
    private boolean isCityAdapter = false;
    IDealerListPresenter.IDealerFilterPresenter filterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealer_filter);
        mSelectState = (TextView) findViewById(R.id.text_view_select_state);
        filterPresenter = new DealerListFilterPresenter((List<Dealer>) getIntent().getSerializableExtra("dealerList"));
        mStateList = filterPresenter.getStateList();
    }

    ListView listViewDealerFilter = null;

    Intent resultIntent = new Intent();

    @Override
    protected void onResume() {
        super.onResume();
        listViewDealerFilter = (ListView) findViewById(R.id.listView_dealer_filter);
        setStateAdapter();
        listViewDealerFilter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mState.equals(SELECT_STATE)) {
                    //load city
                    resultIntent.putExtra("selected","stateCitySelected");
                    String state = mStateList[position];
                    mCityList = filterPresenter.getCityList(state);
                    resultIntent.putExtra("selectedState", state);
                    setCityAdapter();
                    mState = SELECT_CITY;
                    mSelectState.setText("Select City");

                } else {
                    mState = SELECT_CITY_SELECTED;
                    String city = mCityList.get(position);
                    resultIntent.putExtra("selectedCity", city);
                    setResult(1, resultIntent);
                    finish();
                    //select city
                }
            }
        });
    }

    private void setStateAdapter() {
        isCityAdapter = false;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStateList);
        listViewDealerFilter.setAdapter(adapter);
    }

    private void setCityAdapter() {
        isCityAdapter = true;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mCityList);
        listViewDealerFilter.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isCityAdapter) {
                mSelectState.setText("Select State");
                mState = SELECT_STATE;
                setStateAdapter();
            } else {
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

   /* private ArrayList<String> getStateCityList() {
        IServerUpdateCallback callback = null;
        ArrayList<DealerLocationData> dealerLocationList = ProductManager.getInstance().getDealerLocationList(callback);

        for (DealerLocationData d : dealerLocationList) {
            mStateList.add(d.getState());
            mCityList.add(d.getCity());
        }

    }*/
}
