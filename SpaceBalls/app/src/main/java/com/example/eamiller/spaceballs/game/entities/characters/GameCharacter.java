package com.example.eamiller.spaceballs.game.entities.characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eamiller.spaceballs.game.interfaces.updateable;
import com.example.eamiller.spaceballs.game.interfaces.drawable;

/**
 * Created by eamiller on 20.12.2016.
 */
public abstract class GameCharacter implements drawable, updateable {
    protected Context context;
    protected Bitmap image;
    protected int x, y;
    public int width, height;
    protected int dx, dy;
    protected boolean outOfSight;
    protected boolean aBall = false;
    protected int health = 100;
    protected int power=10; //amount of damage to inflict
    protected int weaknessId; //to see if a ball matches a ships weakness and vice versa
    protected int strId; //to see if a ball matches a ships weakness and vice versa
    protected boolean bouncealbe = false; //if a ball collides w weakness, to bounce off or just get destroyed
    protected boolean bounced = false;

    protected int imageId1;
    protected int imageId2;
    protected int currentImageId;

    public boolean isOutOfSight(){
        return outOfSight;
    }
    public void setOutOfSight(boolean outOfSight){
        this.outOfSight =  outOfSight;
    }


    protected void switchImage() {
        if(currentImageId == imageId1){
            currentImageId = imageId2;
        }else{
            currentImageId = imageId1;
        }
        image = BitmapFactory.decodeResource(context.getResources(), currentImageId);
        image = Bitmap.createScaledBitmap(image, width, height, true);
    }



    public void takeDamage(int damage){
        this.health -= damage;
    }

    public void takeDamage(){}

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPower() {
        return power;
    }

    public int getHealth() {
        return health;
    }

    public int getWeaknessId() {
        return weaknessId;
    }

    public int getStrId() {
        return strId;
    }

    public boolean isBouncealbe() {
        return bouncealbe;
    }

    public boolean isBounced() {
        return bounced;
    }

    public void setDxDy(int newDx, int newDy){
        this.dx = newDx;
        this.dy = newDy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setBouncealbe(boolean bouncealbe) {
        this.bouncealbe = bouncealbe;
    }

    public void setBounced(boolean bounced) {
        this.bounced = bounced;
    }

    public boolean isaBall() {
        return aBall;
    }

    public Bitmap getImage() {
        return image;
    }


    public Bitmap getPropImage(){
        return null;
    }
}
