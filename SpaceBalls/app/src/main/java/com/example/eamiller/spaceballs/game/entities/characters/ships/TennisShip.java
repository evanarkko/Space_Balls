package com.example.eamiller.spaceballs.game.entities.characters.ships;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eamiller.spaceballs.R;
import com.example.eamiller.spaceballs.game.GamePanel;

/**
 * Created by eamiller on 21.12.2016.
 */
public class TennisShip extends Ship{
    public static final int tennisShipWidth = 150;
    public static final int tennisShipHeight = 75;
    private static final int TSdx = -10;

    public TennisShip(Context context, int x, int y) {
        super(context, x, y);
        this.strId = -1;
        this.weaknessId = 1;
        this.width = (int)(tennisShipWidth* GamePanel.widthFactor); height = (int)(tennisShipHeight*GamePanel.heightFactor);
        imageId1 = R.drawable.yellow_ship;
        imageId2 = R.drawable.yellow_ship2;
        currentImageId = imageId1;
        image = BitmapFactory.decodeResource(context.getResources(), currentImageId);
        image = Bitmap.createScaledBitmap(image, width, height, true);
        this.dx = (int)(TSdx*GamePanel.widthFactor);
    }

    @Override
    protected void switchImage() {
        super.switchImage();
    }

    @Override
    public void update() {
        super.update();
        updates++;
        if(updates>15){
            switchImage();
            updates=0;
        }
    }
}
