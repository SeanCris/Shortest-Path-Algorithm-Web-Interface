package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

//import algorithms.DijkstraAlgorithm;
import controls.Controls;
import objects.Pathing;
import algorithms.Dijkstra;

public class MainWindow extends JPanel {

    private Pathing path;
    private Controls controls;

    public MainWindow() {   /// MAIN SETUP

        super.setLayout(new BorderLayout());

        setButtonPanel();

        setGraphPanel();

        setControlPanel();

    }

    private void setButtonPanel() {   /// THIS PANEL CONTAINS ALL BUTTONS, CAN ADD MORE EASILY

        JPanel buttons = new JPanel();

        buttons.setBackground(new Color(176, 232, 144));

        JButton solve = new JButton("Solve!");
        buttons.add(solve);

        JButton reset = new JButton("Reset");
        buttons.add(reset);

        solve.addActionListener(new ActionListener() { // djikstras call
            @Override
            public void actionPerformed(ActionEvent e) {
                Dijkstra dijkstraAlgorithm = new Dijkstra(path);
                try {
                    dijkstraAlgorithm.run();
                    controls.setPath(dijkstraAlgorithm.getDestinationPath());
                } catch (IllegalStateException ise) {
                    JOptionPane.showMessageDialog(null, ise.getMessage());
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controls.reset();
            }
        });

        add(buttons, BorderLayout.NORTH);

    }

    private void setGraphPanel() { /// THE ACTUAL VIEWPORT FOR ALGORITHM DEMONSTRATION

        path = new Pathing(); 
        controls = new Controls(path);  

        controls.setPreferredSize(new Dimension(1500, 1000)); // default window size, can make however big you want

        JScrollPane resizeVP = new JScrollPane();

        resizeVP.setViewportView(controls); // load controls

        add(resizeVP, BorderLayout.CENTER);


    }

    private void setControlPanel() {

        JPanel controls = new JPanel();

        JLabel info = new JLabel("Shift Left Click: Set Start | Shift Right Click: Set End Point | Ctrl Left C Drag: Drag and Drop Vertice | Del(Key): Delete Vertice");

        controls.add(info);

        controls.setBackground(new Color(176, 232, 144));


        add(controls, BorderLayout.SOUTH);
    }


    

}
