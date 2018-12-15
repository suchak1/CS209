package edu.virginia.engine.display;
import java.awt.*;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;


public class Dial extends DisplayObject {
    private ArrayList<AnimatedSprite> faces = new ArrayList<AnimatedSprite>();
    private AnimatedSprite dial;
    private AnimatedSprite completeDial;
    private AnimatedSprite match;
    private AnimatedSprite onIndicator;
    private boolean selected;

    public ArrayList<AnimatedSprite> getFaces() { return faces; }
    public void setFaces(ArrayList<AnimatedSprite> asList) { this.faces = asList; }

    public AnimatedSprite getDial() { return dial; }
    public void setDial(AnimatedSprite d) { this.dial = d; }

    public AnimatedSprite getCompleteDial() { return completeDial; }
    public void setCompleteDial(AnimatedSprite cd) { this.completeDial = cd; }

    public AnimatedSprite getMatch() { return match; }
    public void setMatch(AnimatedSprite m) { this.match = m; }

    public AnimatedSprite getOnIndicator() { return onIndicator; }
    public void setOnIndicator(AnimatedSprite as) { this.onIndicator = as; }

    public boolean getSelected() { return selected; }
    public void setSelected(boolean b) { this.selected = b; }


    public Dial(String match, String face1, String face2, String face3, String face4, Point pt) {
        AnimatedSprite tempdial = new AnimatedSprite("dial", "dial2.png", new Point(pt.x, pt.y));
        tempdial.setScaleX(0.5);
        tempdial.setScaleY(0.5);
        this.dial = tempdial;

        AnimatedSprite tempcompletedial = new AnimatedSprite("completeDial", "dial3.png", new Point(pt.x, pt.y));
        tempcompletedial.configureDialElement("completeDial", "dial3.png");
        this.completeDial = tempcompletedial;

        AnimatedSprite tempmatch = new AnimatedSprite("match", match, new Point(pt.x+75, pt.y+75));
        tempmatch.configureDialElement("match", match);
        this.match = tempmatch;

        AnimatedSprite tempface1 = (new AnimatedSprite("matchface", face1, new Point(pt.x+100, pt.y-70)));
        tempface1.configureDialElement("matchface", face1);
        this.faces.add(0, tempface1);

        AnimatedSprite tempface2 = new AnimatedSprite("face2", face2, new Point(pt.x+260, pt.y+100));
        tempface2.configureDialElement("face2", face2);
        this.faces.add(0, tempface2);

        AnimatedSprite tempface3 = new AnimatedSprite("face3", face3, new Point(pt.x+95, pt.y+260));
        tempface3.configureDialElement("face3", face3);
        this.faces.add(0, tempface3);

        AnimatedSprite tempface4 = new AnimatedSprite("face4", face4, new Point(pt.x-80, pt.y+100));
        tempface4.configureDialElement("face4", face4);
        this.faces.add(0, tempface4);

        this.onIndicator = new AnimatedSprite("onIndicator", "onIndicator5.png", new Point(pt.x+31, pt.y+30));
        onIndicator.setScaleX(0.38);
        onIndicator.setScaleY(0.38);
    }

    public void rotate(int direction) {
        int length = faces.size();
        if (direction > 0) {
            for (int i = 0; i < direction; i ++) {
                Point tempPoint = faces.get(0).getPosition();
                faces.get(0).setPosition(faces.get(3).getPosition());
                faces.get(3).setPosition(faces.get(2).getPosition());
                faces.get(2).setPosition(faces.get(1).getPosition());
                faces.get(1).setPosition(tempPoint);
            }
        } else if (direction < 0) {
            for (int i = 0; i > direction; i --) {
                Point tempPoint = faces.get(0).getPosition();
                faces.get(0).setPosition(faces.get(1).getPosition());
                faces.get(1).setPosition(faces.get(2).getPosition());
                faces.get(2).setPosition(faces.get(3).getPosition());
                faces.get(3).setPosition(tempPoint);
            }
        } else {
            ;
        }
    }

    public boolean checkAlignment() {
        AnimatedSprite tempface = faces.get(0);

        for (AnimatedSprite obj : faces) {
            if (obj.getPosition().y < tempface.getPosition().y) {
                tempface = obj;
            }
        }

        return (tempface.getId().contains("match"));
    }

    @Override
    public void draw(Graphics g) {
        if (super.getVisible()) {

            super.draw(g);
            Graphics2D g2d = (Graphics2D) g;
            applyTransformations(g2d);

            for (AnimatedSprite obj : faces) {
                if (obj != null) {
                    obj.draw(g);
                }
            }

            if (match != null) { match.draw(g); }
            if (onIndicator != null && selected) { onIndicator.draw(g); }

            if (this.checkAlignment()) {
                completeDial.draw(g);
            } else {
                dial.draw(g);
            }

            reverseTransformations(g2d);
        }
    }

}
