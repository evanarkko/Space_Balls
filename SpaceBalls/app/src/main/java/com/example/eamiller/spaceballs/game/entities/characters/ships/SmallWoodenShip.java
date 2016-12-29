package com.example.eamiller.spaceballs.game.entities.characters.ships;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.eamiller.spaceballs.R;
import com.example.eamiller.spaceballs.game.GamePanel;

import java.util.ArrayList;

/**
 * Created by eamiller on 24.12.2016.
 */
public class SmallWoodenShip extends Ship  {
    public static final int SWSWidth = 150;
    public static final int SWSHeight = 90;
    public static final int SWSdx = -10;
    private ArrayList<Integer> boostImageIds = new ArrayList<>();
    private int currentBoostIndex = 0;

    public SmallWoodenShip(Context c, int x, int y) {
        super(c, x, y);
        this.strId = 2;
        this.weaknessId = 4;
        this.width = (int)(SWSWidth* GamePanel.widthFactor); height = (int)(SWSHeight*GamePanel.heightFactor);
        currentImageId  = R.drawable.woodship1;
        image = BitmapFactory.decodeResource(context.getResources(), currentImageId);
        image = Bitmap.createScaledBitmap(image, width, height, true);
        this.dx = (int)(SWSdx*GamePanel.widthFactor);

        this.boostWidth = 50;
        boostImageIds.add(R.drawable.fireboost_brown);
        boostImageIds.add(R.drawable.fireboost_brown2);
        boostImageIds.add(R.drawable.fireboost_brown3);
        this.boostImage = BitmapFactory.decodeResource(context.getResources(), boostImageIds.get(currentBoostIndex));
        this.boostImage = Bitmap.createScaledBitmap(this.boostImage, boostWidth, height, true);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
        canvas.drawBitmap(boostImage, x + width, y-10, null);
    }

    @Override
    public void update() {
        super.update();
       /* updates++;  THIS SLOWED THE APP DOWN LIKE A MAHFUCKA. WHY?
        if(updates>10){
            updates = 0;
            currentBoostIndex++;
            if(currentBoostIndex>boostImageIds.size())currentBoostIndex=0;
            this.boostImage = BitmapFactory.decodeResource(context.getResources(), boostImageIds.get(currentBoostIndex));
            this.boostImage = Bitmap.createScaledBitmap(this.boostImage, boostWidth, height, true);
        }*/
    }
}
