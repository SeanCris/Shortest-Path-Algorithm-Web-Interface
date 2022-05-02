package controls;

// NOTES:
// (1) THIS .JAVA CONTAINS ALL THE BASICS REQUIRED FOR THE CONTROLS THROUGHOUT THE PROGRAM.
// (2)  BELOW I'VE LABALED EVERYTHING TO MAKE CHANGES OR ADDITIONS IF NEEDED

// (3) THIS FILE CONTAINS A VARIETY OF TOOLS ACCESSED THROUGH CALLS OF THE MAIN JFRAME.
//          BELOW YOU CAN FIND:
//                          - USER INPUT SETTINGS
//                          - GUI PROMPTS BASED OFF USER INTERACTION
//                          - DRAG AND DROP


import objects.Line;
import objects.Pathing;
import objects.Vertice;

import javax.swing.*;

import GUI.GraphicsSettings;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;


public class Controls extends JPanel implements MouseListener, MouseMotionListener {

    private GraphicsSettings gSettings;

    private Point mouseLoc;
    private Pathing pathing;

    private Vertice targetV = null;
    private Line targetL = null;

    private java.util.List<Vertice> path = null;

    public Controls(Pathing graph){
        this.pathing = graph;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setPath(List<Vertice> path) {

        this.path = path;
        targetL = null;
        
        repaint();

    }

    @Override
    protected void paintComponent(Graphics importg) {  // DRAWS OBJECTS
        super.paintComponent(importg);

        Graphics2D g = (Graphics2D) importg;

        gSettings = new GraphicsSettings(g);

        if(pathing.completeCheck()){ // makes sure path is complete  ( MAKE SURE THIS IS ABOVE THE DRAW LINE OR IT WILL COVER UP YOUR LINES ONCE SOLVED)
            gSettings.drawPath(path);
        }

        for(Line line : pathing.getLines()){ //drawing line for path
            gSettings.drawLine(line);
        }

        if(targetV != null && mouseLoc != null){  // draws lines to vertice
            Line e = new Line(targetV, new Vertice(mouseLoc));
            gSettings.drawLine(e);
        }

        for(Vertice vertice : pathing.getVertices()){ // drawing vertices
            if(pathing.startIndex(vertice)) // draw start
                gSettings.drawSourceV(vertice);
            else if(pathing.endIndex(vertice)) // draw end
                gSettings.drawDestinationV(vertice);
            else
                gSettings.drawVertice(vertice); // middle vertices
        }
    }

    ///// Mouse listening funcs, Even if some are these are empty they are required to be in the program for it to function

    @Override
    public void mouseClicked(MouseEvent e) {

        Vertice target = null;

        for(Vertice vertice : pathing.getVertices()) {
            if(GraphicsSettings.boundryCheck(e, vertice.getLoc())){ //  boundry check for vertice
                target = vertice;
                break;
            }
        }

        if(target!=null) {

            if(e.isControlDown() && e.isShiftDown()){  // delete vertice
                pathing.deleteVertice(target); 
                pathing.setCompletion(false);
                repaint();
                return;
            } 
            
            else if(e.isShiftDown()){ // shift pressed check
                
                if(SwingUtilities.isLeftMouseButton(e)){ // set start vertice
                    if(!pathing.endIndex(target))
                        pathing.setStart(target);
                } 
                
                else if(SwingUtilities.isRightMouseButton(e)) { // set end vertice
                    if(!pathing.startIndex(target))
                        pathing.setEnd(target);
                
                }
                
                else
                    return;

                pathing.setCompletion(false);
                repaint();
                return;
            }
        }
        

        if(targetL!=null){

            

            if(e.isControlDown() && e.isShiftDown()){ // remooves lines connected to deleted vertices
                pathing.getLines().remove(targetL);
                targetL = null;
                pathing.setCompletion(false);
                repaint();
                return;
            }

            try {

                String input = JOptionPane.showInputDialog("Enter line weight below: "); // while target isnt null, clicking on line sets weight

                int weight = Integer.parseInt(input);
                
                if (weight > 0) { // must be larger than 0 (default is 1)
                    targetL.setWeight(weight);
                    pathing.setCompletion(false);
                    repaint();
                } 
                
                else {
                    JOptionPane.showMessageDialog(null, "!! Weight must be positive !!"); // fail message
                }

            } catch (NumberFormatException nfe) {}
            return;
        }

        for(Vertice vertice : pathing.getVertices()) {
            if(GraphicsSettings.noOverlap(e, vertice.getLoc())){    // if overlapcheck returns show message
                JOptionPane.showMessageDialog(null, "!! Cannot overlap vertices !!");
                return;
            }
        }

        pathing.addVertice(e.getPoint());
        pathing.setCompletion(false);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Vertice vertice : pathing.getVertices()) {
            if(targetV !=null && vertice!= targetV && GraphicsSettings.boundryCheck(e, vertice.getLoc())){
                Line newL = new Line(targetV, vertice);
                pathing.addLine(newL);
                pathing.setCompletion(false);
            }
        }
        targetV = null; // reset

        repaint(); // this repaint deletes lines that arent actually connected to vertices
    }
    
  
    @Override
    public void mouseDragged(MouseEvent e) { // drag and drop

        for (Vertice vertice : pathing.getVertices()) {
            if(targetV == null && GraphicsSettings.boundryCheck(e, vertice.getLoc())){
                targetV = vertice;
            }
        }

        if(targetV !=null){
            if(e.isControlDown()){
                targetV.setLoc(e.getX(), e.getY());
                mouseLoc = null;
                repaint(); // this repaint gives us the live update on dragging
                return;
            }

            mouseLoc = new Point(e.getX(), e.getY());

            repaint(); // this repaint live draws our lines
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        targetL = null; 

        for (Line line : pathing.getLines()) {
            if(GraphicsSettings.isOnPath(e, line)) {
                targetL = line;
            }
        }



    }

    @Override
    public void mouseEntered(MouseEvent e) {   // do not remove these three funcs

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    public void reset(){ // reset method

        pathing.reset();

        targetV = null;
        targetL = null;

        repaint();
    }
}
