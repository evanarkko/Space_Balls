package com.example.eamiller.spaceballs.game.entities.characters.balls;

import android.content.Context;
import android.graphics.Canvas;

import com.example.eamiller.spaceballs.game.entities.characters.GameCharacter;


/**
 * Created by eamiller on 17.12.2016.
 */
public class Ball extends GameCharacter {//make ball oscillate
    protected int ddx, ddy;
    protected boolean outOfSight = false;
    protected int avgFlyHeight;
    protected final int oscillationLength = 15;

    public Ball(Context context, int x, int y){
        this.context = context;
        this.aBall = true;
        avgFlyHeight = y;
        dx = 12;
        this.x = x;
        this.y = y;
    }

    public boolean isOutOfSight() {
        return outOfSight;
    }

    @Override
    public void setOutOfSight(boolean outOfSight) {
        this.outOfSight = outOfSight;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public void update() {
        this.dx +=  ddx;
        this.dy += ddy;
        this.x += dx;
        this.y += dy;

    }
}
