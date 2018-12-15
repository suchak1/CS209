package edu.virginia.engine.display;
import java.awt.*;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;


public class Minigame extends DisplayObject {
    private ArrayList<Dial> dialList;
    private int selected = 0;
    private String id;
    private int[][] matrix;

    public Minigame(String id) {
        this.id = id;
    }

    public Minigame(String id, ArrayList<Dial> dialList) {
        this.id = id;
        this.dialList = dialList;
        if (this.dialList.size() >1 ) { dialList.get(0).setSelected(true); }
        this.matrix = new int[dialList.size()][dialList.size()];
        for (int i = 0; i < dialList.size(); i++) {
            for (int j = 0; j < dialList.size(); j++) {
                if (i == j) {
                    this.matrix[i][j] = 1;
                } else {
                    this.matrix[i][j] = 0;
                }
            }
        }
    }

    public int[][] getMatrix() { return matrix; }
    public void setMatrix(int[][] m) { this.matrix = m; }

    public void addDial(Dial d) {
        dialList.add(d);
    }

    public boolean checkMinigame() {
        for (Dial d : dialList) {
            if (!d.checkAlignment()) {
                return false;
            }
        }
        return true;
    }

    public void rotate(int direction) {
        for (int i = 0; i < dialList.size(); i++) {
            for (int j = 0; j < dialList.size(); j++) {
                if (i == selected) {
                    if (direction > 0) { dialList.get(j).rotate(this.matrix[i][j]); }
                    else { dialList.get(j).rotate(-1 * this.matrix[i][j]); }
                }
            }
        }
    }

    public void changeSelector(int direction) {
        int length = dialList.size();
        if (length < 1) {
            return;
        } else if (length == 1) {
            selected = 0;
        } else if (length > 1) {
            if (direction == 1) {
                if (length == selected + 1) {
                    selected = 0;
                } else {
                    selected += 1;
                }
            } else if (direction == -1) {
                if (selected == 0) {
                    selected = length - 1;
                } else {
                    selected -= 1;
                }
            }
        } else {
            ;
        }
        for (Dial d : dialList) {
            d.setSelected(false);
        }
        dialList.get(selected).setSelected(true);
    }

    @Override
    public void draw(Graphics g) {
        if (super.getVisible()) {

            super.draw(g);
            Graphics2D g2d = (Graphics2D) g;
            applyTransformations(g2d);

            for (Dial d : dialList) {
                d.draw(g);
            }

            reverseTransformations(g2d);
        }
    }

}