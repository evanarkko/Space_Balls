package com.evan.eamiller.spaceballs.game.entities.characters.balls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.evan.eamiller.spaceballs.game.GamePanel;
import com.evan.eamiller.spaceballs.game.entities.characters.GameCharacter;


/**
 * Created by eamiller on 17.12.2016.
 */
public class Ball extends GameCharacter {
    protected int ddx, ddy;
    protected boolean outOfSight = false;
    protected int combo = 0;

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
        if(combo > 2){
            Paint p = new Paint();
            p.setColor(Color.WHITE);p.setTextSize((int)(45* GamePanel.heightFactor));
            canvas.drawText("COMBOx" + combo, x, y-(int)(45*GamePanel.heightFactor), p);
        }

    }

    @Override
    public void update() {
        this.dx +=  ddx;
        this.dy += ddy;
        this.x += dx;
        this.y += dy;
    }

    public void hit(){
        combo++;
    }
    public int getCombo(){
        return combo;
    }

}
