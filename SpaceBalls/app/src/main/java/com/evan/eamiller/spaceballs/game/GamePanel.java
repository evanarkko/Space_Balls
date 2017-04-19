package com.evan.eamiller.spaceballs.game;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.evan.eamiller.spaceballs.R;
import com.evan.eamiller.spaceballs.game.entities.BallChanger;
import com.evan.eamiller.spaceballs.game.entities.characters.GameCharacter;
import com.evan.eamiller.spaceballs.game.entities.ShipDispenser;
import com.evan.eamiller.spaceballs.game.entities.characters.balls.Ball;
import com.evan.eamiller.spaceballs.game.interfaces.drawable;
import com.evan.eamiller.spaceballs.game.background.Background;
import com.evan.eamiller.spaceballs.game.interfaces.updateable;
import com.evan.eamiller.spaceballs.game.player.Player;
import com.evan.eamiller.spaceballs.game.player.PlayerStatusBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by eamiller on 7.10.2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback, drawable, updateable {
    private static  int lineOfShooting = 350;
    public static final int BGWIDTH = 1794;
    public static final int BGHEIGHT= 1008;
    public static double heightFactor;
    public static double widthFactor;
    private MainThread thread;
    private LevelManager lvlManager;
    private BallChanger ballChanger;
    private Background background;
    private GameOverDisplay gameOverDisplay;
    private boolean gameOver = false;

    private Player player;
    private PlayerStatusBar statusBar;
    private ArrayList<GameCharacter> ballEntities = new ArrayList<>();
    private ArrayList<GameCharacter> shipEntities = new ArrayList<>();
    private ArrayList<updateable> updateables = new ArrayList<>();
    private ArrayList<drawable> drawables = new ArrayList<>();
    private ArrayList<DeathAnimator> dAnimators = new ArrayList<>();

    private Collider collider = new Collider();

    private void restartGame(){
        this.shipEntities.clear();
        player.setScore(0);
        player.setHealth(Player.MAX_HEALTH);
        player.setAmountOfDifferentBalls(1);
        player.setBallToBeThrown(1);//        DOESNT WORK?? BALL STAYS AS BALL LEFT WITH
        this.lvlManager.setCurrentLevel(1);
        this.lvlManager.setShipsDeployed(0);
        System.out.println("RESTARTED GAME");
        gameOver=false;

        //thread.setRunning(true);
        //thread.start();
    }

    private int ballAlertInt = 0;




    public GamePanel(Context context){
        super(context);
        this.player = new Player(context);
        this.statusBar = player.getStatusBar();

        //add callBack to interpret events (finger tapping etc.)
        getHolder().addCallback(this);
        //make gamePanel focusable so it can handle events
        setFocusable(true);

        thread = new MainThread(getHolder(), this);
        this.lvlManager = new LevelManager(this, new ShipDispenser(context));

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        heightFactor = (1.0*getHeight())/(1.0*BGHEIGHT);
        widthFactor = (1.0*getWidth())/(1.0*BGWIDTH);
        lineOfShooting *= widthFactor;
        System.out.println(heightFactor);
        System.out.println(widthFactor);

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
                ballChanger.changeBall();
            }
        }
        if(this.gameOver){
            System.out.println("touchevent happening w gameover");
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
                if(!g.isaBall()&&!gameOver) player.takeDamage(g.getPower());//if a ship escaped on left, player takes damage (if game is over, damage isnt taken)
                if(player.getHealth()<=0)gameOver=true;//THE GAME IS OVER
                gameOverDisplay = new GameOverDisplay(getContext(), this, BGWIDTH, BGHEIGHT);
                this.draw(MainThread.canvas);
            }
            if(g.getY()>BGHEIGHT)g.setOutOfSight(true);
            if(g.getY()<-g.getHeight())g.setOutOfSight(true);
            if(g.isOutOfSight()){
                indexes.add(i);
            }
            i++;
        }
        Collections.reverse(indexes);
        for(int index : indexes){
            //if gc is a ball and has a combo, add extra pts here
            GameCharacter gc = gcs.get(index);
            if(gc.isaBall()){
                int combo = ((Ball) gc).getCombo();
                if(combo>2){
                    player.setScore(player.getScore()+combo);
                }
            }
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

        ArrayList<Integer> dIndexes = new ArrayList<>();
        int dIndex = 0;
       for(DeathAnimator d : dAnimators){//DELETE DEATH ANIMATORS HERE. NOW I GOING TO SQUEEZE ONE OFF
            d.update();
           if(d.getY()>getHeight()) {//delete danimator;
               dIndexes.add(dIndex);
           }
           dIndex++;
        }
        Collections.reverse(dIndexes);
        for(int index : dIndexes){
            dAnimators.remove(index);
        }

        player.update();
    }

    private void manageCollisions(){
        ArrayList<Integer> shipIndexes = new ArrayList<>();
        ArrayList<Integer> ballIndexes;
        int i = 0;
        for(GameCharacter s : shipEntities){
            int j = 0;
            ballIndexes = new ArrayList<>();
            for(GameCharacter b : ballEntities){
                if(collider.collide(s, b)){
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
                        ((Ball) b).hit();
                        continue;
                    }

                    s.takeDamage(b.getPower());
                    System.out.println(s.getStrId() + " health:" + s.getHealth());//DEBUGGING
                    if(s.getHealth()<0){
                        shipIndexes.add(i);
                        ((Ball) b).hit();
                    }
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
            dAnimators.add(new DeathAnimator(shipEntities.remove(index)));
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
        if(gameOver){
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

    public boolean isGameOver() {
        return gameOver;
    }
}
