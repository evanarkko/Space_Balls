package com.example.eamiller.spaceballs.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.eamiller.spaceballs.R;
import com.example.eamiller.spaceballs.game.interfaces.drawable;
import com.example.eamiller.spaceballs.game.player.Player;

/**
 * Created by eamiller on 25.12.2016.
 */
public class GameOverDisplay implements drawable {
    private Context context;
    private boolean gameWon;
    private int x, y;
    private int replayButtonWidth;
    private int replayButtonHeight;
    private int quitButtonWidth;
    private int quitButtonHeight;
    private int backgroundWidth;
    private int backgroundHeight;
    private GamePanel gp;

    private Bitmap background;
    private Bitmap replayButtonImage;
    private Bitmap quitButtonImage;


    public GameOverDisplay(Context c, GamePanel gp, int screenWidth, int screenHeight){
        this.context = c;
        this.gp = gp;

        backgroundWidth = (int)(screenWidth/1.5);
        backgroundHeight = (int)(screenHeight/1.5);

        this.x = (screenWidth-backgroundWidth)/2;
        this.y = (screenHeight-backgroundHeight)/2;


        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_over_background_red);
        background = Bitmap.createScaledBitmap(background, backgroundWidth, backgroundHeight, true);
    }
    @Override
    public void draw(Canvas canvas) {
        int sy = (int)(140 * GamePanel.heightFactor);
        int sx = (int)(130 * GamePanel.widthFactor);
        int sy2 = (int)(90*GamePanel.heightFactor);
        int sx2 = (int)(50*GamePanel.heightFactor);

        canvas.drawBitmap(background, x, y, null);

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize((int)(100*GamePanel.widthFactor));
        canvas.drawText("GAME OVER", x + sx*2, y + sy, p);
        canvas.drawText("Score: " + gp.getPlayer().getScore(), x + sx2, y + (int)(sy*2.1), p);
        canvas.drawText("Level: " + gp.getLvlManager().getCurrentLevel(), x + sx2, y + (int)(sy*2.1)+sy2, p);

    }

    private void drawReplayButton(Canvas c){

    }

    private void drawQuitButton(Canvas c){

    }
}
