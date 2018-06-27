package com.apps.engel.simonides;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DeckMemorizing extends AppCompatActivity  {

    private Deck deck = new Deck();
    private ArrayList<Integer> cardImages = new ArrayList<>();
    private ImageView cardView;
    private int cardCounter;

    private TextView stopwatchDisplay;

    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;

    Stopwatch timer = new Stopwatch();
    final int REFRESH_RATE = 100;


    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    timer.start(); //start timer
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    stopwatchDisplay.setText(beutifyTimeFormat(timer.getElapsedTime()));
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                    break;                                  //though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stop();//stop timer
                    stopwatchDisplay.setText(beutifyTimeFormat(timer.getElapsedTime()));
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_memorizing);

        deck.shuffle();

        assignImages();
        cardCounter =0;

        cardView = (ImageView) findViewById(R.id.myCardDeck);
        cardView.setImageResource(cardImages.get(cardCounter));

        stopwatchDisplay = findViewById(R.id.stopwatchDisplay);

    }

    private void assignImages() {
        for(int counter=0; counter<52; counter++)
        cardImages.add(getResources().getIdentifier(deck.cards.get(counter).value.toString().toLowerCase() +"_of_"+ deck.cards.get(counter).suit.toString().toLowerCase(),"drawable",getPackageName()));
    }

    public void showNextCard(View view) {
        if(cardCounter<51) {
            cardCounter++;
            cardView.setImageResource(cardImages.get(cardCounter));
        }
    }

    public void startStopwatch(View view) {
        mHandler.sendEmptyMessage(MSG_START_TIMER);
    }

    public void stopStopwatch(View view) {
        mHandler.sendEmptyMessage(MSG_STOP_TIMER);
    }

    public String beutifyTimeFormat(long elapsedMilliSec) {
        long millisec = elapsedMilliSec % 1000;
        long seconds = (elapsedMilliSec / 1000) % 60;
        long minutes = (elapsedMilliSec/1000)/60;
        return minutes+":"+seconds+":"+millisec;
    }
}
