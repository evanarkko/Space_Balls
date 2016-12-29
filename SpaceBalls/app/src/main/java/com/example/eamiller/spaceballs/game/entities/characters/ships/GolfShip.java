package com.example.eamiller.spaceballs.game.entities.characters.ships;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eamiller.spaceballs.R;
import com.example.eamiller.spaceballs.game.GamePanel;

/**
 * Created by eamiller on 21.12.2016.
 */
public class GolfShip extends Ship {
    public static final int GSWidth = 150;
    public static final int GSHeight = 75;
    public static final int GSdx = -12;
    public GolfShip(Context c, int x, int y) {
        super(c, x, y);
        this.width = (int)(GSWidth*GamePanel.widthFactor); this.height = (int)(GSHeight*GamePanel.heightFactor);
        imageId1 = R.drawable.white_ship1;
        imageId2 = R.drawable.white_ship2;
        currentImageId = imageId1;
        image = BitmapFactory.decodeResource(context.getResources(), currentImageId);
        image = Bitmap.createScaledBitmap(image, width, height, true);
        this.dx = (int)(GSdx*GamePanel.widthFactor);
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
