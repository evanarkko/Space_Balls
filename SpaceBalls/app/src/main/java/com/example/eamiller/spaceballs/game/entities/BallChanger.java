package com.example.eamiller.spaceballs.game.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.eamiller.spaceballs.R;
import com.example.eamiller.spaceballs.game.GamePanel;
import com.example.eamiller.spaceballs.game.entities.characters.balls.FireBall;
import com.example.eamiller.spaceballs.game.entities.characters.balls.GolfBall;
import com.example.eamiller.spaceballs.game.entities.characters.balls.Laser;
import com.example.eamiller.spaceballs.game.entities.characters.balls.TennisBall;
import com.example.eamiller.spaceballs.game.interfaces.*;
import com.example.eamiller.spaceballs.game.player.Player;

import java.util.ArrayList;

/**
 * Created by eamiller on 23.12.2016.
 */
public class BallChanger implements drawable {
    public static final int whiteBGWidth = 120, whiteBGHeight = 120;
    private static final int imgWidth = 100, imgHeight = 100;
    private Player player;
    private int x, y;
    private int sx = 0, sy = 0;
    private int width, height;
    private int currentBallIndex;

    private ArrayList<Integer> imageIds = new ArrayList<>();
    private int tennisBallId = R.drawable.tennis_ball;
    private int golfBallId = R.drawable.golf_ball;
    private int fireBallId = R.drawable.fire_ball;
    private int laserId = R.drawable.laser4;

    private Bitmap image;
    private Bitmap whiteBackground;

    public BallChanger(Player p) {
        this.player = p;
        this.currentBallIndex = player.getBallToBeThrown();
        this.sx = 10; this.sy = 10; //HERE I ASSUME THAT THE GAME ALWAYS STARTS WITH THE TENNIS BALL
        this.whiteBackground = BitmapFactory.decodeResource(player.getContext().getResources(), R.drawable.balldisplayer);
        this.whiteBackground = Bitmap.createScaledBitmap(this.whiteBackground, (int)(120* GamePanel.widthFactor),
                (int)(120*GamePanel.widthFactor), true);

        this.image = BitmapFactory.decodeResource(player.getContext().getResources(), tennisBallId);
        this.image = Bitmap.createScaledBitmap(this.image, (int)(100*GamePanel.widthFactor),
                (int)(100*GamePanel.widthFactor), true);


    }

    public void changeBall(){
        currentBallIndex++;
        System.out.println(currentBallIndex);
        if(currentBallIndex > player.getAmountOfDifferentBalls()) currentBallIndex = 1;
        this.player.setBallToBeThrown(currentBallIndex);
        switch(currentBallIndex){
            case 1:
                this.image = BitmapFactory.decodeResource(player.getContext().getResources(), tennisBallId);
                this.image = Bitmap.createScaledBitmap(image, (int)(TennisBall.TBWidth*GamePanel.widthFactor),
                        (int)(TennisBall.TBHeight*GamePanel.heightFactor), true);
                sx = 10; sy = 10;
                break;
            case 2:
                this.image = BitmapFactory.decodeResource(player.getContext().getResources(), golfBallId);
                this.image = Bitmap.createScaledBitmap(image, (int)(GolfBall.GBWidth*GamePanel.widthFactor),
                        (int)(GolfBall.GBHeight*GamePanel.heightFactor), true);
                sx = 30; sy = 30;
                break;
            case 3:
                this.image = BitmapFactory.decodeResource(player.getContext().getResources(), fireBallId);
                this.image = Bitmap.createScaledBitmap(image, (int)(FireBall.FBWidth*GamePanel.widthFactor),
                        (int)(FireBall.FBHeight*GamePanel.heightFactor), true);
                sx = 5; sy = 25;
                break;
            case 4:
                this.image = BitmapFactory.decodeResource(player.getContext().getResources(), laserId);
                this.image = Bitmap.createScaledBitmap(image, (int)((4*Laser.laserWidth/5)*GamePanel.widthFactor),
                        (int)((4*Laser.laserHeight/5)*GamePanel.heightFactor), true);
                sx = 15; sy = 48;
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        sx =  (int)(sx*GamePanel.widthFactor);
        sy = (int)(sy*GamePanel.heightFactor);

        canvas.drawBitmap(whiteBackground, x, y, null);
        canvas.drawBitmap(image, x+sx, y+sy, null);
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
}
