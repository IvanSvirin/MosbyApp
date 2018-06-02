package com.example.isvirin.mosbyapp.presenter;

import com.example.isvirin.mosbyapp.model.GreetingGenerator;
import com.example.isvirin.mosbyapp.view.HelloWorldView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HelloWorldPresenter extends MvpBasePresenter<HelloWorldView> {
    private GreetingGenerator greetingGenerator;

    private void cancelGreetingTaskIfRunning() {
        if (greetingGenerator != null) {
            greetingGenerator.cancel(true);
        }
    }

    public void generateHello(){
        cancelGreetingTaskIfRunning();
        greetingGenerator = new GreetingGenerator("Hello", new GreetingGenerator.GreetingTaskListener() {
            @Override
            public void onGreetingGenerated(String generatedString) {
                if (isViewAttached()) {
                    getView().showHello(generatedString);
                }
            }
        });
        greetingGenerator.execute();
    }

    public void generateBye(){
        cancelGreetingTaskIfRunning();
        greetingGenerator = new GreetingGenerator("GoodBye", new GreetingGenerator.GreetingTaskListener() {
            @Override
            public void onGreetingGenerated(String generatedString) {
                if (isViewAttached()) {
                    getView().showGoodBye(generatedString);
                }
            }
        });
        greetingGenerator.execute();
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            cancelGreetingTaskIfRunning();
        }
    }

    void startInNewThread() {
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return String.valueOf((int) (Math.random() * 100));
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
