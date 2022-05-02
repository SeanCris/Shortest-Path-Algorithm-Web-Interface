import GUI.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {  }

        JFrame j = new JFrame();
        j.setTitle("test");

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        j.setSize(new Dimension(1250, 800));
        j.add(new MainWindow());

        j.setVisible(true);

    }

}
