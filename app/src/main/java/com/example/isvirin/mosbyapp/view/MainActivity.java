package com.example.isvirin.mosbyapp.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.isvirin.mosbyapp.R;
import com.example.isvirin.mosbyapp.presenter.HelloWorldPresenter;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<HelloWorldView, HelloWorldPresenter> implements HelloWorldView{
    @BindView(R.id.textViewHelloBye)
    TextView textViewHelloBye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public HelloWorldPresenter createPresenter() {
        return new HelloWorldPresenter();
    }

    @OnClick(R.id.buttonHello)
    public void onButtonHelloClicked(){
        presenter.generateHello();
    }

    @OnClick(R.id.buttonBye)
    public void onButtonByeClicked(){
        presenter.generateBye();
    }

    @Override
    public void showHello(String helloTxt) {
        textViewHelloBye.setText(helloTxt);
    }

    @Override
    public void showGoodBye(String byeTxt) {
        textViewHelloBye.setText(byeTxt);
    }}
