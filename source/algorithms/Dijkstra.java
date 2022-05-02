package algorithms;

// DJIK!

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import objects.Line;
import objects.Pathing;
import objects.Vertice;

public class Dijkstra {
    private boolean madeit = false;
    private String msg = null;

    private Pathing path;

    private Map<Vertice, Vertice> trashV;
    private Map<Vertice, Integer> storedDistance;

    private PriorityQueue<Vertice> unvisited;
    private HashSet<Vertice> visited;

    public class dCalc implements Comparator<Vertice> {
        @Override
        public int compare(Vertice starV, Vertice endV) {
            return storedDistance.get(starV) - storedDistance.get(endV);
        }
    };

    public Dijkstra(Pathing path) { // HASHMAP
        this.path = path;
        trashV = new HashMap<>(); // the bin, kinda
        storedDistance = new HashMap<>(); 

        for (Vertice vertice : path.getVertices()) { // get vertices
            storedDistance.put(vertice, Integer.MAX_VALUE); // throw them in the map at max value (rly big number)
        }
        visited = new HashSet<>();

        madeit = evaluate();
    }

    private boolean evaluate() {
        if (path.getStart() == null) {
            msg = "!! You haven't set a starting vertice !!";
            return false;
        }

        if (path.getEnd() == null) {
            msg = "!! You haven't set an ending vertice !!";
            return false;
        }

        for (Vertice vertice : path.getVertices()) {
            if (path.neighborCheck(vertice)) {
                msg = "!! You have unconnected vertices !!";
                return false;
            }
        }

        return true;
    }

    public void run() throws IllegalStateException {
        if (!madeit) { // if it cant make it to the vertice you throw the corresponding messages listed
                       // above
            throw new IllegalStateException(msg);
        }

        unvisited = new PriorityQueue<>(path.getVertices().size(), new dCalc()); // taked vertices you havent gone to,
                                                                                 // and stores their distances in the PQ

        Vertice start = path.getStart();

        storedDistance.put(start, 0);

        visited.add(start); // start init

        for (Line neighbor : getNeighboringL(start)) {

            Vertice neighboringV = getNeighboringV(neighbor, start);

            if (neighboringV == null)
                continue;

            storedDistance.put(neighboringV, neighbor.getWeight());
            trashV.put(neighboringV, start);
            unvisited.add(neighboringV);
        }

        while (!unvisited.isEmpty()) {
            Vertice target = unvisited.poll(); // set top of unvisited to target

            updateDistance(target); 

            unvisited.remove(target);
            visited.add(target);
        }

        for (Vertice vertice : path.getVertices()) {
            vertice.setPath(returnPath(vertice));
        }

        path.setCompletion(true);

    }

    private void updateDistance(Vertice vertice) {
        int distance = storedDistance.get(vertice); // set working distance

        for (Line neighbor : getNeighboringL(vertice)) { // checking neighboring lines
            Vertice connectedV = getNeighboringV(neighbor, vertice); // get the connected V

            if (visited.contains(connectedV)) // skip visited (VERY IMPORTANT, I LOST HAIR OVER THIS)
                continue;

            int current_dist = storedDistance.get(connectedV); // check
            int new_dist = distance + neighbor.getWeight(); // set

            if (new_dist < current_dist) { // replace if needed
                storedDistance.put(connectedV, new_dist);
                trashV.put(connectedV, vertice); // throw old one away
                unvisited.add(connectedV); // throw it back in unvisited
            }
        }
    }

    private Vertice getNeighboringV(Line line, Vertice vertice) { // get neighnoringVs
        if (line.getStartV() != vertice && line.getEndV() != vertice)
            return null;

        return vertice == line.getEndV() ? line.getStartV() : line.getEndV();
    }

    private List<Line> getNeighboringL(Vertice vertice) { // neighbor lines
        List<Line> neighborLs = new ArrayList<>();

        for (Line line : path.getLines()) {
            if (line.getStartV() == vertice || line.getEndV() == vertice)
                neighborLs.add(line);
        }

        return neighborLs;
    }

    public Integer getEndDistTotal() { // end point dist totals
        return storedDistance.get(path.getEnd());
    }

    public Integer checkDist(Vertice vertice) { // check PQ vertice for Dist
        return storedDistance.get(vertice);
    }

    public List<Vertice> getDestinationPath() { // back to path go get its end
        return returnPath(path.getEnd());
    }

    public List<Vertice> returnPath(Vertice vertice) {
        List<Vertice> route = new ArrayList<>();

        Vertice target = vertice;
        route.add(target);
        while (target != path.getStart()) {
            target = trashV.get(target);
            route.add(target);
        }

        Collections.reverse(route);

        return route;
    }

}
