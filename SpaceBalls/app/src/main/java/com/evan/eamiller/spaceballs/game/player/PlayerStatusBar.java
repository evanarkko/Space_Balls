package com.evan.eamiller.spaceballs.game.player;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.evan.eamiller.spaceballs.game.GamePanel;
import com.evan.eamiller.spaceballs.game.interfaces.updateable;
import com.evan.eamiller.spaceballs.game.interfaces.drawable;

/**
 * Created by eamiller on 17.12.2016.
 */
public class PlayerStatusBar implements updateable, drawable {
    private Context context;
    private Player player;

    private int x = 500, y=500;


    public PlayerStatusBar(Context c, Player p){
        this.player = p;
        this.context = c;
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        int origTextSize = 40;
        int textSize = (int)(origTextSize* GamePanel.widthFactor);
        int origSx = 60, origSy = 40;

        Paint mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(textSize);
        int sx = (int)(origSx*GamePanel.widthFactor), sy = (int)(origSy*GamePanel.heightFactor);//sx=655, sy=510
        //canvas.drawText("Level:  ", x+sx, y+sy, mTextPaint);
        canvas.drawText("Health  " + player.getHealth() + "%", x+sx, y+sy+(int)(40*GamePanel.heightFactor), mTextPaint);
        canvas.drawText("Score  " + player.getScore(), x+sx, y+sy+(int)(80*GamePanel.heightFactor), mTextPaint);
        canvas.drawText("Balls     " + player.getAmountOfDifferentBalls(), x+sx, y+sy+(int)(120*GamePanel.heightFactor), mTextPaint);

    }


}
