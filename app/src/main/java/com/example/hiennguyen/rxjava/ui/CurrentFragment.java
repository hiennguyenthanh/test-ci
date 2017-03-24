package com.example.hiennguyen.rxjava.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hiennguyen.rxjava.CurrentView;
import com.example.hiennguyen.rxjava.R;
import com.example.hiennguyen.rxjava.model.WeatherData;
import com.example.hiennguyen.rxjava.presenter.CurrentWeatherPresenter;
import com.example.hiennguyen.rxjava.presenter.CurrentWeatherPresenterImpl;

/**
 * A placeholder fragment containing a simple view.
 */
public class CurrentFragment extends Fragment implements View.OnClickListener, CurrentView {
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    private static final String ACTION_CALL = "android.intent.action.CALL";
    private Button mBtnOk, mBtnAnnotation;
    private TextView mTxtLocation;
    private EditText mSearch;
    private CurrentWeatherPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    public CurrentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = CurrentWeatherPresenterImpl.getInstance(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        mBtnOk = (Button) view.findViewById(R.id.btn_ok);
        mTxtLocation = (TextView) view.findViewById(R.id.txt_location);
        mSearch = (EditText) view.findViewById(R.id.ed_enter_city);
        mBtnAnnotation = (Button) view.findViewById(R.id.btn_annotation);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setMessage("Please wait.");
        mProgressDialog.setCancelable(false);

        mBtnOk.setOnClickListener(this);
        mBtnAnnotation.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                String city = mSearch.getText().toString();
                mPresenter.getCurrentWeather(city);
                break;
            case R.id.btn_annotation:
                Intent intent = new Intent(ACTION_CALL);
                intent.setData(Uri.parse("tel: 1234567890"));
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getData(WeatherData data) {
        mTxtLocation.setText(data.getLocation().getCountry());
    }

    @Override
    public void getError(String error) {
        mTxtLocation.setText(error);
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }
}
