package objects;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Circle {

    private Point xy = new Point();
    private int CircleID;
    private java.util.List<Circle> path;
    

    public Circle(){}


    ////////  SETTING CIRCLE ID AND POINT POSITION

    public Circle(int CircleID){
        this.CircleID = CircleID;
    }

    public Circle(Point xy){
        this.xy = xy;
    }

    public int getCircleId(){
        return CircleID;
    }

    public void setCircleID(int CircleID){
        this.CircleID = CircleID;
    }


    //////// GETTING X AND Y COORDINATES FOR SETTING CIRCLE LOCATION

    public int getX(){
        return (int) xy.getX();
    }

    public int getY(){
        return (int) xy.getY();
    }

    public void setXY(int x, int y){
        xy.setLocation(x, y);
    }

    public Point getCoord(){
        return xy;
    }

    //////// GETTING CIRCLES PATH

    public List<Circle> getPath() {
        return path;
    }

    public void setPath(List<Circle> path) {
        this.path = path;
    }

    //////// OUTPUT

    @Override
    public String toString() {
        return "Circle " + CircleID;
    }

    
}