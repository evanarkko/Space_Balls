package com.evan.eamiller.spaceballs.tutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.evan.eamiller.spaceballs.R;

import java.util.ArrayList;

/**
 * Created by eamiller on 18.1.2017.
 */
public class TutorialPanel extends SurfaceView implements SurfaceHolder.Callback {
    double heightFactor, widthFactor;
    int width, height;
    private TutorialThread thread;
    private Context context;
    private int currentTutorialImageIndex = 0;
    private ArrayList<Bitmap> tutorialImages = new ArrayList<>();

    public TutorialPanel(Context context){
        super(context);
        this.context = context;
        getHolder().addCallback(this);
        setFocusable(true);



        thread = new TutorialThread(getHolder(), this);
    }

    private void addImage(int i){

        tutorialImages.add( Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), i), width,
                height, true));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //heightFactor = (1.0*getHeight())/(1.0*getHeight());
        //widthFactor = (1.0*getWidth())/(1.0*getWidth());
        width = getWidth();
        height = getHeight();
        addImage(R.drawable.tutorial_1);
        addImage(R.drawable.tutorial_2);
        addImage(R.drawable.tutorial_3);
        addImage(R.drawable.tutorial_4);


        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            currentTutorialImageIndex++;
            if(currentTutorialImageIndex>=tutorialImages.size()) currentTutorialImageIndex = 0;
        }
        return true;
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
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawBitmap(tutorialImages.get(currentTutorialImageIndex), 0, 0, null);
    }
}
