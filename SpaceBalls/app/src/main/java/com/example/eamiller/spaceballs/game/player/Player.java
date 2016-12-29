package com.example.eamiller.spaceballs.game.player;

import android.content.Context;

import com.example.eamiller.spaceballs.game.entities.characters.balls.Ball;
import com.example.eamiller.spaceballs.game.entities.characters.balls.*;
import com.example.eamiller.spaceballs.game.interfaces.updateable;



/**
 * Created by eamiller on 17.12.2016.
 */
public class Player implements updateable {
    public static final int MAX_HEALTH = 100;
    private Context context;
    private PlayerStatusBar statusBar;
    private int health = MAX_HEALTH;
    private int score;
    private int ballToBeThrown = 1; //järjestysnumero palloille (esim 0-tennis, 1-golf...)
    private int amountOfDifferentBalls; //yläraja balltobethrown-luvulle
    private boolean ableToPassBall = true;
    private int updates = 0;
    private int updatesUntilNextBall = 50;
    private boolean countUpdates = false;


    public Player(Context context){
        this.statusBar = new PlayerStatusBar(context, this);
        this.context = context;
        this.score = 0;
        this.amountOfDifferentBalls = 1;
    }


    @Override
    public void update() {
        if(countUpdates){
            updates++;
            if(updates>updatesUntilNextBall){
                updates = 0;
                ableToPassBall = true;
                countUpdates = false;
            }
        }
        this.statusBar.update();//doesnt do anything
    }

    public Ball passBall(int x, int y){
        ableToPassBall=false;
        countUpdates = true;
        System.out.println("one ball thrown");
        switch (ballToBeThrown){
            case 1:
                setUpdatesUntilNextBall(40);
                return new TennisBall(context, x+10, y-TennisBall.TBHeight/2);
            case 2:
                setUpdatesUntilNextBall(22);
                return new GolfBall(context, x+10, y-GolfBall.GBHeight/2);
            case 3:
                setUpdatesUntilNextBall(35);
                return new FireBall(context, x+10, y-FireBall.FBHeight/2);
            case 4:
                setUpdatesUntilNextBall(15);
                return new Laser(context, x+10, y-Laser.laserHeight/2);
        }

        return null;
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }

    public PlayerStatusBar getStatusBar() {
        return statusBar;
    }

    public int getAmountOfDifferentBalls() {
        return amountOfDifferentBalls;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public void setBallToBeThrown(int ballToBeThrown) {
        this.ballToBeThrown = ballToBeThrown;
    }

    public void setUpdatesUntilNextBall(int updatesUntilNextBall) {
        this.updatesUntilNextBall = updatesUntilNextBall;
    }

    public boolean isAbleToPassBall() {
        return ableToPassBall;
    }

    public Context getContext() {
        return context;
    }

    public int getBallToBeThrown() {
        return ballToBeThrown;
    }

    public int getUpdates() {
        return updates;
    }

    public int getUpdatesUntilNextBall() {
        return updatesUntilNextBall;
    }

    public boolean isCountUpdates() {
        return countUpdates;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAmountOfDifferentBalls(int amountOfDifferentBalls) {
        this.amountOfDifferentBalls = amountOfDifferentBalls;
    }
}
