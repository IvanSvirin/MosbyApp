package com.example.isvirin.mosbyapp.model;

import android.os.AsyncTask;

public class GreetingGenerator extends AsyncTask<Void, Void, Integer> {
    public interface GreetingTaskListener {
        void onGreetingGenerated(String generatedString);
    }

    private String baseText;
    private GreetingTaskListener greetingTaskListener;

    public GreetingGenerator(String baseText, GreetingTaskListener greetingTaskListener) {
        this.baseText = baseText;
        this.greetingTaskListener = greetingTaskListener;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (int) (Math.random() * 100);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        greetingTaskListener.onGreetingGenerated(baseText + " " + integer);
    }
}
