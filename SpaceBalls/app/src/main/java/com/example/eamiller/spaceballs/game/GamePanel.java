package com.example.eamiller.spaceballs.game;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.eamiller.spaceballs.R;
import com.example.eamiller.spaceballs.game.entities.BallChanger;
import com.example.eamiller.spaceballs.game.entities.characters.GameCharacter;
import com.example.eamiller.spaceballs.game.entities.ShipDispenser;
import com.example.eamiller.spaceballs.game.entities.characters.balls.Ball;
import com.example.eamiller.spaceballs.game.entities.characters.ships.TennisShip;
import com.example.eamiller.spaceballs.game.interfaces.drawable;
import com.example.eamiller.spaceballs.game.background.Background;
import com.example.eamiller.spaceballs.game.interfaces.updateable;
import com.example.eamiller.spaceballs.game.player.Player;
import com.example.eamiller.spaceballs.game.player.PlayerStatusBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by eamiller on 7.10.2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback, drawable, updateable {
    private static  int lineOfShooting = 350;
    public static int BGWIDTH = 1794;
    public static int BGHEIGHT= 864;
    public static double heightFactor;
    public static double widthFactor;
    private MainThread thread;
    private LevelManager lvlManager;
    private BallChanger ballChanger;
    private Background background;
    private GameOverDisplay gameOverDisplay;

    private Player player;
    private PlayerStatusBar statusBar;
    private ArrayList<GameCharacter> ballEntities = new ArrayList<>();
    private ArrayList<GameCharacter> shipEntities = new ArrayList<>();
    private ArrayList<updateable> updateables = new ArrayList<>();
    private ArrayList<drawable> drawables = new ArrayList<>();
    private ArrayList<DeathAnimator> dAnimators = new ArrayList<>();

    private Collider collider = new Collider();;

    private Context passContext;

    private void restartGame(){
        player.setScore(0);
        player.setAmountOfDifferentBalls(1);
        player.setBallToBeThrown(1);
        this.lvlManager.setCurrentLevel(1);
        System.out.println("RESTARTED");
        thread.setRunning(true);
    }

    private int ballAlertInt = 0;




    public GamePanel(Context context){
        super(context);
        this.player = new Player(context);
        this.statusBar = player.getStatusBar();

        //add callBack to interpret events (finger tapping etc.)
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        this.lvlManager = new LevelManager(this, new ShipDispenser(context));

        //make gamePanel focusable so it can handle events
        setFocusable(true);

        //TennisShip testShip = new TennisShip(context, 600, 600);
       // this.shipEntities.add(new TennisShip(context, 600, 600));;

    }



    private void startGame(){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("height: " + getHeight());
        System.out.println("width: " + getWidth());

        heightFactor = (1.0*getHeight())/(1.0*BGHEIGHT);
        widthFactor = (1.0*getWidth())/(1.0*BGWIDTH);
        lineOfShooting *= widthFactor;
        System.out.println(heightFactor);
        System.out.println(widthFactor);

        BGHEIGHT = getHeight();
        BGWIDTH = getWidth();
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.starry_sky));
        //addSomeBalls();
        this.statusBar.setCoordinates(getWidth()/5, getHeight() - (getHeight()/5));
        this.lvlManager.setScreenDimensions(getWidth(), getHeight());
        this.ballChanger = new BallChanger(player);
        this.ballChanger.setCoordinates(getWidth()/7, getHeight() - (getHeight()/7));
        //start game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e){e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(event.getX() < lineOfShooting){
                if(player.isAbleToPassBall()){
                    Ball ball = player.passBall((int) event.getX(),(int) event.getY());
                    ballEntities.add(ball);
                }
            }else{
                //this.player.setBallToBeThrown(2);
                ballChanger.changeBall();
            }
        }
        if(!this.thread.isRunning()){
            restartGame();
        }
        return true;
    }

    public void update(){
        background.update();

        ArrayList<GameCharacter> gcs = new ArrayList<>();
        gcs.addAll(ballEntities);
        gcs.addAll(shipEntities);
        ballEntities = new ArrayList<>();
        shipEntities = new ArrayList<>();

        int i = 0;
        ArrayList<Integer> indexes = new ArrayList<>();
        for(GameCharacter g : gcs){
            g.update();
            if(g.getX()>BGWIDTH)g.setOutOfSight(true);
            if(g.getX()<-g.getWidth()){
                g.setOutOfSight(true);
                if(!g.isaBall()) player.takeDamage(g.getPower());//if a ship escaped on left, player takes damage
                if(player.getHealth()<=0)thread.setRunning(false);
                gameOverDisplay = new GameOverDisplay(getContext(), this, BGWIDTH, BGHEIGHT);
                this.draw(MainThread.canvas);
            }
            if(g.getY()>BGHEIGHT)g.setOutOfSight(true);
            if(g.getY()<-g.getHeight())g.setOutOfSight(true);
            if(g.isOutOfSight()) System.out.println("SMTH IS OUT OF SIGHT!");
            if(g.isOutOfSight()){
                indexes.add(i);
            }
            i++;
        }
        Collections.reverse(indexes);
        for(int index : indexes){
            gcs.remove(index);
        }
        for(GameCharacter gc : gcs){
            if(gc.isaBall()){
                ballEntities.add(gc);
            }else{
                shipEntities.add(gc);
            }
        }

        manageCollisions();

        for (updateable u : updateables){
            u.update();
        }

       for(DeathAnimator d : dAnimators){
            d.update();
        }
        player.update();
    }

    private void manageCollisions(){
        ArrayList<Integer> shipIndexes = new ArrayList<>();
        ArrayList<Integer> ballIndexes = new ArrayList<>();
        int i = 0;
        for(GameCharacter s : shipEntities){
            int j = 0;
            ballIndexes = new ArrayList<>();
            for(GameCharacter b : ballEntities){
                if(collider.collide(s, b)){
                    System.out.println("COLLISION!");
                    if(b.getWeaknessId() == s.getStrId()){
                        if(b.isBouncealbe()){//ball bounces off instead of getting destroyed
                            if(!b.isBounced()){
                                if(b.getY()<=s.getY()){//ball is above
                                    b.setDxDy((int)(-b.getDx()*1.5), -10);
                                }else{//ball is below
                                    b.setDxDy((int)(-b.getDx()*1.5), 10);
                                }
                                b.setBounced(true);
                            }
                        }else{
                            ballIndexes.add(j);
                        }
                        continue;
                    }else if(b.getStrId() == s.getWeaknessId()){
                        shipIndexes.add(i);
                        continue;
                    }

                    s.takeDamage(b.getPower());
                    System.out.println(s.getStrId() + " health:" + s.getHealth());//DEBUGGING
                    if(s.getHealth()<0)shipIndexes.add(i);
                }
                j++;
            }
            Collections.reverse(ballIndexes);
            for(int index : ballIndexes){
                ballEntities.remove(index);
            }
            i++;
        }
        Collections.reverse(shipIndexes);
        for(int index : shipIndexes){
            //shipEntities.remove(index);
            dAnimators.add(new DeathAnimator(passContext, shipEntities.remove(index)));
            player.setScore(player.getScore()+1);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //Scaling the drawings
        final float scaleFactorX = getWidth()/BGWIDTH;
        final float scaleFactorY = getHeight()/BGHEIGHT;


        if(canvas != null){
            final int savedState = canvas.save();
            //canvas.scale(scaleFactorX, scaleFactorY);
            background.draw(canvas);

            for(drawable d  : drawables){
                d.draw(canvas);
            }
            for(GameCharacter g :shipEntities){
                g.draw(canvas);
            }
            for(DeathAnimator d : dAnimators){
                d.draw(canvas);
            }
            for(GameCharacter g : ballEntities){
                g.draw(canvas);
            }
            statusBar.draw(canvas);
            drawShootingLine(canvas);
            ballChanger.draw(canvas);
            if(ballAlertInt>0){
                drawNewBallAlert(canvas);
            }
            drawCurrentLVL(canvas);
            canvas.restoreToCount(savedState);
        }
        if(!thread.isRunning()){
            gameOverDisplay.draw(canvas);
        }


    }

    private void drawShootingLine(Canvas c){
        int sy = (int)(15 * widthFactor);
        double ratio = 1.0*player.getUpdates()/player.getUpdatesUntilNextBall()*1.0;
        Paint redP = new Paint();
        redP.setColor(Color.RED);
        Paint greenP = new Paint();
        greenP.setColor(Color.GREEN);
        c.drawLine(lineOfShooting, 0, lineOfShooting, getHeight(), greenP);
        c.drawLine(lineOfShooting+sy, 0, lineOfShooting+sy, getHeight(), greenP);
        c.drawLine(lineOfShooting+sy*2, 0, lineOfShooting+sy*2, getHeight(), greenP);
        if(player.isCountUpdates()){
            c.drawLine(lineOfShooting, (getHeight()/2) - (int)(ratio*(getHeight()/2)), lineOfShooting, 0, redP);//top fence
            c.drawLine(lineOfShooting+sy, (getHeight()/2) - (int)(ratio*(getHeight()/2)), lineOfShooting+sy, 0, redP);
            c.drawLine(lineOfShooting+sy*2, (getHeight()/2) - (int)(ratio*(getHeight()/2)), lineOfShooting+sy*2, 0, redP);

            c.drawLine(lineOfShooting, (getHeight()/2) + (int)(ratio*(getHeight()/2)), lineOfShooting, getHeight(), redP);//bottom fence
            c.drawLine(lineOfShooting+sy, (getHeight()/2) + (int)(ratio*(getHeight()/2)), lineOfShooting+sy, getHeight(), redP);
            c.drawLine(lineOfShooting+sy*2, (getHeight()/2) + (int)(ratio*(getHeight()/2)), lineOfShooting+sy*2, getHeight(), redP);
        }

    }

    private void drawCurrentLVL(Canvas c){
        int textSize = 70;
        int scaledTextSize = (int)(textSize*widthFactor);
        Paint mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(scaledTextSize);
        c.drawText("Level: " + this.lvlManager.getCurrentLevel(), (float)(getWidth()/(2.4)), (int)(100*heightFactor), mTextPaint);
    }

    private void drawNewBallAlert(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setTextSize((int)(100*GamePanel.heightFactor));
        if(ballAlertInt<100){
            c.drawText("New Ball!", getWidth()/2-(int)(300*GamePanel.widthFactor), getHeight()/2, p);
        }else{
            c.drawText("Touch Right to change ball", getWidth()/2-(int)(600*GamePanel.widthFactor), (int)(getHeight()/2+120*GamePanel.heightFactor), p);
        }
        ballAlertInt++;
        if(ballAlertInt>200) ballAlertInt = 0;
    }

    public void drawNewBallAlert(){
        ballAlertInt++;
    }

    public ArrayList<GameCharacter> getShipEntities() {
        return shipEntities;
    }

    public LevelManager getLvlManager() {
        return lvlManager;
    }

    public Player getPlayer() {
        return player;
    }
}
