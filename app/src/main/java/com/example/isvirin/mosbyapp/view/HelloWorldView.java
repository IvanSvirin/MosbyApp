package com.example.isvirin.mosbyapp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface HelloWorldView extends MvpView{
    void showHello(String helloTxt);

    void showGoodBye(String byeTxt);
}
