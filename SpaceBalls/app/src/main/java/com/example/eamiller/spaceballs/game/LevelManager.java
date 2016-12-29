package com.example.eamiller.spaceballs.game;

import com.example.eamiller.spaceballs.game.entities.ShipDispenser;
import com.example.eamiller.spaceballs.game.entities.characters.ships.Ship;
import com.example.eamiller.spaceballs.game.interfaces.updateable;

import java.util.ArrayList;

/**
 * Created by eamiller on 21.12.2016.
 */
public class LevelManager implements updateable {
    private int currentLevel = 1;
    private GamePanel gp;
    private ShipDispenser shipDispenser;
    private int sloI = 0;
    private int i = 0;
    private int j = 0;
    private int k;
    private int slointerval = 55;
    private int iInterval = 45;
    private int jInterval = 35;
    private int kInterval = 25;

    private ArrayList<Ship> shipContainer = new ArrayList<>();
    private int shipIndex = 0;


    public LevelManager(GamePanel gp, ShipDispenser sd){
        this.gp = gp;
        this.shipDispenser = sd;
    }

    public void actOnGameWorld(){
        switch (currentLevel){
            case 1:
                if(shipContainer.isEmpty()){
                    shipContainer.addAll(tennisShips(5, 100, 800));
                    shipContainer.addAll(tennisShips(2, 750, 10));
                    shipContainer.addAll(tennisShips(3, 100, 800));
                    shipContainer.addAll(tennisShips(2, 750, 10));
                }
                i++;
                if(i > iInterval){
                    i = 0;
                    checkIfNextLevel();
                }
                break;
            case 2:
                if(shipContainer.isEmpty()){
                    shipContainer.addAll(tennisShips(8, 100, 500));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                    shipContainer.addAll(golfShips(3, 800, 50));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                }
                i++;
                if(i > iInterval){
                    i = 0;
                    checkIfNextLevel();
                }
                break;
            case 3:
                if(shipContainer.isEmpty()){
                    shipContainer.addAll(tennisShips(6, 700, 300));
                    shipContainer.addAll(golfShips(2, 100, 750));
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                }
                i++;
                if(i > iInterval){
                    i = 0;
                    checkIfNextLevel();
                }
                break;
            case 4:
                if(shipContainer.isEmpty()){
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(3, 750, 80));
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(6, 700, 300));
                    shipContainer.addAll(golfShips(4, 100, 750));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                    shipContainer.addAll(golfShips(3, 300, 400));
                    shipContainer.addAll(tennisShips(5, 750, 80));
                    shipContainer.addAll(golfShips(2, 300, 400));
                }
                if(shipIndex < 9){
                    sloI++;
                    if(sloI > slointerval){
                        sloI = 0;
                        checkIfNextLevel();
                    }
                }else{
                    i++;
                    if(i > iInterval){
                        i = 0;
                        checkIfNextLevel();
                    }
                }
                break;
            case 5:
                if(shipContainer.isEmpty()){
                    gp.getPlayer().setAmountOfDifferentBalls(2);
                    //gp.infromPlayerAboutNewBall();
                    shipContainer.addAll(golfShips(2, 100, 840));
                    shipContainer.addAll(tennisShips(6, 700, 300));
                    shipContainer.addAll(golfShips(2, 100, 750));
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(4, 750, 80));
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(8, 700, 300));
                    shipContainer.addAll(golfShips(2, 170, 550));
                    shipContainer.addAll(tennisShips(3, 750, 80));
                }
                sloI++;
                if(sloI > slointerval){
                    sloI = 0;
                    checkIfNextLevel();
                }
                i++;
                if(i > iInterval){
                    i = 0;
                    checkIfNextLevel();
                }
                break;
            case 6:
                if(shipContainer.isEmpty()){
                    gp.getPlayer().setAmountOfDifferentBalls(2);
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(3, 750, 80));
                    shipContainer.addAll(golfShips(4, 100, 400));
                    shipContainer.addAll(tennisShips(2, 500, 400));
                    shipContainer.addAll(golfShips(4, 100, 750));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                    shipContainer.addAll(golfShips(3, 300, 400));
                    shipContainer.addAll(tennisShips(5, 750, 80));
                    shipContainer.addAll(golfShips(2, 300, 400));
                }
                if(shipIndex < 9){
                    sloI++;
                    if(sloI > slointerval){
                        sloI = 0;
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        checkIfNextLevel();
                    }
                }else{
                    i++;
                    if(i > jInterval){
                        i = 0;
                        checkIfNextLevel();
                    }
                }
                break;
            case 7:
                if(shipContainer.isEmpty()){
                    gp.getPlayer().setAmountOfDifferentBalls(2);
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(3, 750, 80));
                    shipContainer.addAll(golfShips(4, 100, 400));
                    shipContainer.addAll(tennisShips(2, 500, 400));
                    shipContainer.addAll(golfShips(4, 100, 750));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                    shipContainer.addAll(golfShips(3, 300, 400));
                    shipContainer.addAll(tennisShips(5, 750, 80));
                    shipContainer.addAll(golfShips(2, 300, 400));
                }
                if(shipIndex < 9){
                    sloI++;
                    if(sloI > slointerval){
                        sloI = 0;
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        checkIfNextLevel();
                    }
                }else{
                    i++;
                    if(i > jInterval){
                        i = 0;
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        checkIfNextLevel();
                    }
                }
                break;
            case 8:
                if(shipContainer.isEmpty()){
                    gp.getPlayer().setAmountOfDifferentBalls(2);
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(3, 750, 80));
                    shipContainer.addAll(golfShips(2, 100, 400));
                    shipContainer.addAll(tennisShips(2, 500, 400));
                    shipContainer.addAll(golfShips(2, 100, 750));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                    shipContainer.addAll(golfShips(2, 300, 400));
                }
                if(shipIndex < 9){
                    sloI++;
                    if(sloI > slointerval){
                        sloI = 0;
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                        checkIfNextLevel();
                    }
                }else{
                    i++;
                    if(i > jInterval){
                        i = 0;
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        checkIfNextLevel();
                    }
                }
                break;
            case 9:
                if(shipContainer.isEmpty()){
                    gp.getPlayer().setAmountOfDifferentBalls(2);
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(5, 750, 80));
                    shipContainer.addAll(golfShips(2, 100, 400));
                    shipContainer.addAll(tennisShips(2, 550, 100));
                    shipContainer.addAll(golfShips(2, 100, 750));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                    shipContainer.addAll(golfShips(2, 550, 400));
                    shipContainer.addAll(golfShips(2, 300, 400));
                    shipContainer.addAll(tennisShips(2, 750, 80));
                    shipContainer.addAll(golfShips(2, 300, 400));
                }
                if(shipIndex < 9){
                    sloI++;
                    if(sloI > slointerval){
                        sloI = 0;
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        checkIfNextLevel();
                    }
                }else{
                    i++;
                    if(i > jInterval){
                        i = 0;
                        gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                        gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                        checkIfNextLevel();
                    }
                }
                break;
            case 10:
                //first wave passed, give fireball
                break;
        }
    }

    private void checkIfNextLevel(){
        shipIndex++;
        if(shipIndex>=shipContainer.size()){
            currentLevel++;
            shipContainer.clear();
            shipIndex = 0;
        }else{
            gp.getShipEntities().add(shipContainer.get(shipIndex));
        }
    }

    private ArrayList<Ship> tennisShips(int amount, int startY, int endY){
        int y1 = (int)(startY*GamePanel.heightFactor);
        int y2 = (int)(endY*GamePanel.widthFactor);
        ArrayList<Ship> ships = new ArrayList<>();
        int sy = (y2-y1)/amount;
        for(int i = 0; i < amount; i++){
            ships.add(shipDispenser.tennisShip(startY+i*sy));
        }
        return ships;
    }

    private ArrayList<Ship> golfShips(int amount, int startY, int endY){
        int y1 = (int)(startY*GamePanel.heightFactor);
        int y2 = (int)(endY*GamePanel.widthFactor);
        ArrayList<Ship> ships = new ArrayList<>();
        int sy = (y2-y1)/amount;
        for(int i = 0; i < amount; i++){
            ships.add(shipDispenser.golfShip(startY+i*sy));
        }
        return ships;
    }

    @Override
    public void update() {

    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setScreenDimensions(int x, int y){
        this.shipDispenser.setScreenDimensions(x, y);
    }
}
