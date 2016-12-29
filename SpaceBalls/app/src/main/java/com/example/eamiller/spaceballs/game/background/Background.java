package com.example.eamiller.spaceballs.game.background;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.eamiller.spaceballs.game.GamePanel;
import com.example.eamiller.spaceballs.game.interfaces.drawable;

/**
 * Created by eamiller on 7.10.2016.
 */
public class Background implements drawable{
    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res){
        image = res;
        System.out.println("background constructor caled");
        dx = (int)(-5*GamePanel.widthFactor);
        //SCALING BITMAP TO PHONE DIMENSTIONS WITHOUT KEEPING ASPECT RATIO:
        image = Bitmap.createScaledBitmap(image, GamePanel.BGWIDTH,
                GamePanel.BGHEIGHT, true);
        //image = scaledImage;
    }

    public void update(){
        x+=dx;
        if(x<-(int)(GamePanel.BGWIDTH*GamePanel.widthFactor)){//-GamePanel.BGWIDTH WAS HERE
            x=0;
        }
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
        if(x < 0){
            canvas.drawBitmap(image, x+(int)(GamePanel.BGWIDTH*GamePanel.widthFactor), y, null);//X+GamePanel.BGWIDTH WAS HERE
        }

    }

    public void setDx(int dx){
        this.dx = dx;
    }
}
