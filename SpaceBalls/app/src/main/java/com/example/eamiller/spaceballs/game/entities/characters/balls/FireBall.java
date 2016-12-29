package com.example.eamiller.spaceballs.game.entities.characters.balls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.eamiller.spaceballs.R;
import com.example.eamiller.spaceballs.game.GamePanel;

/**
 * Created by eamiller on 23.12.2016.
 */
public class FireBall extends Ball {
    public static final int FBWidth = 100;
    public static final int FBHeight = 60;
    public static final int FBdx = 12;
    private int updates = 5;
    public FireBall(Context context, int x, int y) {
        super(context, x, y);
        this.strId = 4;
        this.imageId1 = R.drawable.fire_ball_right;
        this.imageId2 = R.drawable.fire_ball;
        width = (int)(FBWidth* GamePanel.widthFactor); height = (int)(FBHeight*GamePanel.heightFactor);
        dx = (int)(FBdx*GamePanel.widthFactor);
        image = BitmapFactory.decodeResource(context.getResources(), imageId1);
        image = Bitmap.createScaledBitmap(image, width, height, true);
        System.out.println("Gamepanel.WidthFactor = " + GamePanel.widthFactor);
        System.out.println("FIREBALL HEIGHT:" + height);
    }

    @Override
    public void update() {
        super.update();
        updates++;
        if(updates>5){
            this.switchImage();
            updates = 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
