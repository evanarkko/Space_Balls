package com.evan.eamiller.spaceballs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evan.eamiller.spaceballs.tutorial.TutorialPanel;

public class TutorialActivity extends AppCompatActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TutorialPanel(this));
    }
}