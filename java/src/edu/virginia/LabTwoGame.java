package edu.virginia;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Point;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;

import edu.virginia.engine.display.Animation;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabTwoGame extends Game {

    /* Create a sprite object for our game. We'll use mario */

    AnimatedSprite mario = new AnimatedSprite("Mario", "Mario.png", new Point(0,0));


    private void populateFrames() {
        if (mario != null) {
            mario.addFrame("jump_01.png");
            mario.addFrame("jump_02.png");
            mario.addFrame("jump_03.png");
            mario.addFrame("jump_04.png");
            mario.addFrame("jump_05.png");
            mario.addFrame("jump_06.png");
            mario.addFrame("jump_07.png");
            mario.addFrame("jump_08.png");
            mario.addFrame("jump_09.png");
            mario.addFrame("jump_10.png");
            mario.addFrame("jump_11.png");
        }
    }

    private void populateAnimations() {
        Animation an = new Animation("jump", 0, 10);
        mario.setAnimations(an);
    }


    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     */
    public LabTwoGame() {
        super("Lab One Test Game", 800, 400);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if (mario != null) mario.update(pressedKeys);

        if (mario.getCount() < 30) {
            mario.setCount(mario.getCount() + 1);
        }
        if (!mario.getPaused()) {
            if (mario.getFrameCount() < mario.getAnimationSpeed()) {
                mario.setFrameCount(mario.getFrameCount() + 1);
            } else if (mario.getFrameCount() > mario.getAnimationSpeed()) {
                mario.setFrameCount(0);
            }
        }
		/* arrow key presses */
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
        }
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y + 5));
        }
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            mario.setPosition(new Point(mario.getPosition().x - 5, mario.getPosition().y));
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y));
        }

		/* IJKL presses */
        if (pressedKeys.contains(KeyEvent.VK_I)) {
            mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y - 5));
        }
        if (pressedKeys.contains(KeyEvent.VK_K)) {
            mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y + 5));
        }
        if (pressedKeys.contains(KeyEvent.VK_J)) {
            mario.setPivotPoint(new Point(mario.getPivotPoint().x - 5, mario.getPivotPoint().y));
        }
        if (pressedKeys.contains(KeyEvent.VK_L)) {
            mario.setPivotPoint(new Point(mario.getPivotPoint().x + 5, mario.getPivotPoint().y));
        }

		/* rotation counterclockwise and clockwise */
        if (pressedKeys.contains(KeyEvent.VK_Q)) {
            mario.setRotation(mario.getRotation() - 10);
        }
        if (pressedKeys.contains(KeyEvent.VK_W)) {
            mario.setRotation(mario.getRotation() + 10);
        }
		/* set visibility */
        if (pressedKeys.contains(KeyEvent.VK_V)) {
            if (mario.getCount() == 30) {
                mario.setVisible(!mario.getVisible());
                mario.setCount(0);
            }

        }
		/* set alpha */
        if (pressedKeys.contains(KeyEvent.VK_Z)) {
            if (mario.getAlpha() >= 1.0f) {
                mario.setAlpha(1.0f);
            } else {
                if (mario.getAlpha() * 1.1f >= 1.0f) {
                    mario.setAlpha(1.0f);
                } else {
                    mario.setAlpha(mario.getAlpha() * 1.1f);
                }
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_X)) {
            mario.setAlpha(mario.getAlpha() * .9f);
        }

		/* scale mario */
        if (pressedKeys.contains(KeyEvent.VK_A)) {
            mario.setScaleX(mario.getScaleX() * 1.1);
            mario.setScaleY(mario.getScaleY() * 1.1);
        }
        if (pressedKeys.contains(KeyEvent.VK_S)) {
            mario.setScaleX(mario.getScaleX() * .9);
            mario.setScaleY(mario.getScaleY() * .9);
        }
        if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
            mario.setPlaying(true);
            mario.animate("jump");
            // mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
        }
        if(pressedKeys.contains(KeyEvent.VK_U)) {
            if (mario.getAnimationSpeed() >= 2) {
                mario.setAnimationSpeed(mario.getAnimationSpeed() - 1);
            }
        }
        if(pressedKeys.contains(KeyEvent.VK_Y)) {
            mario.setAnimationSpeed(mario.getAnimationSpeed() + 1);
        }
        if(pressedKeys.contains(KeyEvent.VK_ENTER)) {
            if (mario.getCount() > 15) {
                if (mario.getPlaying()) {
                    if (!mario.getPaused())
                        mario.stopAnimation(mario.getCurrentFrame());
                    else
                        mario.setPaused(false);

                } else
                    mario.setPaused(false);
            mario.setCount(0);
            }
        }
    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);

		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
        if (mario != null) mario.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     */
    public static void main(String[] args) {
        LabTwoGame game = new LabTwoGame();
        game.populateFrames();
        game.populateAnimations();
        game.start();


    }
}