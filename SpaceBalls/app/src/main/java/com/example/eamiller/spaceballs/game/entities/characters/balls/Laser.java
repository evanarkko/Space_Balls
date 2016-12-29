package com.example.eamiller.spaceballs.game.entities.characters.balls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eamiller.spaceballs.R;
import com.example.eamiller.spaceballs.game.GamePanel;

import java.util.ArrayList;

/**
 * Created by eamiller on 23.12.2016.
 */
public class Laser extends Ball {
    public static final int laserWidth = 120;
    public static final int laserHeight = 30;
    public static final int laserdx = 30;
    private ArrayList<Integer> imageIds;
    private int currentIndex = 0;
    public Laser(Context context, int x, int y) {
        super(context, x, y);
        imageIds = new ArrayList<>();
        imageIds.add(R.drawable.laser1);
        imageIds.add(R.drawable.laser2);
        imageIds.add(R.drawable.laser3);
        imageIds.add(R.drawable.laser4);
        imageIds.add(R.drawable.laser5);
        imageIds.add(R.drawable.laser6);

        width = (int)(laserWidth * GamePanel.widthFactor); height = (int)(laserHeight * GamePanel.heightFactor);
        this.dx = (int)(laserdx*GamePanel.widthFactor);
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.laser);
        image = Bitmap.createScaledBitmap(image, width, height, true);

    }

    @Override
    public void update() {
        super.update();
        currentIndex++;
        if(currentIndex >= imageIds.size()) currentIndex = 0;
        image = BitmapFactory.decodeResource(context.getResources(), imageIds.get(currentIndex));
        image = Bitmap.createScaledBitmap(image, width, height, true);
    }
}
