package com.evan.eamiller.spaceballs.game.entities.characters.balls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.evan.eamiller.spaceballs.R;
import com.evan.eamiller.spaceballs.game.GamePanel;

/**
 * Created by eamiller on 23.12.2016.
 */
public class GolfBall extends Ball {
    public static final int GBWidth = 60;
    public static final int GBHeight = 60;
    public static final int GBdx = 12;
    public GolfBall(Context context, int x, int y) {
        super(context, x, y);
        this.strId=0;
        this.weaknessId = 2;//GOLFID = 2
        this.bouncealbe = true;
        width = (int)(GBWidth* GamePanel.widthFactor); height = (int)(GBHeight*GamePanel.heightFactor);
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.golf_ball);
        image = Bitmap.createScaledBitmap(image, width, height, true);
        this.dx = (int)(GBdx*GamePanel.widthFactor);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(image, x, y, null);
    }
}
