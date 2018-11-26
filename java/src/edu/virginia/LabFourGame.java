package edu.virginia;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Point;
    /**
     * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
     * although, for now, it won't be a very fun game :)
     */

    public class LabFourGame extends Game {

        /* Create the Sprites*/
        Sprite mario = new Sprite("Mario", "MarioSS.png");
        Sprite mario_hb = new Sprite("mario_hb", "MarioSS_hb.png");
        Sprite sun = new Sprite("Sun", "sun.png");
        Sprite sun_hb = new Sprite("sun_hb", "sun_hb.png");
        Sprite planet = new Sprite("Planet", "planet.png");
        Sprite planet2 = new Sprite("Planet2", "planet.png");
        Sprite planet3 = new Sprite("Planet3", "planet.png");
        Sprite planet4 = new Sprite("Planet4", "planet.png");
        Sprite planet5 = new Sprite("Planet5", "planet.png");
        Sprite planet_hb = new Sprite("planet_hb", "planet_hb.png");
        Sprite planet2_hb = new Sprite("planet2_hb", "planet_hb.png");
        Sprite planet3_hb = new Sprite("planet3_hb", "planet_hb.png");
        Sprite planet4_hb = new Sprite("planet4_hb", "planet_hb.png");
        Sprite planet5_hb = new Sprite("planet4_hb", "planet_hb.png");

        /* Score */

        private int score = 100;



        /* Game State - True: Playing False: Over */

        private boolean gameState = false;
        private boolean beginning = true;
        private boolean end = false;

        /* Music and Sound Effects */

        public SoundManager sounds = new SoundManager();


        /**
         * Constructor. See constructor in Game.java for details on the parameters given
         */
        public LabFourGame() {
            super("Lab Four Game", 500, 500);
        }


        public void addObjects() {


            /* add hitboxes */
            mario.addAtIndex(0, mario_hb);
            sun.addAtIndex(0, sun_hb);
            planet.addAtIndex(0, planet_hb);
            planet2.addAtIndex(0, planet2_hb);
            planet3.addAtIndex(0, planet3_hb);
            planet4.addAtIndex(0, planet4_hb);
            planet5.addAtIndex(0, planet5_hb);

            /* set positions of sun and planets */
            sun.setPosition(new Point (450, 250));
            planet.setPosition(new Point (75, 50));
            planet2.setPosition(new Point (260, 140));
            planet3.setPosition(new Point (120, 150));
            planet4.setPosition(new Point (400, 400));
            planet5.setPosition(new Point (200, 200));


            /* scale images */
            sun.setScaleX(.5);
            sun.setScaleY(.5);
            mario.setScaleX(.4);
            mario.setScaleY(.4);
            planet.setScaleY(.5);
            planet.setScaleX(.5);
            planet5.setScaleX(.6);
            planet5.setScaleY(.6);

            /* Load in sounds */
            sounds.LoadMusic("Theme", "theme.wav");
            sounds.LoadSoundEffect("Game Over", "gameover.wav");
            sounds.LoadSoundEffect("Crash", "crash.wav");


        }
        /* Checks for collisions */
        private boolean collides() {
            if (planet.collidesWith(mario) || mario.collidesWith(planet)){
                return true;
            }
            if (planet2.collidesWith(mario) || mario.collidesWith(planet2)){
                return true;
            }
            if (planet3.collidesWith(mario) || mario.collidesWith(planet3)){
                return true;
            }
            if (planet4.collidesWith(mario) || mario.collidesWith(planet4)){
                return true;
            }
            if (planet5.collidesWith(mario) || mario.collidesWith(planet5)){
                return true;
            }
            return false;

        }

        /**
         * Engine will automatically call this update method once per frame and pass to us
         * the set of keys (as strings) that are currently being pressed down
         */
        @Override
        public void update(ArrayList<Integer> pressedKeys) {
            super.update(pressedKeys);
            if (mario != null && sun != null) {

		        if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
                    beginning = false;
                    gameState = true;
                    if(end) {
                        end = false;
                        mario.setPosition(new Point (0, 0));
                        score = 100;
                    }
                }
                if (gameState) {
                if (mario != null) mario.update(pressedKeys);

                if (mario.getCount() < 30) {
                    mario.setCount(mario.getCount() + 1);
                }

                if (mario.getFrameCount() < 24) {
                    mario.setFrameCount(mario.getFrameCount() + 1);
                }

                /* planet movement */
                if (planet.getPosition().y > -20) {
                    planet.setPosition(new Point (planet.getPosition().x, planet.getPosition().y - 3));
                } else if (planet.getPosition().y <= -20) {
                    planet.setPosition(new Point(planet.getPosition().x, 500));
                }

                if (planet2.getPosition().y < 520) {
                    planet2.setPosition(new Point (planet2.getPosition().x, planet2.getPosition().y + 6));
                } else if (planet2.getPosition().y >= 520) {
                    planet2.setPosition(new Point(planet2.getPosition().x, 0));
                }

                if (planet3.getPosition().y > -20) {
                    planet3.setPosition(new Point (planet3.getPosition().x, planet3.getPosition().y - 3));
                } else if (planet3.getPosition().y <= -20) {
                    planet3.setPosition(new Point(planet3.getPosition().x, 500));
                }

                if (planet4.getPosition().y > -20) {
                    planet4.setPosition(new Point (planet4.getPosition().x, planet4.getPosition().y - 5));
                } else if (planet4.getPosition().y <= -20) {
                    planet4.setPosition(new Point(planet4.getPosition().x, 500));
                }

                if (planet5.getPosition().y < 520) {
                    planet5.setPosition(new Point (planet5.getPosition().x, planet5.getPosition().y + 9));
                } else if (planet5.getPosition().y >= 520) {
                    planet5.setPosition(new Point(planet5.getPosition().x, 0));
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

                /* Checking for collisions */
                if (mario.getFrameCount() == 24){
                    if (collides()){
                        score -= 10;
                        sounds.PlaySoundEffect("Crash");
                        mario.setFrameCount(0);
                    }
                }

                if (mario.collidesWith(sun) || sun.collidesWith(mario)) {
                     gameState = false;
                }

                if(score<=0){
                    gameState = false;
                    sounds.StopMusic();
                    sounds.PlaySoundEffect("Game Over");
                }
            }
            }
        }


        /**
         * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
         * the screen, we need to make sure to override this method and call sun's draw method.
         */
        @Override
        public void draw(Graphics g) {
            if (gameState) {
                super.draw(g);

		        /* Checking for null */
                if (mario != null) mario.draw(g);
                if (sun != null) sun.draw(g);
                if (planet != null) planet.draw(g);
                if (planet2 != null) planet2.draw(g);
                if (planet3 != null) planet3.draw(g);
                if (planet4 != null) planet4.draw(g);
                if (planet5 != null) planet5.draw(g);

                /* Drawing score */
                String scorestr = Integer.toString(score);
                g.drawString(scorestr, 450, 30);
            } else {
                /* Various game states */
                if (beginning) {
                    g.drawString("GET MARIO TO THE SUN WHILE AVOIDING ASTEROIDS", 30, 230);
                    g.drawString("PRESS ENTER TO START", 150, 250);

                } else if (score <= 0){
                    g.drawString("GAME OVER :(", 200, 230);
                    g.drawString("PRESS ENTER TO PLAY AGAIN", 130, 250);
                    end = true;
                } else {
                    g.drawString("YOU WIN! :)", 200, 230);
                    g.drawString("PRESS ENTER TO PLAY AGAIN", 130, 250);
                    end = true;
                }
            }

        }

        /**
         * Quick main class that simply creates an instance of our game and starts the timer
         * that calls update() and draw() every frame
         */
        public static void main(String[] args) {
            edu.virginia.LabFourGame game = new edu.virginia.LabFourGame();
            game.addObjects();;
            game.start();
            game.sounds.PlayMusic();

        }
    }


