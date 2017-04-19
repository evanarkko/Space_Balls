package com.evan.eamiller.spaceballs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    public void playGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        System.out.println("gameplaybutton pressed");
        startActivity(intent);
    }

    public void tutorial(View view){
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

    public void highScores(View view){
        Intent intent = new Intent(this, Highscores.class);
        startActivity(intent);
        System.out.println("highscorebutton pressed");

    }

    public void settings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
