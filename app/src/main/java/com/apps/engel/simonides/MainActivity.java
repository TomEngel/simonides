package com.apps.engel.simonides;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startDeckMemorizingSession(View view) {
        Intent intent = new Intent(this, DeckMemorizing.class);
        startActivity(intent);
    }



}
