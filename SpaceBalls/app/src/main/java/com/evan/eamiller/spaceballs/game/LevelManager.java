package com.evan.eamiller.spaceballs.game;

import com.evan.eamiller.spaceballs.game.entities.ShipDispenser;
import com.evan.eamiller.spaceballs.game.entities.characters.ships.Ship;
import com.evan.eamiller.spaceballs.game.interfaces.updateable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by eamiller on 21.12.2016.
 */
public class LevelManager implements updateable {
    private int currentLevel = 1;
    private GamePanel gp;
    private ShipDispenser shipDispenser;
    private int pauseMeter = 0;
    private int sloI = 0;
    private int i = 0;
    private int j = 0;
    private int k;
    private int interval5 = 65;
    private int interval4 = 55;
    private int interval3 = 45;
    private int interval2 = 35;
    private int interval1 = 25;
    private int interval0 = 1;

    //maybe useless
    private ArrayList<Ship> shipContainer = new ArrayList<>();
    private int shipIndex = 0;


    //new level changing logic
    private int shipsDeployed = 0;
    private int shipsInCurrentLvl;
    private HashMap<Integer, Integer> levelShipCounts = new HashMap<>();

    public LevelManager(GamePanel gp, ShipDispenser sd){
        this.gp = gp;
        this.shipDispenser = sd;
        addLevelShipCounts();
        shipsInCurrentLvl=levelShipCounts.get(currentLevel);
        gp.getPlayer().setAmountOfDifferentBalls(1);
    }

    private void addLevelShipCounts(){
        levelShipCounts.put(0,10);
        for(int i = 1; i < 7; i++){
            levelShipCounts.put(i, 10+i*7);
        }
        levelShipCounts.put(7, 1);
        levelShipCounts.put(8, 15);
        for(int i = 9; i < 16; i++){
            levelShipCounts.put(i, 25);
        }
    }

    public void actOnGameWorld(){
        switch (currentLevel){
            case 0:
                i++;
                if(i > interval0){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.redShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                break;
            case 1:
                i++;
                if(i > interval2){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                break;
            case 2:
                i++;
                if(i > interval2){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                break;
            case 3:
                i++;
                if(i > interval2){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                j++;
                if(j > interval4){
                    j = 0;
                    gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                break;
            case 4:
                i++;
                if(i > interval3){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                j++;
                if(j > interval2){
                    j = 0;
                    gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                break;
            case 5:
                i++;
                if(i > interval2){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                    gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                    shipsDeployed+=2;
                    checkIfNextLevel();
                }
                break;
            case 6:
                i++;
                if(i > interval1){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                j++;
                if(j > interval3){
                    j = 0;
                    gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                break;
            case 7:
                i++;
                if(i > interval4){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                j++;
                if(j > interval3){
                    j = 0;
                    gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                    gp.getShipEntities().add(shipDispenser.golfShipRandomly());
                    shipsDeployed+=2;
                    checkIfNextLevel();
                }
                break;
            case 8://Fireball given here
                j++;
                if(j > interval3){
                    j = 0;
                    gp.getShipEntities().add(shipDispenser.smallWoodenShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                break;
            case 9:
                i++;
                if(i > interval5){
                    i = 0;
                    gp.getShipEntities().add(shipDispenser.smallWoodenShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                j++;
                if(j > interval3){
                    j = 0;
                    gp.getShipEntities().add(shipDispenser.tennisShipRandomly());
                    shipsDeployed++;
                    checkIfNextLevel();
                }
                break;
            case 10://first wave passed, give fireball //Colorful ships here??
                deployShipsWithIndexi(shipDispenser.smallWoodenShipRandomly(), interval1);
                break;
            case 11:
                deployShipsWithIndexi(shipDispenser.smallWoodenShip(GamePanel.BGHEIGHT-150), interval1);
                deployShipsWithIndexk(shipDispenser.smallWoodenShip(150), interval1);
                break;
            case 12:
                deployShipsWithIndexi(shipDispenser.smallWoodenShipRandomly(), interval2);
                deployShipsWithIndexj(shipDispenser.smallWoodenShipRandomly(), interval2);
                break;
            case 13:
                deployShipsWithIndexi(shipDispenser.smallWoodenShipRandomly(), interval2);
                deployShipsWithIndexj(shipDispenser.redShipRandomly(), interval5);
                break;
            case 14:
                deployShipsWithIndexi(shipDispenser.smallWoodenShipRandomly(), interval3);
                deployShipsWithIndexj(shipDispenser.tennisShipRandomly(), interval4);
                deployShipsWithIndexk(shipDispenser.golfShipRandomly(), interval5);
                break;
            case 15:
                deployShipsWithIndexi(shipDispenser.redShipRandomly(), interval5);
                break;
            case 16:
                deployShipsWithIndexi(shipDispenser.redShipRandomly(), interval5);
                deployShipsWithIndexj(shipDispenser.tennisShipRandomly(), interval3);
                break;
        }

    }

    private void deployShipsWithIndexi(Ship ship, int interval){
        i++;
        if(i > interval){
            i = 0;
            gp.getShipEntities().add(ship);
            shipsDeployed++;
            checkIfNextLevel();
        }
    }
    private void deployShipsWithIndexj(Ship ship, int interval){
        j++;
        if(j > interval){
            j = 0;
            gp.getShipEntities().add(ship);
            shipsDeployed++;
            checkIfNextLevel();
        }
    }
    private void deployShipsWithIndexk(Ship ship, int interval){
        k++;
        if(k > interval){
            k = 0;
            gp.getShipEntities().add(ship);
            shipsDeployed++;
            checkIfNextLevel();
        }
    }

    private void checkIfNextLevel(){
        if(!gp.isGameOver()){
            if(shipsDeployed>=shipsInCurrentLvl){
                shipsDeployed=0;
                currentLevel++;
                pauseMeter = 150;//makes this.isPaused() return true x times
                System.out.println("Currentlevel: " + currentLevel);
                shipsInCurrentLvl = levelShipCounts.get(currentLevel);



                if(currentLevel == 2){
                    gp.getPlayer().setAmountOfDifferentBalls(2);
                    gp.drawNewBallAlert();
                }else if(currentLevel == 8){
                    gp.getPlayer().setAmountOfDifferentBalls(3);
                    gp.drawNewBallAlert();
                }else if(currentLevel == 15){
                    gp.getPlayer().setAmountOfDifferentBalls(4);
                    gp.drawNewBallAlert();
                }

            }
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

    public boolean isPaused(){//in order to make delay in between levels
        if(pauseMeter==0){
            return false;
        }else{
            pauseMeter--;
            return true;
        }
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

    public void setShipsDeployed(int shipsDeployed) {
        this.shipsDeployed = shipsDeployed;
    }
}
