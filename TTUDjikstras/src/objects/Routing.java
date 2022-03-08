package objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Routing {

    private List<Circle> circles = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();

    private Circle coming;
    private Circle going;

    private boolean solved = false;

    private int count = 1;


    ////////// SETTING CIRCLES ON CHART

    public List<Circle> getCircles(){
        return circles;
    }

    public void setCircles(List<Circle> circles){
        this.circles = circles;
    }


    ////////// SETTING LINES ON CREATION

    public List<Line> getLines(){
        return lines;
    }

    public void setLines(List<Line> lines){
        this.lines = lines;
    }

   
    ////////// BOOLEAN TO SEE IF THE CURRENT PATH CAN REACH THE CIRCLE DESIRED

    public boolean isCircleReachable(Circle circle){
        for(Line line : lines)
            if(circle == line.getCircleOne() || circle == line.getCircleTwo())
                return true;

        return false;
    }

    ////////// CREATE/GET/SET COMING CIRCLE

    public boolean isComing(Circle circle){
        return circle == coming;
    }

    public Circle getComing(){
        return coming;
    }

    public void setComing(Circle circle){
        if(circles.contains(circle))
            coming = circle;
    }

    ////////// CREATE/GET/SET GOING CIRCLE

    public boolean isGoing(Circle circle){
        return circle == going;
    }

    public Circle getGoing(){
        return going;
    }

    public void setGoing(Circle circle){
        if(circles.contains(circle))
            going = circle;
    }


   
    ////////// PORTION FOR CREATING/DELETING CIRCLES AS NEEDED
    

    public void addCircle(Point coord){
        Circle circle = new Circle(coord);
        addCircle(circle);
    }

    public void addCircle(Circle circle){
        circle.setCircleID(count++);
        circles.add(circle);
        if(circle.getCircleId()==1)
            coming = circle;
    }

    public void deleteCircle(Circle circle){
        List<Line> delete = new ArrayList<>();
        for (Line line : lines){
            if(line.destinationCheck(circle)){
                delete.add(line);
            }
        }
        for (Line line : delete){
            lines.remove(line);
        }
        circles.remove(circle);
    }

    ////////// ADDING LINES

    public void addLine(Line new_line){
        boolean added = false;
        for(Line line : lines){
            if(line.equals(new_line)){
                added = true;
                break;
            }
        }
        if(!added)
            lines.add(new_line);
    }

     ////////// THIS IS OUR PORTION FOR RETURNING CONFIRMATION THAT PATH IS SOLVED

     public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    ////////// CLEAR THE WINDOW FUNCTION

    public void clear(){

        count = 1;

        circles.clear();
        lines.clear();
        
        solved = false;

        coming = null;
        going = null;
    }
    
}
