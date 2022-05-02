package objects; // Stating belong to this package

// NOTES:
// (1) THIS .JAVA CONTAINS ALL THE BASICS REQUIRED FOR USING PATHS THROUGHOUT THE PROGRAM.
// (2)  BELOW I'VE LABALED EVERYTHING TO MAKE CHANGES OR ADDITIONS IF NEEDED

// (3) THIS FILE CONTAINS A VARIETY OF TOOLS ACCESSED THROUGH CALLS OF THE MAIN JFRAME.
//          BELOW YOU CAN FIND:
//                          - ADDING AND REMOVING VERTICES INTO THE MAIN VERTICE LIST
//                          - ADDING AND REMOVING LINES INTO THE MAIN LINE LIST
//                          - STARTING AND ENDING VERTICES ID BOOLEANS
//                          - ALGORITHM COMPLETION CHECKER
//                          - GLOBAL RESET FUNCTION

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Pathing {

    private int count = 1; // (DEFAULT) Start at 1

    private boolean completed = false;

    private Vertice start;
    private Vertice end;

    private List<Vertice> vertices = new ArrayList<>(); // List of vertices
    private List<Line> lines = new ArrayList<>(); // list of lines

    ////// Adding vertices and lines into lists

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    /////// Completion portion

    public void setCompletion(boolean solved) {
        this.completed = solved;
    }

    public boolean completeCheck() {
        return completed;
    }

    ////// Funcs for adding/removing vertices, called in user interface

    public void addVertice(Point loc) { // location
        Vertice vertice = new Vertice(loc);
        addVertice(vertice);
    }

    public void addVertice(Vertice vertice) { // adds to index
        vertice.setVIDNum(count++);
        vertices.add(vertice);
        if (vertice.getVIDNum() == 1)
            start = vertice;
    }

    public void deleteVertice(Vertice vertice) { // removal
        List<Line> delete = new ArrayList<>();
        for (Line line : lines) {
            if (line.getEnds(vertice)) {
                delete.add(line);
            }
        }
        for (Line line : delete) {
            lines.remove(line);
        }
        vertices.remove(vertice);
    }

    public void addLine(Line newL) { // line addition
        boolean check = false;
        for (Line edge : lines) {
            if (edge.getSegment(newL)) {
                check = true;
                break;
            }
        }
        if (!check)
            lines.add(newL);
    }

    ////// Getting start and end vertices of the line we are manipulating

    public Vertice getStart() {
        return start;
    }

    public void setStart(Vertice vertice) {
        if (vertices.contains(vertice))
            start = vertice;
    }

    public Vertice getEnd() {
        return end;
    }

    public void setEnd(Vertice vertice) {
        if (vertices.contains(vertice))
            end = vertice;
    }

    public boolean neighborCheck(Vertice vertice) { // This function makes sure all the vertices are connected with paths

        for (Line line : lines) // Goes through each line and makes sure its a start or end vertice
            if (vertice == line.getStartV() || vertice == line.getEndV())
                return false;

        return true;
    }

    ////// Indexing booleans

    public boolean startIndex(Vertice vertice) { // Called later to specify the starting vertice
        return vertice == start;
    }

    public boolean endIndex(Vertice vertice) { // Called later to specify the ending vertice
        return vertice == end;
    }


    public void reset() { // reset call to clear everything

        count = 1;

        start = null;
        end = null;

        vertices.clear();
        lines.clear();
        
    }

}
