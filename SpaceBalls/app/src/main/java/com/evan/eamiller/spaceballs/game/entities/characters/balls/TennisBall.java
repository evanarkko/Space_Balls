package com.evan.eamiller.spaceballs.game.entities.characters.balls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.evan.eamiller.spaceballs.R;
import com.evan.eamiller.spaceballs.game.GamePanel;

/**
 * Created by eamiller on 17.12.2016.
 */
public class TennisBall extends Ball {
    public static final int TBWidth = 100;
    public static final int TBHeight = 100;
    public static final int TBdx = 12;
    public static final int TBdy = -3;
    private int dspin = 15;
    private int angle= 0;


    public TennisBall(Context context, int x, int y) {
        super(context, x, y);
        this.strId = 1;
        this.weaknessId = 2;
        this.bouncealbe = true;
        width = (int)(TBWidth* GamePanel.widthFactor); height = (int)(TBHeight*GamePanel.heightFactor);
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.tennis_ball);
        image = Bitmap.createScaledBitmap(image, width, height, true);
        this.dx = (int)(TBdx*GamePanel.widthFactor); this.dy=(int)(TBdy*GamePanel.heightFactor);
    }

    @Override
    public void update() {
        super.update();
        rotate();
        if(!isBounced()){
            oscillate();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.rotate(angle, x+width/2, y+width/2);
        canvas.drawBitmap(image, x, y, null);
        canvas.restore();

    }

    private void rotate(){
        angle+=dspin;
        if(dspin>360){
            dspin = (dspin-360);

        }
    }
    private void oscillate(){
        if(y<avgFlyHeight-oscillationLength){
            dy = Math.abs(dy);
        }
        if(y>avgFlyHeight+oscillationLength){
            dy = -1*Math.abs(dy);
        }
    }
}
