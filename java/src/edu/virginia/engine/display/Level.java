package edu.virginia.engine.display;
import edu.virginia.engine.util.Tuple;
import java.awt.Point;
import java.util.ArrayList;

public class Level {
    /* Level Class fields:
     *
     * initGrid and currGrid are arrays with each index corresponding to a square on the game grid
     * * array goes left to right, bottom to top on grid
     * * the Boolean spot indicates whether that grid square can be entered
     * * the Integer spot indicates what object is in that grid square
     * * Key: 0 = nothing, 1 = wall, 2 = enemy, 3 = barricade, 4 = start, 5 = goal
     * * initGrid does not change, and contains the starting state of the grid for that level
     * * currGrid is updated to reflect the character's actions
     *
     * movesAvail is an array of the buttons that are allowed to be used in that level
     *
     * movesTaken is an array of integers that correspond to actions the character can take (buttons pressed)
     * * Key: 0 = no move, 1 = move forward, 2 = rotate right, 3 = stab, 4 = conditional block, 5 = loop (3x)
     *
     * position indicates where the top left of the grid is in the whole image
     *
     * id indicates which number level this is
     */
    private ArrayList <Tuple <Boolean, Integer>> initGrid;
    private ArrayList <Tuple <Boolean, Integer>> currGrid;
    private ArrayList <Integer> movesAvail;
    private ArrayList <Integer> movesTaken;
    private Point position;
    private int id;

    /* getters and setters */
    public ArrayList <Tuple <Boolean, Integer>> getInitGrid() {return this.initGrid;}
    public  void setInitGrid(ArrayList <Tuple <Boolean, Integer>> grid) {this.initGrid = grid;}

    public ArrayList <Tuple <Boolean, Integer>> getCurrGrid() {return this.currGrid;}
    public  void setCurrGrid(ArrayList <Tuple <Boolean, Integer>> grid) {this.currGrid = grid;}

    public ArrayList <Integer> getMovesAvail() {return this.movesAvail;}
    public void setMovesAvail(ArrayList <Integer> moves) {this.movesAvail = moves;}

    public Point getPosition() {return this.position;}
    public void setPosition(Point pos) {this.position = pos;}

    public int getId() {return this.id;}
    public void setId(int n) {this.id = n;}


    /* updateCurrGrid updates the state of a given grid square */
    void updateCurrGrid(int square, Boolean enter, int state) {
        this.getCurrGrid().add(square, new Tuple <> (enter, state));
    }



}
