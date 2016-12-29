package com.example.eamiller.spaceballs.game.entities.characters.ships;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.Image;

import com.example.eamiller.spaceballs.game.entities.characters.GameCharacter;

/**
 * Created by eamiller on 21.12.2016.
 */
public class Ship extends GameCharacter {
    protected int updates = 0;
    protected Bitmap boostImage;
    protected int bodyWidth; //maybe get rid of this and use (width + boostwidth) as width (boostwidth is 0 for those without boost)
    protected int boostWidth;

    public Ship(Context c, int x, int y){
        this.context = c;
        this.x = x;
        this.y = y;
    }
    /*public Ship(Context c, int x, int y, int dx){
        this.context = c;
        this.x = x;
        this.y = y;
        this.dx = dx;
    }*/


    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public void update() {
        this.x += dx;
        this.y += dy;
    }





}
