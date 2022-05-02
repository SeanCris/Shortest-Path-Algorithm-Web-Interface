package objects; // Stating belong to this package

// NOTES:
// (1) THIS .JAVA CONTAINS ALL THE BASICS REQUIRED FOR USING LINES THROUGHOUT THE PROGRAM.
// (2)  BELOW I'VE LABALED EVERYTHING TO MAKE CHANGES OR ADDITIONS IF NEEDED

public class Line {

    private Vertice start;
    private Vertice end;
    private int weight = 1; // Default weight is here

    public Line(Vertice start, Vertice end) {
        this.start = start;
        this.end = end;
    }

    public Vertice getStartV() {
        return start;
    }

    public Vertice getEndV() {
        return end;
    }

    //// End Aquisition and Output

    public boolean getEnds(Vertice vertice) {
        return start == vertice || end == vertice;
    }

    public boolean getSegment(Line line) { // This function allows algorithm to work no matter the start or end
        return (start == line.start && end == line.end);
    }

    //// Weight aquisition and setting

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
