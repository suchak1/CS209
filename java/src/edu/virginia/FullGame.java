package edu.virginia;

import edu.virginia.engine.display.*;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class FullGame extends Game{

    int score = 0;
    double level = 1;
    Minigame curminigame;
    boolean vlast = false;
    boolean uplast = false;
    boolean downlast = false;
    boolean leftlast = false;
    boolean rightlast = false;
    boolean collisionlast = false;
    boolean success = false;

    ArrayList<DisplayObject> gameObjectsL1 = new ArrayList<DisplayObject>();
    ArrayList<DisplayObject> gameObjectsL2 = new ArrayList<DisplayObject>();
    ArrayList<DisplayObject> gameObjectsL3 = new ArrayList<DisplayObject>();
    ArrayList<DisplayObject> inventory = new ArrayList<DisplayObject>();

    public void setGameObjectsL1(ArrayList<DisplayObject> go) {this.gameObjectsL1 = go;}
    public ArrayList<DisplayObject> getGameObjectsL1() {return gameObjectsL1;}
    public void setGameObjectsL2(ArrayList<DisplayObject> go) {this.gameObjectsL2 = go;}
    public ArrayList<DisplayObject> getGameObjectsL2() {return gameObjectsL2;}
    public void setGameObjectsL3(ArrayList<DisplayObject> go) {this.gameObjectsL3 = go;}
    public ArrayList<DisplayObject> getGameObjectsL3() {return gameObjectsL3;}

    // ##### All Levels #####

    AnimatedSprite mario = new AnimatedSprite("Brock", "brockD2.png", new Point(425,275));
    SoundManager x = new SoundManager("xx3");


    // ##### Level 1 #####

    AnimatedSprite stairs1 = new AnimatedSprite("Stairs1", "stairs1.png", new Point(75,-250));
    AnimatedSprite key1 = new AnimatedSprite("Key1", "key1.png", new Point(275,500));
    AnimatedSprite puzzle1 = new AnimatedSprite("Puzzle1", "puzzle7.png", new Point(240,465));

    Dial dial1m1l1 = new Dial("hawk2.png", "hawk2.png", "butterfly1.png", "crab1.png", "elephant1.png", new Point(600, 400));
    Dial dial2m1l1 = new Dial("crab1.png", "crab1.png", "butterfly1.png", "hawk2.png", "elephant1.png", new Point(100, 400));
    Dial dial3m1l1 = new Dial("butterfly1.png", "butterfly1.png", "hawk2.png", "crab1.png", "elephant1.png", new Point(350, 100));

    Minigame minigame1L1;

    // ##### Level 2 #####

    AnimatedSprite stairs2 = new AnimatedSprite("Stairs1", "stairs1.png", new Point(5250,0));
    AnimatedSprite key2 = new AnimatedSprite("Key1", "key2.png", new Point(2524,500));
    AnimatedSprite puzzle2 = new AnimatedSprite("Puzzle1", "puzzle9.png", new Point(2475,450));

    Dial dial1m1l2 = new Dial("hawk2.png", "hawk2.png", "butterfly1.png", "crab1.png", "elephant1.png", new Point(600, 400));
    Dial dial2m1l2 = new Dial("crab1.png", "crab1.png", "butterfly1.png", "hawk2.png", "elephant1.png", new Point(100, 400));
    Dial dial3m1l2 = new Dial("butterfly1.png", "butterfly1.png", "hawk2.png", "crab1.png", "elephant1.png", new Point(350, 100));

    Minigame minigame1L2;

    // ##### Level 3 #####

    AnimatedSprite stairs3 = new AnimatedSprite("Stairs1", "door.png", new Point(900,-1050));
    AnimatedSprite stairs4 = new AnimatedSprite("Stairs1", "door1.png", new Point(1500,-250));
    AnimatedSprite key3 = new AnimatedSprite("Key1", "key3.png", new Point(3000,-200));
    AnimatedSprite key4 = new AnimatedSprite("Key1", "key4.png", new Point(1125,500));
    AnimatedSprite puzzle3 = new AnimatedSprite("Puzzle1", "puzzle4.png", new Point(2925,-235));
    AnimatedSprite puzzle4 = new AnimatedSprite("Puzzle1", "puzzle8.png", new Point(1075,450));

    Dial dial1m1l3 = new Dial("hawk2.png", "hawk2.png", "butterfly1.png", "crab1.png", "elephant1.png", new Point(600, 400));
    Dial dial2m1l3 = new Dial("crab1.png", "crab1.png", "butterfly1.png", "hawk2.png", "elephant1.png", new Point(100, 400));
    Dial dial3m1l3 = new Dial("butterfly1.png", "butterfly1.png", "hawk2.png", "crab1.png", "elephant1.png", new Point(350, 100));

    Dial dial1m2l3 = new Dial("hawk2.png", "hawk2.png", "butterfly1.png", "crab1.png", "elephant1.png", new Point(600, 400));
    Dial dial2m2l3 = new Dial("crab1.png", "crab1.png", "butterfly1.png", "hawk2.png", "elephant1.png", new Point(100, 400));
    Dial dial3m2l3 = new Dial("butterfly1.png", "butterfly1.png", "hawk2.png", "crab1.png", "elephant1.png", new Point(350, 100));

    Minigame minigame1L3;
    Minigame minigame2L3;

    private void initFrames()
    {
        if (mario != null) {
            mario.addFrame("jump1.png");
            mario.addFrame("jump2.png");
            mario.addFrame("jump3.png");
            mario.addFrame("jump4.png");
            mario.addFrame("jump5.png");
            mario.addFrame("jump6.png");
            mario.addFrame("jump7.png");
            mario.addFrame("jump8.png");
            mario.addFrame("jump9.png");
            mario.addFrame("jump10.png");
            mario.addFrame("jump11.png");
        }
    }

    private void initAnimations()
    {
        Animation a = new Animation("jump,", 0, 10);
        mario.setAnimations(a);
    }
    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public FullGame() {
        super("Full Game", 1000, 800);

        //ArrayList<DisplayObject> invis = new ArrayList<DisplayObject>();

        //invis.add()

        if (Math.random() > .5){
            mario.setId("Misty");
            mario.setImage(mario.readImage("mistyD2.png"));
        }

        /* ##### Level 1 ###### */

        mario.setScaleX(getScaleX()*.5);
        mario.setScaleY(getScaleY()*.5);

        stairs1.setScaleX(getScaleX());
        stairs1.setScaleY(getScaleY() * 0.5);
        gameObjectsL1.add(stairs1);

        key1.setScaleX(getScaleX() * 0.08);
        key1.setScaleY(getScaleY() * 0.08);
        gameObjectsL1.add(key1);

        puzzle1.setScaleX(getScaleX() * 0.3);
        puzzle1.setScaleY(getScaleY() * 0.3);
        gameObjectsL1.add(puzzle1);

        this.getGameObjectsL1().add(mario);

        AnimatedSprite brick = new AnimatedSprite("Brick1", "brick.png", new Point(-300, 25));
        brick.setScaleX(getScaleX() * 0.5);
        //brick.setScaleY(getScaleY() * 0.5);
        this.getGameObjectsL1().add(brick);

        AnimatedSprite brick2 = new AnimatedSprite("Brick2", "brick.png", new Point(850, 25));
        brick2.setScaleX(getScaleX() * 0.5);
        //brick.setScaleY(getScaleY() * 0.5);
        this.getGameObjectsL1().add(brick2);

        AnimatedSprite brick3 = new AnimatedSprite("Brick3", "brick.png", new Point(75, 650));
        //brick3.setScaleX(getScaleX() * 0.5);
        brick3.setScaleY(getScaleY() * 0.5);
        this.getGameObjectsL1().add(brick3);

        // L1 Minigame Stuff

        ArrayList<Dial> dialList = new ArrayList<>();
        dialList.add(dial1m1l1);
        dial1m1l1.rotate(1);
        dialList.add(dial2m1l1);
        dial2m1l1.rotate(1);
        dialList.add(dial3m1l1);

        minigame1L1 = new Minigame("minigame1", dialList);

        /* ##### Level 2 ##### */

        this.getGameObjectsL2().add(mario);

        AnimatedSprite brick21 = new AnimatedSprite("Brick1", "wall3.png", new Point(150, 600));
        brick21.setScaleX(getScaleX()*2);
        brick21.setScaleY(getScaleY()*.25);
        this.getGameObjectsL2().add(brick21);

        AnimatedSprite brick22 = new AnimatedSprite("Brick1", "wall3.png", new Point(150, -300));
        brick22.setScaleX(getScaleX()*2);
        brick22.setScaleY(getScaleY()*.25);
        this.getGameObjectsL2().add(brick22);

        AnimatedSprite brick23 = new AnimatedSprite("Brick1", "wall3.png", new Point(100, -100));
        brick23.setScaleX(getScaleX()*.1);
        brick23.setScaleY(getScaleY()*.5);
        this.getGameObjectsL2().add(brick23);

        stairs2.setRotation(90);
        this.getGameObjectsL2().add(stairs2);

        key2.setScaleX(getScaleX() * 0.08);
        key2.setScaleY(getScaleY() * 0.08);
        this.getGameObjectsL2().add(key2);

        puzzle2.setScaleX(getScaleX() * 0.3);
        puzzle2.setScaleY(getScaleY() * 0.3);
        gameObjectsL2.add(puzzle2);

        // L2 Minigame Stuff

        dialList = new ArrayList<>();
        dialList.add(dial1m1l2);
        dial1m1l2.rotate(1);
        dialList.add(dial2m1l2);
        dial2m1l2.rotate(1);
        dialList.add(dial3m1l2);

        minigame1L2 = new Minigame("minigame2", dialList);

        minigame1L2.getMatrix()[0][1] = 1;
        minigame1L2.getMatrix()[2][0] = 1;

        /* ##### Level 3 ##### */

        AnimatedSprite brick39 = new AnimatedSprite("Brick1", "wall7.png", new Point(1700, -1000));
        brick39.setScaleX(getScaleX()*.05);
        brick39.setScaleY(getScaleY()*.5);
        brick39.setRotation(90);
        this.getGameObjectsL3().add(brick39);

        this.getGameObjectsL3().add(mario);

        key3.setScaleX(getScaleX()*.08);
        key3.setScaleY(getScaleY()*.08);

        key4.setScaleX(getScaleX()*.08);
        key4.setScaleY(getScaleY()*.08);

        this.getGameObjectsL3().add(key3);
        this.getGameObjectsL3().add(key4);


        AnimatedSprite brick31 = new AnimatedSprite("Brick1", "wall6.png", new Point(-100, -700));
        brick31.setScaleX(getScaleX()*.25);
        brick31.setScaleY(getScaleY()*1.75);
        this.getGameObjectsL3().add(brick31);

        AnimatedSprite brick32 = new AnimatedSprite("Brick1", "wall6.png", new Point(700, -100));
        brick32.setScaleX(getScaleX()*.25);
        this.getGameObjectsL3().add(brick32);

        AnimatedSprite brick36 = new AnimatedSprite("Brick1", "wall6.png", new Point(1500, -100));
        brick36.setScaleX(getScaleX()*2);
        this.getGameObjectsL3().add(brick36);

        AnimatedSprite brick37 = new AnimatedSprite("Brick1", "wall5.png", new Point(1500, -1000));
        brick37.setScaleX(getScaleX()*2);
        this.getGameObjectsL3().add(brick37);

        AnimatedSprite brick38 = new AnimatedSprite("Brick1", "wall7.png", new Point(3100, -200));
        brick38.setScaleX(getScaleX()*.05);
        brick38.setScaleY(getScaleY()*.075);
        this.getGameObjectsL3().add(brick38);

        AnimatedSprite brick33 = new AnimatedSprite("Brick1", "wall7.png", new Point(1750, 700));
        brick33.setScaleX(getScaleX()*.05);
        brick33.setScaleY(getScaleY()*2);
        brick33.setRotation(90);
        this.getGameObjectsL3().add(brick33);

        AnimatedSprite brick34 = new AnimatedSprite("Brick1", "wall5.png", new Point(1300, -600));
        brick34.setScaleX(getScaleX()*.25);
        brick34.setScaleY(getScaleY()*2);
        brick34.setRotation(90);
        this.getGameObjectsL3().add(brick34);

        /*
        AnimatedSprite brick35 = new AnimatedSprite("Brick1", "wall5.png", new Point(700, -400));
        brick35.setScaleX(getScaleX()*.25);
        brick35.setRotation(90);
        this.getGameObjectsL3().add(brick35);
        */

        stairs3.setScaleX(getScaleX()*.6);
        stairs3.setScaleY(getScaleY()*.6);
        this.getGameObjectsL3().add(stairs3);

        stairs4.setScaleX(.055);
        stairs4.setScaleY(.055);
        this.getGameObjectsL3().add(stairs4);

        puzzle3.setScaleX(getScaleX() * 0.3);
        puzzle3.setScaleY(getScaleY() * 0.3);
        gameObjectsL3.add(puzzle3);

        puzzle4.setScaleX(getScaleX() * 0.3);
        puzzle4.setScaleY(getScaleY() * 0.3);
        gameObjectsL3.add(puzzle4);

        // L3 Minigame Stuff

        dialList = new ArrayList<>();
        dialList.add(dial1m1l3);
        dial1m1l3.rotate(1);
        dialList.add(dial2m1l3);
        dial2m1l3.rotate(1);
        dialList.add(dial3m1l3);

        minigame1L3 = new Minigame("minigame3", dialList);

        minigame1L3.getMatrix()[0][1] = 2;
        minigame1L3.getMatrix()[1][2] = 2;

        dialList = new ArrayList<>();
        dialList.add(dial1m2l3);
        dial1m2l3.rotate(1);
        dialList.add(dial2m2l3);
        dial2m2l3.rotate(1);
        dialList.add(dial3m2l3);

        minigame2L3 = new Minigame("minigame4", dialList);

        minigame2L3.getMatrix()[0][1] = 2;
        minigame2L3.getMatrix()[1][0] = 2;
        minigame2L3.getMatrix()[0][2] = 2;
        minigame2L3.getMatrix()[2][0] = 2;
        minigame2L3.getMatrix()[1][2] = 2;
        minigame2L3.getMatrix()[2][1] = 2;
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){

        super.update(pressedKeys);
        /* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if(mario != null) mario.update(pressedKeys);

        ArrayList<DisplayObject> gameObjects = new ArrayList<>();

        if (level == 1) {
            gameObjects = this.getGameObjectsL1();
            for (DisplayObject gameObject : gameObjects) {
                gameObject.updateHitbox();
            }
            curminigame = null;
        } else if (level == 1.1) {
            gameObjects = null;
            curminigame = minigame1L1;
            if (minigame1L1.checkMinigame()) {
                gameObjectsL1.remove(puzzle1);
                SoundManager s = new SoundManager("win");
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                level = 1;
            }
        } else if (level == 2) {
            gameObjects = this.getGameObjectsL2();
            for (DisplayObject gameObject : gameObjects) {
                gameObject.updateHitbox();
            }
            curminigame = null;
        } else if (level == 2.1) {
            gameObjects = null;
            curminigame = minigame1L2;
            if (minigame1L2.checkMinigame()) {
                gameObjectsL2.remove(puzzle2);
                SoundManager s = new SoundManager("win");
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                level = 2;
            }
        } else if (level == 3) {
            gameObjects = this.getGameObjectsL3();
            for (DisplayObject gameObject : gameObjects) {
                gameObject.updateHitbox();
            }
            curminigame = null;
        } else if (level == 3.1) {
            gameObjects = null;
            curminigame = minigame1L3;
            if (minigame1L3.checkMinigame()) {
                gameObjectsL3.remove(puzzle4);
                SoundManager s = new SoundManager("win");
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                level = 3;
            }
        } else if (level == 3.2) {
            gameObjects = null;
            curminigame = minigame2L3;
            if (minigame2L3.checkMinigame()) {
                gameObjectsL3.remove(puzzle3);
                SoundManager s = new SoundManager("win");
                try {
                    Thread.sleep(4000);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                level = 3;
            }
        } else {
            exitGame();
        }

        if (success) {
            SoundManager s = new SoundManager("smwcastleclear");
            try {
                Thread.sleep(8000);

            } catch(InterruptedException e) {
                System.out.println("Interrupted.");
            }
            System.exit(0);
        }

        /* Add key press event to update visibility */
        if (pressedKeys.size() == 0 && vlast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }
        if (pressedKeys.size() == 0 && uplast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }
        if (pressedKeys.size() == 0 && downlast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }
        if (pressedKeys.size() == 0 && leftlast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }
        if (pressedKeys.size() == 0 && rightlast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }


        if (pressedKeys.contains(KeyEvent.VK_T)) {
            mario.setAccelerationXN(10.0f);
            mario.setAccelerationXP(10.0f);
            mario.setAccelerationYN(10.0f);
            mario.setAccelerationYP(10.0f);

        }

        ArrayList<DisplayObject> collisions = new ArrayList<DisplayObject>();

        /* Iterate through pressed keys arraylist */
        if (Math.round(level) == level) {
            for (int counter = 0; counter < pressedKeys.size(); counter++) {
                /* Logic for Up Arrowkey Press */
                if (pressedKeys.get(counter).equals(KeyEvent.VK_UP)) {
                    if(mario.getId().equals("Brock"))   mario.setImage(mario.readImage("brockU2.png"));
                    else    mario.setImage(mario.readImage("mistyU2.png"));

                    collisions.addAll(mario.tryMove(0, Math.round(-5 * mario.getAccelerationYN()), gameObjects));
                }
                /* Logic for Down Arrowkey Press */
                if (pressedKeys.get(counter).equals(KeyEvent.VK_DOWN)) {
                    if(mario.getId().equals("Brock"))   mario.setImage(mario.readImage("brockD2.png"));
                    else    mario.setImage(mario.readImage("mistyD2.png"));
                    collisions.addAll(mario.tryMove(0, Math.round(5 * mario.getAccelerationYP()), gameObjects));
                }
                /* Logic for Left Arrowkey Press */
                if (pressedKeys.get(counter).equals(KeyEvent.VK_LEFT)) {
                    if(mario.getId().equals("Brock"))   mario.setImage(mario.readImage("brockL2.png"));
                    else    mario.setImage(mario.readImage("mistyL2.png"));
                    collisions.addAll(mario.tryMove(Math.round(-5 * mario.getAccelerationXN()), 0, gameObjects));
                }
                /* Logic for Right Arrowkey Press */
                if (pressedKeys.get(counter).equals(KeyEvent.VK_RIGHT)) {
                    if(mario.getId().equals("Brock"))   mario.setImage(mario.readImage("brockR2.png"));
                    else    mario.setImage(mario.readImage("mistyR2.png"));
                    collisions.addAll(mario.tryMove(Math.round(5 * mario.getAccelerationXP()), 0, gameObjects));
                }
            }
        } else {
            for (int counter = 0; counter < pressedKeys.size(); counter++) {
                /* Logic for Up Arrowkey Press */
                if (pressedKeys.get(counter).equals(KeyEvent.VK_UP)) {
                    uplast = true;
                }
                /* Logic for Down Arrowkey Press */
                if (pressedKeys.get(counter).equals(KeyEvent.VK_DOWN)) {
                    downlast = true;
                }
                /* Logic for Left Arrowkey Press */
                if (pressedKeys.get(counter).equals(KeyEvent.VK_LEFT)) {
                    leftlast = true;
                }
                /* Logic for Right Arrowkey Press */
                if (pressedKeys.get(counter).equals(KeyEvent.VK_RIGHT)) {
                    rightlast = true;
                }
            }

            if (!pressedKeys.contains(KeyEvent.VK_UP) && uplast) {
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                curminigame.rotate(1);
                uplast = false;
            }
            if (!pressedKeys.contains(KeyEvent.VK_DOWN) && downlast) {
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                curminigame.rotate(-1);
                downlast = false;
            }
            if (!pressedKeys.contains(KeyEvent.VK_LEFT) && leftlast) {
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                curminigame.changeSelector(-1);
                leftlast = false;
            }
            if (!pressedKeys.contains(KeyEvent.VK_RIGHT) && rightlast) {
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                curminigame.changeSelector(1);
                rightlast = false;
            }

        }

        /* Manage Acceleration */
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            if (mario.getAccelerationYN() <= 5) {mario.setAccelerationYN(mario.getAccelerationYN() + 0.05f);}
        } else {
            mario.setAccelerationYN(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            if (mario.getAccelerationYP() <= 5) {mario.setAccelerationYP(mario.getAccelerationYP() + 0.05f);}
        } else {
            mario.setAccelerationYP(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            if (mario.getAccelerationXN() <= 5) {mario.setAccelerationXN(mario.getAccelerationXN() + 0.05f);}
        } else {
            mario.setAccelerationXN(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            if (mario.getAccelerationXP() <= 5) {mario.setAccelerationXP(mario.getAccelerationXP() + 0.05f);}
        } else {
            mario.setAccelerationXP(1.0f);
        }

        if (!pressedKeys.contains(KeyEvent.VK_V) && vlast) {
            if (mario.getVisible()) {
                mario.setVisible(false);
            } else {
                mario.setVisible(true);
            }
            vlast = false;
        }

        // Key Pickup Logic

        if (collisions.contains(key1)) {
            inventory.add(key1);
            SoundManager s = new SoundManager("smwcoin");
            this.getGameObjectsL1().remove(key1);
        }
        if (collisions.contains(key2)) {
            inventory.add(key2);
            SoundManager s = new SoundManager("smwcoin");
            this.getGameObjectsL2().remove(key2);
        }
        if (collisions.contains(key3)) {
            inventory.add(key3);
            SoundManager s = new SoundManager("smwcoin");
            this.getGameObjectsL3().remove(key3);
        }
        if (collisions.contains(key4)) {
            inventory.add(key4);
            SoundManager s = new SoundManager("smwcoin");
            this.getGameObjectsL3().remove(key4);
        }

        // Minigame Entry Logic

        if (collisions.contains(puzzle1)) {
            level = 1.1;
        }
        if (collisions.contains(puzzle2)) {
            level = 2.1;
        }
        if (collisions.contains(puzzle4)) {
            level = 3.1;
        }
        if (collisions.contains(puzzle3)) {
            level = 3.2;
        }

        // Level Change Logic

        if (inventory.contains(key1) && collisions.contains(stairs1)) {
            SoundManager s = new SoundManager("smw1up");
            level = 2;
            inventory.remove(key1);
        }
        if (inventory.contains(key2) && collisions.contains(stairs2)) {
            SoundManager s = new SoundManager("smw1up");
            level = 3;
            inventory.remove(key2);
        }
        if (inventory.contains(key4) && collisions.contains(stairs4)) {
            SoundManager s = new SoundManager("smw1up");
            gameObjectsL3.remove(stairs4);
            inventory.remove(key4);
        }
        if (inventory.contains(key3) && collisions.contains(stairs3)) {
            success = true;
            inventory.remove(key3);
        }

        for (DisplayObject collisionObject : collisions) {
            if (collisionObject.getPhysics()) {
                if (mario.getPosition().x > collisionObject.getPosition().x) {
                    collisionObject.tryMove(Math.round(-5 * mario.getAccelerationXN()), 0, gameObjects);
                } else {
                    collisionObject.tryMove(Math.round(5 * mario.getAccelerationXP()), 0, gameObjects);
                }
                if (mario.getPosition().y > collisionObject.getPosition().y) {
                    collisionObject.tryMove(0, Math.round(-5 * mario.getAccelerationYN()), gameObjects);
                } else {
                    collisionObject.tryMove(0, Math.round(5 * mario.getAccelerationYP()), gameObjects);
                }
            } else {
                ;
            }
        }
    }



    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        Graphics2D g2d = (Graphics2D) g;

        if (level == 1) {
            for (DisplayObject gameObject : this.getGameObjectsL1()) {
                if(gameObject != null) gameObject.draw(g);
            }

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level 1: The Attic #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level 1: The Attic #", 30, 30);


            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Inventory: #", 700, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Inventory: #", 702, 30);

            if (inventory.contains(key1)) {
                key1.setPosition(new Point(800, 75));
                key1.draw(g);
            }

        } else if (level == 1.1) {
            minigame1L1.draw(g);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level 1: Puzzle 1 #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level 1: Puzzle 1 #", 30, 30);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("Match the middle", 700, 32);
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("to the top", 700, 82);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("Match the middle", 702, 30);
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("to the top", 702, 80);

        }    else if (level == 2) {
            for (DisplayObject gameObject : this.getGameObjectsL2()) {
                if(gameObject != null) gameObject.draw(g);
            }

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level N: Ground Floor #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level N: Ground Floor #", 30, 30);


            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Inventory: #", 700, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Inventory: #", 702, 30);

            if(inventory.contains(key2)){
                key2.setPosition(new Point(800, 75));
                key2.draw(g);
            }

        } else if (level == 2.1) {
            minigame1L2.draw(g);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level 2: Puzzle 1", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level 2: Puzzle 1 #", 30, 30);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("One movement", 700, 32);
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("causes others!", 700, 82);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("One movement", 702, 30);
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("causes others!", 702, 80);

        } else if (level == 3) {
            for (DisplayObject gameObject : this.getGameObjectsL3()) {
                if(gameObject != null) gameObject.draw(g);
            }

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level N + 1: The End #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level N + 1: The End #", 30, 30);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Inventory: #", 700, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Inventory: #", 702, 30);

            if(inventory.contains(key3)){
                key3.setPosition(new Point(800, 75));
                key3.draw(g);
            }

            if(inventory.contains(key4)) {
                key4.setPosition(new Point(800, 75));
                key4.draw(g);
            }

        } else if (level == 3.2) {
            minigame2L3.draw(g);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level 3: Puzzle 2 #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level 3: Puzzle 2 #", 30, 30);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("Now a challenge!", 700, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("Now a challenge!", 702, 30);

        } else if (level == 3.1) {
            minigame1L3.draw(g);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level 3: Puzzle 1 #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level 3: Puzzle 1 #", 30, 30);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("Some things rotate", 675, 32);
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("faster than others...", 675, 82);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("Some things rotate", 677, 30);
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("faster than others...", 677, 80);

        } else {
            ;
        }

        if (success) {
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 48));
            g2d.setColor(Color.BLACK);
            g2d.setBackground (Color.BLACK);
            g2d.drawString("CONGRATULATIONS, YOU ESCAPED!", 77, 402);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 48));
            g2d.setColor(Color.GREEN);
            g2d.setBackground (Color.GREEN);
            g2d.drawString("CONGRATULATIONS, YOU ESCAPED!", 75, 400);
        }


    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        FullGame game = new FullGame();
        game.initFrames();
        game.initAnimations();
        game.start();
        SoundManager s1 = new SoundManager("readygo");
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted.");
        }
    }
}
