package com.evan.eamiller.spaceballs.game.entities.characters.balls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.evan.eamiller.spaceballs.R;
import com.evan.eamiller.spaceballs.game.GamePanel;

/**
 * Created by eamiller on 10.1.2017.
 * Plasma is a bad name, maybe come up w better
 */
public class Plasma extends Ball {
    public static final int PlasmaWidth=100;
    public static final int PlasmaHeight=100;
    public static final int Plasmadx=15;
    public static final int Plasmady=0;


    public Plasma(Context context, int x, int y) {
        super(context, x, y);this.strId = 1;
        bouncealbe=true;
        weaknessId=1;
        width = (int)(PlasmaWidth* GamePanel.widthFactor); height = (int)(PlasmaHeight*GamePanel.heightFactor);
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.plasma_missile_pb);
        image = Bitmap.createScaledBitmap(image, width, height, true);
        this.dx = (int)(Plasmadx*GamePanel.widthFactor); this.dy=(int)(Plasmady*GamePanel.heightFactor);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(-20, x+width/2, y+width/2);
        canvas.drawBitmap(image, x, y, null);
        canvas.restore();
    }
}
