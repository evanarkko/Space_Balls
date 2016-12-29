package com.example.eamiller.spaceballs.game;

import com.example.eamiller.spaceballs.game.entities.characters.GameCharacter;

/**
 * Created by eamiller on 22.12.2016.
 */
public class Collider {
    public Collider(){
        
    }
    
    public boolean collide(GameCharacter gc1, GameCharacter gc2){
        return collideByWidth(gc1, gc2) & collideByHeight(gc1, gc2);
    }

    private boolean collideByWidth(GameCharacter gc1, GameCharacter gc2){
        GameCharacter onLeft;
        GameCharacter onRight;
        if(gc1.getX()<gc2.getX()){
            onLeft = gc1;
            onRight = gc2;
        }else{
            onLeft = gc2;
            onRight = gc1;
        }
        if(onRight.getX()<onLeft.getX()+onLeft.getWidth()){
            return true;
        }
        return false;
    }

    private boolean collideByHeight(GameCharacter gc1, GameCharacter gc2){
        GameCharacter onTop;
        GameCharacter onBottom;
        if(gc1.getY()<gc2.getY()){
            onTop = gc1;
            onBottom = gc2;
        }else{
            onTop = gc2;
            onBottom = gc1;
        }
        if(onBottom.getY()<onTop.getY()+onTop.getHeight()){
            return true;
        }
        return false;
    }
}
