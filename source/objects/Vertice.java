package objects; // Stating belong to this package

// NOTES:
// (1) THIS .JAVA CONTAINS ALL THE BASICS REQUIRED FOR USING VERTICES THROUGHOUT THE PROGRAM.
// (2)  BELOW I'VE LABALED EVERYTHING TO MAKE CHANGES OR ADDITIONS IF NEEDED

import java.awt.Point;
import java.util.List;

public class Vertice {

    private Point loc = new Point();  // This is going to be the holder for our vertices actual location on the board
    private int index;
    private java.util.List<Vertice> path;

    ///// Base level aquisitions

    public Vertice(int index) {
        this.index = index;
    }

    public Vertice(Point p) {
        this.loc = p;
    }

    public int getVIDNum() {
        return index;
    }

    public void setVIDNum(int index) {
        this.index = index;
    }

    ///// Get Location and setting Location

    public Point getLoc() {
        return loc;
    }

    public int getX() {
        return (int) loc.getX();
    }

    public int getY() {
        return (int) loc.getY();
    }

    public void setLoc(int x, int y) {
        loc.setLocation(x, y);
    }

    ///// Pathing

    public List<Vertice> getPath() {
        return path;
    }
    
    public void setPath(List<Vertice> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Vertice: " + index;
    }
}
