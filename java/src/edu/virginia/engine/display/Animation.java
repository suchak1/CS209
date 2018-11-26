package edu.virginia.engine.display;
/* Declare and implement the following instance variables in Animation:
id: Name of this animation this is (e.g. swim, dance, jump)
        startFrame: Index of the start frame in the list of images, which enables us to support several animations
        endFrame: Index of the last frame in the list of images
        Implement a constructor for Animation that takes in the parameters id, startFrame, endFrame.
        Implement getters and setters for each of these instance variables.
        In AnimatedSprite(), write a setter for animations and for AnimationSpeed.
        In AnimatedSprite(), implement the draw() method. This method will iterate through currentFrame to draw that image.
        Remember that you only want to change the image after animationSpeed  amount of time has elapsed and if the animation is playing.
        Make sure to reset the game clock after it switches images.
*/
public class Animation {

    private String id;
    private int startFrame;
    private int endFrame;

    public Animation(String id, int sF, int eF){
        this.setId(id);
        this.setStartFrame(sF);
        this.setEndFrame(eF);

    }

    public void setId(String id) {this.id = id;}
    public String getId() {return this.id;}

    public void setStartFrame(int sF) {this.startFrame = sF;}
    public int getStartFrame() {return this.startFrame;}

    public void setEndFrame(int eF) {this.endFrame = eF;}
    public int getEndFrame() {return this.endFrame;}





}
