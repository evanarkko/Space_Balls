package com.example.eamiller.spaceballs.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.eamiller.spaceballs.game.entities.characters.GameCharacter;
import com.example.eamiller.spaceballs.game.interfaces.*;

/**
 * Created by eamiller on 28.12.2016.
 */
public class DeathAnimator implements drawable, updateable{
    private Context context;
    private int ddx = 2;
    private int dy = 5;
    private int dSpin = -2;
    private int angle;
    private GameCharacter gameCharacter;
    private Bitmap shipImage;
    private Bitmap propImage = null;//perhaps a prop sprite

    public DeathAnimator(Context context, GameCharacter gameCharacter){
        this.context = context;
        this.gameCharacter = gameCharacter;
        gameCharacter.setDxDy((int)(gameCharacter.getDx()*0.4) ,5);
        this.shipImage = gameCharacter.getImage();
        this.propImage = gameCharacter.getPropImage();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, gameCharacter.getX()+ gameCharacter.getWidth()/2, gameCharacter.getY()+ gameCharacter.getHeight()/2);
        canvas.drawBitmap(shipImage, gameCharacter.getX(), gameCharacter.getY(), null);
        canvas.restore();
        if(propImage != null)
            canvas.drawBitmap(shipImage, gameCharacter.getX(), gameCharacter.getY(), null);
        update();
    }

    @Override
    public void update() {

        rotate();
    }

    private void rotate(){
        angle+=dSpin;
        if(dSpin>360){
            dSpin = (dSpin-360);
        }
        gameCharacter.update();

    }

}
