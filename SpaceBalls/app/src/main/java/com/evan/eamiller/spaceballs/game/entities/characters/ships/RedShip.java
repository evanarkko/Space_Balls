package com.evan.eamiller.spaceballs.game.entities.characters.ships;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.evan.eamiller.spaceballs.R;
import com.evan.eamiller.spaceballs.game.GamePanel;

/**
 * Created by eamiller on 7.1.2017.
 */
public class RedShip extends Ship {
    public static final int RedShipWidth = 200;
    public static final int RedShipHeight = 120;
    public static final int RedShipdx = -20;
    public RedShip(Context c, int x, int y) {
        super(c, x, y);
        this.strId = -5;
        this.weaknessId = -5;
        health=350;
        this.width = (int)(RedShipWidth * GamePanel.widthFactor); height = (int)(RedShipHeight *GamePanel.heightFactor);
        currentImageId  = R.drawable.woodship1;
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.redship);
        image = Bitmap.createScaledBitmap(image, width, height, true);
        this.dx = (int)(RedShipdx *GamePanel.widthFactor);
    }
}
