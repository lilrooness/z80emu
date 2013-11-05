package z80.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 03/11/2013
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class Window extends JFrame {
    public Window(int width, int height) {
        setLayout(new BorderLayout());
        setSize(width, height);
        InfoPanel infoPanel = new InfoPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(infoPanel, BorderLayout.NORTH);
        add(new IDE(width, height), BorderLayout.SOUTH);
        add(new Controls(), BorderLayout.EAST);
        pack();
        Thread updateLoop = new Thread(infoPanel);
        updateLoop.start();
        setVisible(true);
    }
}
