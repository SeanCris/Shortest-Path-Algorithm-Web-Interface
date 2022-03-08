package objects;

public class Line {

    private Circle coming;  // Circle we are starting at
    private Circle going;   // Circle we are ending at

    private int weight = 1; // Default weight

    //////// GETTING GOING AND COMING NODES

    public Circle getCircleOne() {
        return coming;
    }

    public Circle getCircleTwo() {
        return going;
    }

    public Line(Circle coming, Circle going) {
        this.coming = coming;
        this.going = going;
    }

    //////// SETTING WEIGHT TO LINE FROM COMING TO GOING

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    //////// CIRCLE DESTINATION RETURNS / CHECKING IF CIRCLE EXISTS - THIS FORMAT ALLOWS A LINE TO BE MADE NO MATTER THE NODE YOU ARE DRAWING FROM

    public boolean destinationCheck(Circle circle) {
        return coming == circle || going == circle;
    }

    public boolean destinationEquals(Line line) {
        return (coming == line.coming && going == line.going) || (coming == line.going && going == line.coming) ;
    }

    @Override
    public String toString() {   // Output
        return "Edge = " + getCircleOne().getCircleId() + " - " + getCircleTwo().getCircleId();
    }
}