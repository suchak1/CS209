package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.GameClock;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {

    private ArrayList<Animation> animations;
    private Boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> frames;
    private int currentFrame;
    private int startFrame;
    private int endFrame;
    static final int DEFAULT_ANIMATION_SPEED = 3;
    private int animationSpeed;
    private GameClock gameClock;
    private int frameCount;
    private Boolean paused;


    public AnimatedSprite(String id, String fn, Point pos) {
        super(id);
        this.setCount(30);
        this.initGameClock();
        this.setId(id);
        this.setImage(fn);
        this.setPosition(pos);
        this.setAnimationSpeed(DEFAULT_ANIMATION_SPEED);
        this.setStartFrame(0);
        this.setEndFrame(0);
        this.setCurrentFrame(0);
        this.frames = new ArrayList<BufferedImage>();
        this.animations = new ArrayList<Animation>();
        this.playing = false;
        this.paused = false;


    }

    public void initGameClock() {
        if (this.gameClock == null)
            this.gameClock = new GameClock();
    }


    public void setAnimationSpeed(int spd) {
        this.animationSpeed = spd;
    }

    public int getAnimationSpeed() {
        return this.animationSpeed;
    }

    public void addFrame(String imageName) {
        if (imageName == null) {
            return;
        }
        BufferedImage frame = readImage(imageName);

        if (frame!= null) {
            this.frames.add(frame);
        }
    }

    // getter/setter for animations

    public Animation getAnimation(String id){
        int i;
        for (i = 0; i < this.animations.size(); i++) {
            if(animations.get(i).getId().equals(id)) {
                return animations.get(i);
            }

        }
        return null;
    }

    public void setAnimations(Animation an){
        this.animations.add(an);
    }

    // getters/setters for frames

    public void setStartFrame(int sF) {this.startFrame = sF;}
    public int getStartFrame() {return this.startFrame;}
    public void setEndFrame(int eF) {this.endFrame = eF;}
    public int getEndFrame() {return this.endFrame;}
    public void setCurrentFrame(int cF) {this.currentFrame = cF;}
    public int getCurrentFrame() {return this.currentFrame;}

    public void setCount(int c) {this.frameCount = c;}
    public int getCount() {return this.frameCount;}

    public void setPlaying(Boolean b) {this.playing = b;}
    public Boolean getPlaying() {return this.playing;}

    public void setPaused(Boolean b) {this.paused = b;}
    public Boolean getPaused() {return this.paused;}

    private void animate(Animation an){
        this.setStartFrame(an.getStartFrame());
        this.setEndFrame(an.getEndFrame());
    }

    public void animate(String id) {
        this.setStartFrame(getAnimation(id).getStartFrame());
        this.setEndFrame(getAnimation(id).getEndFrame());
    }



    public void animate(int st, int end) {
        this.setStartFrame(st);
        this.setEndFrame(end);
    }

    public void stopAnimation(int fNum) {
        // this.playing = false;
        this.setPaused(true);
        this.startFrame = fNum;
    }

    public void stopAnimation(){
        stopAnimation(this.startFrame);
    }

    public void draw(Graphics g) {

        int sf = this.getStartFrame();
        int ef = this.getEndFrame();
        int cf = this.getCurrentFrame();
        BufferedImage frame;

            if (playing == true) {
                if (super.getFrameCount() == this.animationSpeed) {

                    frame = this.frames.get(cf);
                    if (cf == ef) {
                        this.setCurrentFrame(sf - 1);
                    }
                    this.setCurrentFrame(this.getCurrentFrame() + 1);
                    super.setFrameCount(0);
                } else {
                    frame = this.frames.get(cf);
                }
                if(this.getCurrentFrame() == this.getEndFrame()){
                   setPlaying(false);
                }
            } else {
                frame = getDisplayImage();
            }
        if (frame != null) {
            Graphics2D g2d = (Graphics2D) g;

            applyTransformations(g2d);
			/* Actually draw the image, perform the pivot point translation here */
            if (super.getVisible()) {
                g2d.drawImage(frame, 0, 0, (int) (getUnscaledWidth()),
                        (int) (getUnscaledHeight()), null);
            }

			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
            reverseTransformations(g2d);
        }


    }
}

