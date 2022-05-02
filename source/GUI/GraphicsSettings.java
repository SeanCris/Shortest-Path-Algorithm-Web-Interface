package GUI;

// NOTES:
// (1) THIS .JAVA CONTAINS ALL THE BASICS REQUIRED FOR THE GRAPHICAL SETTINGS THROUGHOUT THE PROGRAM.
// (2)  BELOW I'VE LABALED EVERYTHING TO MAKE CHANGES OR ADDITIONS IF NEEDED

// (3) THIS FILE CONTAINS A VARIETY OF TOOLS ACCESSED THROUGH CALLS OF THE MAIN JFRAME.
//          BELOW YOU CAN FIND:
//                          - COLOR SETTINGS FOR VERTICES AND LINES
//                          - PATHING COLOR SETTINGS
//                          - FONT SETTINGS

import objects.Line;
import objects.Vertice;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class GraphicsSettings {

    private Graphics2D g;

    private static int radius = 22; // radius pretty much controls all of the shape sizes. Do not make it too big or things start to break

    ///// Tools

    public GraphicsSettings(Graphics2D graphics2D) {
        g = graphics2D;
    }

    public static boolean boundryCheck(MouseEvent click, Point p) {
        int x = click.getX();
        int y = click.getY();

        int boundX = (int) p.getX();
        int boundY = (int) p.getY();

        return (x <= boundX + radius && x >= boundX - radius) && (y <= boundY + radius && y >= boundY - radius);
    }

    public static Color hexToRGB(String colorStr) { // Func to make color assignments easy
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    ///// Checks


    public static boolean noOverlap(MouseEvent click, Point p) {

        int x = click.getX();
        int y = click.getY();

        int boundryX = (int) p.getX();
        int boundryY = (int) p.getY();

        return (x <= boundryX + 2.5 * radius && x >= boundryX - 2.5 * radius) && (y <= boundryY + 2.5 * radius && y >= boundryY - 2.5 * radius);
    }

    public static boolean isOnPath(MouseEvent click, Line line) {

        int dist = distCalc(click.getPoint(), line.getStartV().getLoc(), line.getEndV().getLoc());

        if (dist < 10) // max
            return true;

        return false;
    }

    ///// Drawing Vertices

    public void drawSourceV(Vertice vertice) {
        g.setColor(hexToRGB("#218026")); // outline color
        g.fillOval(vertice.getX() - radius, vertice.getY() - radius, radius * 2, radius * 2);

        radius -= 5; // Shrink radius for smaller inner circle

        g.setColor(hexToRGB("#b9ddbb")); // center color
        g.fillOval(vertice.getX() - radius, vertice.getY() - radius, radius * 2, radius * 2);

        radius += 5; // restore circle size

        g.setColor(hexToRGB("#218026")); // text color
        drawVerticeNumber(String.valueOf(vertice.getVIDNum()), vertice.getX(), vertice.getY());
    }

    public void drawVertice(Vertice vertice) {
        g.setColor(hexToRGB("#1facc4"));
        g.fillOval(vertice.getX() - radius, vertice.getY() - radius, radius * 2, radius * 2);

        radius -= 5;

        g.setColor(hexToRGB("#b5ecf5"));
        g.fillOval(vertice.getX() - radius, vertice.getY() - radius, radius * 2, radius * 2);

        radius += 5;

        g.setColor(hexToRGB("#1facc4"));
        drawVerticeNumber(String.valueOf(vertice.getVIDNum()), vertice.getX(), vertice.getY());
    }

    public void drawDestinationV(Vertice vertice) {
        g.setColor(hexToRGB("#d92316"));
        g.fillOval(vertice.getX() - radius, vertice.getY() - radius, radius * 2, radius * 2);

        radius -= 5;

        g.setColor(hexToRGB("#db959C"));
        g.fillOval(vertice.getX() - radius, vertice.getY() - radius, radius * 2, radius * 2);

        radius += 5;

        g.setColor(hexToRGB("#d92316"));
        drawVerticeNumber(String.valueOf(vertice.getVIDNum()), vertice.getX(), vertice.getY());
    }

    ///// Draw lines

    public void drawPath(java.util.List<Vertice> path) {

        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < path.size() - 1; i++) { // get all lines in a path 
            lines.add(new Line(path.get(i), path.get(i + 1)));
        }

        for (Line line : lines) {
            drawPath(line);
        }

    }

    public void drawPath(Line line) {
        g.setColor(hexToRGB("#fA0202"));
        drawLineOutline(line);
    }

    public void drawLine(Line line) {
        g.setColor(hexToRGB("#466275"));
        drawBaseLine(line);
        drawWeight(line);
    }

    private void drawBaseLine(Line line) { // This is the underlying actual line
        Point from = line.getStartV().getLoc();
        Point to = line.getEndV().getLoc();

        g.setStroke(new BasicStroke(7));  // Line width

        g.drawLine(from.x, from.y, to.x, to.y);
    }

    private void drawLineOutline(Line line) { // This is the path outline once solved
        Point from = line.getStartV().getLoc();
        Point to = line.getEndV().getLoc();

        g.setStroke(new BasicStroke(10)); // line width

        g.drawLine(from.x, from.y, to.x, to.y);

        int x = (from.x + to.x) / 2;
        int y = (from.y + to.y) / 2;

        int rad = 13;

        g.fillOval(x - rad, y - rad, 2 * rad, 2 * rad);
    }

    ///// Draw text

    public void drawWeight(Line line) {

        Point from = line.getStartV().getLoc();
        Point to = line.getEndV().getLoc();

        int x = (from.x + to.x) / 2;
        int y = (from.y + to.y) / 2;

        int rad = radius / 2;

        g.fillOval(x - rad, y - rad, 2 * rad, 2 * rad);

        drawWeightText(String.valueOf(line.getWeight()), x, y);
    }

    public void drawWeightText(String text, int x, int y) {

        g.setColor(hexToRGB("#cccccc"));

        FontMetrics fm = g.getFontMetrics(); // need fm for text positioning
        double t_width = fm.getStringBounds(text, g).getWidth();

        g.drawString(text, (int) (x - t_width / 2), (y + fm.getMaxAscent() / 2));

    }

    public void drawVerticeNumber(String text, int x, int y) {

        Font fontSettings = new Font("Tahoma", 1, 15); // font settings for vertice bubbles
        g.setFont(fontSettings);

        FontMetrics fm = g.getFontMetrics(); 
        double t_width = fm.getStringBounds(text, g).getWidth();

        g.drawString(text, (int) (x - t_width / 2), (y + fm.getMaxAscent() / 2));
    }

    // Math

    private static int sqr(int x) { // unfortunately the easiest way of doing this
        return x * x;
    }

    private static int distCalc(Point p, Point v, Point w) { // math for distance is sqrt((x2 - x1)^2 + (y2-y1)^2))
        return (int) Math.sqrt(distCalcSquared(p, v, w));
    }

    private static int distCalcSquared(Point one, Point two, Point three) { 

        double secondLine = sqr(two.x - three.x) + sqr(two.y - three.y);

        if (secondLine == 0)
            return sqr(one.x - two.x) + sqr(one.y - two.y);

        double triangulate = ((one.x - two.x) * (three.x - two.x) + (one.y - two.y) * (three.y - two.y)) / secondLine; //((x1 - x2) * (x3 - x2) + (y1 - y2) (y3 - y2)) / ((x2 - x3)^2 + (y^2 - y^3)^2)
        
        if (triangulate < 0)
            return sqr(one.x - two.x) + sqr(one.y - two.y);

        if (triangulate > 1)
            return sqr(one.x - three.x) + sqr(one.y - three.y);
            
        return sqr(one.x - new Point((int) (two.x + triangulate * (three.x - two.x)),(int) (two.y + triangulate * (three.y - two.y))).x) 
                + sqr(one.y - new Point((int) (two.x + triangulate * (three.x - two.x)),(int) (two.y + triangulate * (three.y - two.y))).y);
    }

    

}
