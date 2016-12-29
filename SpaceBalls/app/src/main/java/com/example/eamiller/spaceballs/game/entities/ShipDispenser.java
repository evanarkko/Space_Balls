package com.example.eamiller.spaceballs.game.entities;

import android.content.Context;

import com.example.eamiller.spaceballs.game.entities.characters.ships.*;

/**
 * Dispenses enemy ships into gameworld
 * Created by eamiller on 21.12.2016.
 */
public class ShipDispenser{
    private Context context;
    private int amountOfShips = 1;
    private int screenHeight;
    private int screenWidth;


    public ShipDispenser(Context context){
        this.context = context;
        this.screenHeight = 1000;
    }


    public Ship tennisShip(int y){
        return new TennisShip(context, this.screenWidth, y);
    }

    public Ship golfShip(int y){
        return new GolfShip(context, this.screenWidth, y);
    }

    public Ship smallWoodenShip(int y){
        return new SmallWoodenShip(context, this.screenWidth, y);
    }

    public Ship tennisShipRandomly(){
        int y = (int)(Math.random()*(screenHeight - TennisShip.tennisShipHeight));
        //return new TennisShip(context, screenLength*(4/5), 0);
        return new TennisShip(context, this.screenWidth, y);
    }

    public Ship golfShipRandomly(){
        int y = (int)(Math.random()*(screenHeight - TennisShip.tennisShipHeight));
        //return new TennisShip(context, screenLength*(4/5), 0);
        System.out.println(this.screenWidth);
        return new GolfShip(context, this.screenWidth, y);
    }

    public Ship smallWoodenShipRandomly(){
        int y = (int)(Math.random()*(screenHeight - TennisShip.tennisShipHeight));
        //return new TennisShip(context, screenLength*(4/5), 0);
        return new SmallWoodenShip(context, this.screenWidth, y);
    }

    public void setScreenDimensions(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
}
